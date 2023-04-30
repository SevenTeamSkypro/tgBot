package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seventeam.tgbot.model.Dog;

public interface ShelterDogRepository extends JpaRepository<Dog, Long> {
}
