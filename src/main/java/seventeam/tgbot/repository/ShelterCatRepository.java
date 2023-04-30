package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seventeam.tgbot.model.Cat;

public interface ShelterCatRepository extends JpaRepository<Cat, Long> {

}
