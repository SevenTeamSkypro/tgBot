package seventeam.tgbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Report;
import seventeam.tgbot.repository.ReportRepository;

@Service
public class ReportService {
    @Autowired
    private final ReportRepository reportRepository;
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void save(Report report) {
        if (report.getReport() == null) {
            System.out.println("Заполните отчёт!");
        }
        if (report.getPhoto() == null) {
            System.out.println("Добавьте фото!");
        } else {
            reportRepository.saveAndFlush(report);
        }
    }
}
