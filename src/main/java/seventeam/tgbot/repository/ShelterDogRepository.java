package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.Dog;

@Repository
public interface ShelterDogRepository extends JpaRepository<Dog, Long> {
}
