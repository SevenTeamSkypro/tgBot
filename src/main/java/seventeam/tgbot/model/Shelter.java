package seventeam.tgbot.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class Shelter {
    private String address;
    public abstract String getAddress();
}
