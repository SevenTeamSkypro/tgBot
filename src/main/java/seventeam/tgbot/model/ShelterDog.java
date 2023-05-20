package seventeam.tgbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import seventeam.tgbot.exception.InfoNotFoundException;

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
@Table(name = "shelter_dog")
public class ShelterDog extends Shelter {
    @Id
    @Column(name = "shelter_id", nullable = false)
    private Long SHELTER_ID = 1L;
    @Transient
    private transient List<Dog> pets;
    @Transient
    private transient List<DogOwner> users;

    public ShelterDog() {
    }

    @Transient
    @Override
    public String getAddress() {
        try {
            return Files.readString(Paths.get("src/main/resources/draw/shelter_dog_address.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InfoNotFoundException("Адреса приютов для животных не обнаружены");
        }
    }
}
