package seventeam.tgbot.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public abstract class Shelter {
    private final String address;
public Shelter(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
