package seventeam.tgbot.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

public abstract class Shelter {
    private final String address;
public Shelter(String address) {
        this.address = address;
    }

    public String getAddress() throws IOException {
        return address;
    }
}
