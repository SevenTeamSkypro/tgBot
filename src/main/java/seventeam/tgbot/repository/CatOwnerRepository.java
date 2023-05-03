package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.CatOwner;

@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {
}
