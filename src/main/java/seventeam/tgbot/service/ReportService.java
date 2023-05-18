package seventeam.tgbot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;
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
    //TODO Выставить допустимый размер
    public void createReport(@NotNull Update update) {
        PhotoSize photoSize = update.message().photo()[0];
        Long chatId = update.message().chat().id();
        if (photoSize != null) {
            GetFile getFile = new GetFile(photoSize.fileId());
            File file = telegramBot.execute(getFile).file();
            Report report = new Report(
                    chatId,
                    chatId,
                    LocalDateTime.now(),
                    file,
                    update.message().caption()
            );
            if (report.getReport() == null) {
                telegramBot.execute(new SendMessage(chatId, "Заполните отчёт!"));
            } else {
                reportRepository.saveAndFlush(report);
                telegramBot.execute(new SendMessage(chatId, "Отчёт отправлен!"));
            }
        } else telegramBot.execute(new SendMessage(chatId, "Добавьте фото!"));
    }

    public Report getReport(Long chatId) {
        return reportRepository.getReferenceById(chatId);
    }
}
