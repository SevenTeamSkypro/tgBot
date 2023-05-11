package seventeam.tgbot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Report;
import seventeam.tgbot.repository.ReportRepository;
import seventeam.tgbot.service.impl.OwnerServiceImpl;

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

    public void createReport(Long id, Long chatId, File photo, String report) {
        save(new Report(id, chatId, LocalDateTime.now(), photo, report));
    }

    public void save(Report report) {
        if (report.getReport() == null) {
            telegramBot.execute(new SendMessage(report.getId(),"Заполните отчёт!"));
        }
        if (report.getPhoto() == null) {
            System.out.println("Добавьте фото!");
        } else {
            reportRepository.saveAndFlush(report);
        }
    }
}
