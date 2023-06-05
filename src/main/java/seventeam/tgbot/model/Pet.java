package seventeam.tgbot.model;

import java.time.LocalDate;

public interface Pet {
    Long getId();

    String getName();

    String getBreed();

    LocalDate getDateOfBirth();

    String getSuit();

    String getGender();
}