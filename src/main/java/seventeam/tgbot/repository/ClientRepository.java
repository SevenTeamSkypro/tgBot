package seventeam.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seventeam.tgbot.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
