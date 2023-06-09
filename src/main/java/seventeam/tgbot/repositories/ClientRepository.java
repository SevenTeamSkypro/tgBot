package seventeam.tgbot.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import seventeam.tgbot.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @NotNull
    @Override
    @Transactional
    List<Client> findAll();
    @Transactional
    Client getByChatId(Long chatId);
}