package seventeam.tgbot.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ShelterCat extends Shelter {
    private final Integer SHELTER_ID = 2;
    @Override
    public String getAddress() {
        try {
            return Files.readString(Paths.get("src/main/resources/draw/shelter_cat_address.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cat> getPets() {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    public Integer getShelterId() {
        return SHELTER_ID;
    }
}
