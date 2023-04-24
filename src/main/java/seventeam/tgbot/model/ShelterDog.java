package seventeam.tgbot.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShelterDog extends Shelter {
    public ShelterDog(String address) {
        super(address);
    }

    @Override
    public String getAddress() {
        try {
            return Files.readString(Paths.get("src/main/resources/draw/shelter_dog_address.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
