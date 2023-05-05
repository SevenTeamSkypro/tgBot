package seventeam.tgbot.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class Shelter {
    private String address;
    private List<Pet> pets;
    private List<User> users;

    public abstract String getAddress();

    public abstract List<?> getPets();

    public abstract List<?> getUsers();
}
