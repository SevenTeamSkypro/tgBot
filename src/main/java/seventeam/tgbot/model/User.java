package seventeam.tgbot.model;

import lombok.Data;

@Data
public abstract class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
