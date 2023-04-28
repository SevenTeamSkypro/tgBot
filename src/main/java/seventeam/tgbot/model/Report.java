package seventeam.tgbot.model;

import com.pengrad.telegrambot.model.File;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date", nullable = false)
    private LocalDateTime localDateTime;
    @Column(name = "photo", nullable = false)
    private File photo;
    @Column(name = "report", nullable = false)
    private String report;

    public Report(LocalDateTime localDateTime, File photo, String report) {
        this.localDateTime = localDateTime;
        this.photo = photo;
        this.report = report;
    }

    public Report() {

    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report1 = (Report) o;
        return localDateTime.equals(report1.localDateTime) && photo.equals(report1.photo) && report.equals(report1.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDateTime, photo, report);
    }

    @Override
    public String toString() {
        return "Report{" +
                "localDateTime=" + localDateTime +
                ", report='" + report + '\'' +
                '}';
    }
}
