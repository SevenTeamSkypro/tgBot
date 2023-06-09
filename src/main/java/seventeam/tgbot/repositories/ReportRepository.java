package seventeam.tgbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}