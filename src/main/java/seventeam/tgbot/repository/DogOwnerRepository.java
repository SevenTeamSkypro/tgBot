package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.DogOwner;

@Repository
public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
}
