package seventeam.tgbot.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class Shelter {
    private Long SHELTER_ID;
    private String address;
    private List<Pet> pets;
    private List<User> users;

    public Long getShelterId() {
        return SHELTER_ID;
    }

    public void setShelterId(Long shelterId) {
        SHELTER_ID = shelterId;
    }

    public abstract String getAddress();

    public abstract List<?> getPets();

    public abstract List<?> getUsers();
}
