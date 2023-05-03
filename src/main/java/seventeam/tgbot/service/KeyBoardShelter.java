package seventeam.tgbot.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seventeam.tgbot.listener.TelegramBotUpdatesListener;

@Service
public class KeyBoardShelter {

    @Autowired
    private TelegramBot telegramBot;

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    public void menu(long chatId) {
        logger.info("sendMessage: {}, {}", chatId, "Главное меню ");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton("информацию о приюте"));
        replyKeyboardMarkup.addRow(new KeyboardButton("взять питомца"), new KeyboardButton("отчет"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"));
        returnResponseReplyKeyboard(replyKeyboardMarkup, chatId, "Главное меню");
    }

    public void chooseMenu(long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "меню выбора ");
        String emoji_cat = EmojiParser.parseToUnicode(":cat:");
        String emoji_dog = EmojiParser.parseToUnicode(":dog:");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton(emoji_cat + " CAT"));
        replyKeyboardMarkup.addRow(new KeyboardButton(emoji_dog + " DOG"));
        returnResponseReplyKeyboard(replyKeyboardMarkup, chatId, "Скорее выбирай питомца");
    }

    public void returnResponseReplyKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String text) {
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);
        SendMessage request = new SendMessage(chatId, text).replyMarkup(replyKeyboardMarkup).parseMode(ParseMode.HTML).disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);
        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("Код ошибки: {}", codeError);
            logger.info("Описание -: {}", description);
        }
    }
}
