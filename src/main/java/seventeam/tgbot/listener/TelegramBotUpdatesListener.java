package seventeam.tgbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import seventeam.tgbot.model.ShelterCat;
import seventeam.tgbot.model.ShelterDog;
import seventeam.tgbot.repository.ShelterCatRepository;
import seventeam.tgbot.repository.ShelterDogRepository;
import seventeam.tgbot.service.impl.DogServiceImpl;
import seventeam.tgbot.service.impl.KeyBoardShelter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private static final String START = "/start";

    @Autowired
    private final TelegramBot telegramBot;
    private final DogServiceImpl dogService;
    @Autowired
    private ShelterCatRepository shelterCatRepository;
    @Autowired
    private ShelterDogRepository shelterDogRepository;
    @Autowired
    private KeyBoardShelter keyBoardShelter;


    public TelegramBotUpdatesListener(TelegramBot telegramBot, DogServiceImpl dogService) {
        this.telegramBot = telegramBot;
        this.dogService = dogService;
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
                        String textUpdate = update.message().text();
                        String text = update.message().text();
                        ;
                        String nameUser = update.message().chat().firstName();
                        switch (text) {
                            case START:
                                sendMassage(chatId, "Приветствую тебя, " + nameUser);
                                keyBoardShelter.chooseMenu(chatId);
                                break;

                            case "\uD83D\uDC31 CAT":

                                isCatNotDog = true;
                                keyBoardShelter.menu(chatId);
                                sendMassage(chatId, "Выбрана кошка");
                                break;

                            case "\uD83D\uDC36 DOG":

                                isCatNotDog = false;
                                keyBoardShelter.menu(chatId);
                                sendMassage(chatId, "Выбрана собака");
                                break;
                            case "Главное меню":
                                keyBoardShelter.menu(chatId);
                                break;

                            default:
                                replyMessage(chatId, "Такой команды нет", messageId);
                                break;



//                        case "/command1" -> {
//                            sendMassage(chatId, shelterDog.getAddress());
//                            sendMassage(chatId, "Выберите питомца.");
//                            sendMassage(chatId, dogService.getAllPets().toString());
//                        }
//                        case "/command2" -> {
//                            sendMassage(chatId, shelterCat.getAddress());
//                        }
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

//    private void InlineKeyboardMarkup getInlineMessageButtons() {
//        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
//        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
//        var yesButton = new InlineKeyboardButton();

    }
}
