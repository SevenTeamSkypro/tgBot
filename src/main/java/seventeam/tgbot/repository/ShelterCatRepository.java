package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.Cat;

@Repository
public interface ShelterCatRepository extends JpaRepository<Cat, Long> {

}
