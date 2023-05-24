package seventeam.tgbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import seventeam.tgbot.enums.Status;
import seventeam.tgbot.model.*;
import seventeam.tgbot.service.impl.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private static final String START = "/start";
    private final String PASSWORD = "пароль";
    private final TelegramBot telegramBot;
    private final DogServiceImpl dogService;
    private final CatServiceImpl catService;
    private final ClientServiceImpl clientService;
    private final KeyBoardService keyBoardService;
    private final ReportService reportService;
    private final OwnerService ownerService;
    private final VolunteerService volunteerService;
    private final Map<Long, Status> statuses = new HashMap<>();
    private final ShelterDog shelterDog = new ShelterDog();
    private final ShelterCat shelterCat = new ShelterCat();

    public TelegramBotUpdatesListener(TelegramBot telegramBot, DogServiceImpl dogService, CatServiceImpl catService, ClientServiceImpl clientService, KeyBoardService keyBoardService, ReportService reportService, OwnerService ownerService, VolunteerService volunteerService) {
        this.telegramBot = telegramBot;
        this.dogService = dogService;
        this.catService = catService;
        this.clientService = clientService;
        this.keyBoardService = keyBoardService;
        this.reportService = reportService;
        this.ownerService = ownerService;
        this.volunteerService = volunteerService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    private boolean isCatNotDog = false;

    @Override
    public int process(List<Update> updates) {
        try {
            updates.stream()
                    .filter(update -> update.message() != null)
                    .forEach(update -> {
                        Message message = update.message();
                        Long chatId = message.chat().id();
                        String text = message.text();
                        String firstName = message.chat().firstName();
                        String lastName = message.chat().lastName();
                        if (statuses.containsValue(Status.REPORT_NOT_COMPILED)) {
                            reportService.createReport(update);
                            statuses.remove(chatId);
                        }
                        if (message.contact() != null) {
                            Contact contact = message.contact();
                            String phoneNumber = contact.phoneNumber();
                            clientService.createUser(contact.userId(), chatId, firstName, lastName, phoneNumber);
                            keyBoardService.chooseMenu(chatId);
                        }
                        if (statuses.containsValue(Status.PET_ID_NOT_GET)) {
                            Integer petId = Integer.valueOf(text);
                            Client client = clientService.getUserByChatId(chatId);
                            volunteerService.sendToVolunteer(client, petId);
                        }
                        if (statuses.containsValue(Status.SEND_WARNING)) {
                            Long ownerChatId = Long.valueOf(text);
                            statuses.remove(chatId, Status.SEND_WARNING);
                            try {
                                sendMassage(ownerChatId, clientService.readFile("src/main/resources/draw/warning.txt"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (text != null) {
                            switch (text) {
                                case START -> {
                                    sendMassage(chatId, "Приветствую тебя, " + firstName);
                                    keyBoardService.getContact(chatId);
                                }
                                case "\uD83D\uDC31 CAT" -> {
                                    isCatNotDog = true;
                                    sendMassage(chatId, "Выбран приют кошек");
                                    keyBoardService.mainMenu(chatId);
                                }
                                case "\uD83D\uDC36 DOG" -> {
                                    isCatNotDog = false;
                                    sendMassage(chatId, "Выбран приют собак");
                                    keyBoardService.mainMenu(chatId);
                                }
                                case "Главное меню", "Вернуться в главное меню" -> keyBoardService.mainMenu(chatId);
                                case "Информация о приюте" -> keyBoardService.infoMenu(chatId);
                                case "Рассказать о нашем приюте" -> {
                                    try {
                                        sendMassage(chatId, clientService.readFile("src/main/resources/draw/info.txt"
                                        ));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                case "Взять питомца" -> {
                                    if (isCatNotDog) {
                                        sendMassage(chatId, dogService.getAllPets().toString());
                                    } else sendMassage(chatId, catService.getAllPets().toString());
                                    sendMassage(chatId, "Введите id питомца");
                                    statuses.put(chatId, Status.PET_ID_NOT_GET);
                                }
                                case "Отчет" -> {
                                    statuses.put(chatId, Status.REPORT_NOT_COMPILED);
                                    sendMassage(chatId, "Отправьте фото и отчёт одним сообщением");
                                }
                                case "Позвать волонтера" -> {
                                    volunteerService.callVolunteer(clientService.getUserByChatId(chatId).getPhoneNumber());
                                    sendMassage(chatId, "Скоро с вами свяжутся");
                                }
                                case "Правила ухода за животными" -> {
                                    try {
                                        sendMassage(chatId,
                                                clientService.readFile("src/main/resources/draw/care_of_animals.txt"
                                                ));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                case PASSWORD -> {
                                    if (clientService.getUserByChatId(chatId) != null) {
                                        Client client = clientService.getUserByChatId(chatId);
                                        volunteerService.createUser(client.getId(), chatId, client.getFirstName(),
                                                client.getLastName(), client.getPhoneNumber());
                                        clientService.deleteUser(client);
                                    }
                                    keyBoardService.volunteerMenu(chatId);
                                }
                                case "Отправить предупреждение" -> {
                                    statuses.put(chatId, Status.SEND_WARNING);
                                    sendMassage(chatId, "Введите chatId владельца!");
                                }
                                case "Проверить отчет" -> {
                                    List<Report> reports = reportService.getAll();
                                    for (Report report : reports) {
                                        try {
                                            sendReport(chatId, report.getPhoto(), report.getReport());
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            }
                        } else logger.info("Метод message.text() возвращает ожидаемый null");
                    });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMassage(Long chatId, String massage) {
        SendMessage sendMessage = new SendMessage(chatId, massage);
        telegramBot.execute(sendMessage);
    }

    private void sendReport(Long chatId, @NotNull File file, String report) throws IOException {
        telegramBot.execute(new SendMessage(chatId, telegramBot.getFullFilePath(file)));
        telegramBot.execute(new SendMessage(chatId, report));
    }

    public void replyMessage(Long chatId, String messageText, Integer messageId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyToMessageId(messageId);
        telegramBot.execute(sendMessage);
    }

    private void createOwner(Integer petId, Message message) {
        if (shelterCat.getPets() != null && shelterDog.getPets() != null) {
            if (isCatNotDog) {
                Dog dog = shelterDog.getPets().get(petId);
                ownerService.createOwner(Long.valueOf(petId), message.chat().id(), message.contact().firstName(),
                        message.contact().lastName(),
                        message.contact().phoneNumber(),
                        shelterDog.getShelterId(), dog);
                sendMassage(message.chat().id(), "Вы стали владельцем питомца по имени " + dog.getName());
                statuses.remove(message.chat().id());
            } else {
                Cat cat = shelterCat.getPets().get(0);
                ownerService.createOwner(Long.valueOf(petId), message.chat().id(), message.contact().firstName(),
                        message.contact().lastName(),
                        message.contact().phoneNumber(),
                        shelterCat.getShelterId(), cat);
                sendMassage(message.chat().id(), "Вы стали владельцем питомца по имени " + cat.getName());
                statuses.remove(message.chat().id());
            }
        } else {
            sendMassage(message.chat().id(), "Питомца с таким id нет");
            statuses.remove(message.chat().id(), Status.PET_ID_NOT_GET);
        }
        keyBoardService.mainMenu(message.chat().id());
    }
}
