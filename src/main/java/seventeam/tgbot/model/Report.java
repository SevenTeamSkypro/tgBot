package seventeam.tgbot.model;

import com.pengrad.telegrambot.model.File;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
}
