package seventeam.tgbot.model;

import java.time.LocalDateTime;
import java.util.List;

public interface Owner extends User{
    List<Pet> getPets();

    LocalDateTime getProbation();
}
