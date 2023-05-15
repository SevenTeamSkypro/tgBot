package seventeam.tgbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import seventeam.tgbot.model.ShelterCat;
import seventeam.tgbot.model.ShelterDog;
import seventeam.tgbot.service.KeyBoardService;
import seventeam.tgbot.service.ReportService;
import seventeam.tgbot.service.impl.CatServiceImpl;
import seventeam.tgbot.service.impl.ClientServiceImpl;
import seventeam.tgbot.service.impl.DogServiceImpl;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
        ShelterDog shelterDog = new ShelterDog();
        ShelterCat shelterCat = new ShelterCat();
        try {
            updates.stream()
                    .filter(update -> update.message() != null)
                    .forEach(update -> {
                        Message message = update.message();
                        Integer messageId = update.message().messageId();
                        Long chatId = message.chat().id();
                        String text = update.message().text();
                        String firstName = update.message().chat().firstName();
                        String lastName = update.message().chat().lastName();
                        String phoneNumber = "333-33-33";
                        switch (text) {
                            case START -> {
                                sendMassage(chatId, "Приветствую тебя, " + firstName);
                                keyBoardService.chooseMenu(chatId);
                            }
                            case "\uD83D\uDC31 CAT" -> {
                                isCatNotDog = true;
                                keyBoardService.menu(chatId);
                                sendMassage(chatId, "Выбрана кошка");
                                clientService.createUser(chatId, firstName, lastName, phoneNumber,
                                        shelterCat.getSHELTER_ID());
                            }
                            case "\uD83D\uDC36 DOG" -> {
                                isCatNotDog = false;
                                keyBoardService.menu(chatId);
                                sendMassage(chatId, "Выбрана собака");
                                clientService.createUser(chatId, firstName, lastName, phoneNumber,
                                        shelterDog.getSHELTER_ID());
                            }
                            case "Главное меню", "Вернуться в главное меню" -> keyBoardService.menu(chatId);
                            case "Информация о приюте" -> keyBoardService.menuInfo(chatId);
                            case "Рассказать о нашем приюте" -> {
                                try {
                                    sendMassage(chatId, readFile("src/main/resources/draw/info.txt",
                                            StandardCharsets.UTF_8));
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
                                reportService.createReport(update.message());
                            }
                            case "Позвать волонтера" -> sendMassage(chatId, "Такая возможность скоро будет добавлена");
                            case "Правила ухода за животными" -> {
                                try {
                                    sendMassage(chatId, readFile("src/main/resources/draw/care_of_animals.txt",
                                            StandardCharsets.UTF_8));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            default -> replyMessage(chatId, "Такой команды нет", messageId);
                        }
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

    private String readFile(String path, Charset encoding) throws IOException {
        return Files.readString(Paths.get(path), encoding);
    }

    public void replyMessage(Long chatId, String messageText, Integer messageId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyToMessageId(messageId);
        telegramBot.execute(sendMessage);
    }
}
