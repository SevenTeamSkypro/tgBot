package seventeam.tgbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendSticker;
import org.springframework.stereotype.Component;
import seventeam.tgbot.model.ShelterCat;
import seventeam.tgbot.model.ShelterDog;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        ShelterDog shelterDog = new ShelterDog("address");
        ShelterCat shelterCat = new ShelterCat("address");
        updates.stream()
                .filter(update -> update.message() != null)
                .forEach(update -> {
                    Message message = update.message();
                    Long chatId = message.chat().id();
                    String text = message.text();
                    switch (text) {
                        case "/start" -> {
                            sendMassage(chatId, "Бот запущен");
                        }
                        case "/command1" -> {
                            sendMassage(chatId, shelterDog.getAddress());
                        }
                        case "/command2" -> {
                            sendMassage(chatId, shelterCat.getAddress());
                        }
                    }
                });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMassage(Long chatId, String massage) {
        SendMessage sendMessage = new SendMessage(chatId, massage);
        telegramBot.execute(sendMessage);
    }


}
