package seventeam.tgbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import seventeam.tgbot.enums.Status;
import seventeam.tgbot.service.KeyBoardService;
import seventeam.tgbot.service.ReportService;
import seventeam.tgbot.service.impl.CatServiceImpl;
import seventeam.tgbot.service.impl.ClientServiceImpl;
import seventeam.tgbot.service.impl.DogServiceImpl;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private static final String START = "/start";
    private final TelegramBot telegramBot;
    private final DogServiceImpl dogService;
    private final CatServiceImpl catService;
    private final ClientServiceImpl clientService;
    private final KeyBoardService keyBoardService;
    private final ReportService reportService;
    private final Map<Long, Status> statuses = new HashMap<>();

    public TelegramBotUpdatesListener(TelegramBot telegramBot, DogServiceImpl dogService, CatServiceImpl catService, ClientServiceImpl clientService, KeyBoardService keyBoardService, ReportService reportService) {
        this.telegramBot = telegramBot;
        this.dogService = dogService;
        this.catService = catService;
        this.clientService = clientService;
        this.keyBoardService = keyBoardService;
        this.reportService = reportService;
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
                        if (statuses.containsValue(Status.NOT_COMPILED)) {
                            reportService.createReport(update);
                            statuses.remove(chatId);
                        }
                        if (message.contact() != null) {
                            Contact contact = message.contact();
                            String phoneNumber = contact.phoneNumber();
                            clientService.createUser(chatId, firstName, lastName, phoneNumber);
                            keyBoardService.chooseMenu(chatId);
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
                                    keyBoardService.menu(chatId);
                                }
                                case "\uD83D\uDC36 DOG" -> {
                                    isCatNotDog = false;
                                    sendMassage(chatId, "Выбран приют собак");
                                    keyBoardService.menu(chatId);
                                }
                                case "Главное меню", "Вернуться в главное меню" -> keyBoardService.menu(chatId);
                                case "Информация о приюте" -> keyBoardService.menuInfo(chatId);
                                case "Рассказать о нашем приюте" -> {
                                    try {
                                        sendMassage(chatId, readFile("src/main/resources/draw/info.txt"
                                        ));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                case "Взять питомца" -> {
                                    if (isCatNotDog) {
                                        sendMassage(chatId, dogService.getAllPets().toString());
                                    } else sendMassage(chatId, catService.getAllPets().toString());
                                }
                                case "Отчет" -> {
                                    statuses.put(chatId, Status.NOT_COMPILED);
                                    sendMassage(chatId, "Отправьте фото и отчёт одним сообщением");
                                }
                                case "Позвать волонтера" ->
                                        sendMassage(chatId, "Такая возможность скоро будет добавлена");
                                case "Правила ухода за животными" -> {
                                    try {
                                        sendMassage(chatId, readFile("src/main/resources/draw/care_of_animals.txt"
                                        ));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                            //TODO Разобраться с NPE
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

    private String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path), StandardCharsets.UTF_8);
    }

    public void replyMessage(Long chatId, String messageText, Integer messageId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyToMessageId(messageId);
        telegramBot.execute(sendMessage);
    }
}
