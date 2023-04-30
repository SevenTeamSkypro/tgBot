package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seventeam.tgbot.model.DogOwner;

public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
}
