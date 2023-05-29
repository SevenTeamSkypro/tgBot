package seventeam.tgbot.model;

import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "shelter_cat")
public class ShelterCat extends Shelter {
    @Id
    @Column(name = "shelter_id", nullable = false)
    private Long SHELTER_ID = 2L;
    @Transient
    private transient List<Cat> pets;
    @Transient
    private transient List<CatOwner> users;

    public ShelterCat() {
    }

    @Override
    public String getAddress() {
        try {
            return Files.readString(Paths.get("src/main/resources/draw/shelter_cat_address.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}