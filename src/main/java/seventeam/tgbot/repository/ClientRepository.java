package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
