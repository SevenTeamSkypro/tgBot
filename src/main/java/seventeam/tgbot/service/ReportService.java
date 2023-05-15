package seventeam.tgbot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Report;
import seventeam.tgbot.repository.ReportRepository;

import java.time.LocalDateTime;

@Service
public class ReportService {
    private final TelegramBot telegramBot;
    private final ReportRepository reportRepository;
    public static final LocalDateTime reportingPeriod = LocalDateTime.now().plusMonths(1L);

    public ReportService(TelegramBot telegramBot, ReportRepository reportRepository) {
        this.telegramBot = telegramBot;
        this.reportRepository = reportRepository;
    }

    //TODO Заставить бота ждать отправки фото
    public void createReport(Message message) {
        PhotoSize[] photos = message.photo();
        if (photos != null) {
            for (PhotoSize photo : photos) {
                GetFile getFile = new GetFile(photo.fileId());
                File file = telegramBot.execute(getFile).file();
                Report report = new Report(
                        message.migrateFromChatId(),
                        message.chat().id(),
                        LocalDateTime.now(),
                        file,
                        message.text()
                );
                if (report.getReport() == null) {
                    telegramBot.execute(new SendMessage(message.chat().id(), "Заполните отчёт!"));
                }
                if (report.getPhoto() == null) {
                    telegramBot.execute(new SendMessage(message.chat().id(), "Добавьте фото!"));
                } else {
                    reportRepository.saveAndFlush(report);
                }
            }
        } else telegramBot.execute(new SendMessage(message.chat().id(), "photos is empty"));
    }
}
