package seventeam.tgbot.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.CatOwner;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {
    List<CatOwner> findAllByProbation(LocalDateTime probation);
    void deleteById(@NotNull Long id);

    CatOwner getByChatId(Long chatId);
}