package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seventeam.tgbot.model.CatOwner;

public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {
}
