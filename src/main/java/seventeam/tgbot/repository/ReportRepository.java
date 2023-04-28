package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seventeam.tgbot.model.Report;

import java.time.LocalDateTime;
import java.util.List;


    public interface ReportRepository extends JpaRepository<Report, Long> {
        List<Report> findAllByLocalDateTime(LocalDateTime localDateTime);
    }

