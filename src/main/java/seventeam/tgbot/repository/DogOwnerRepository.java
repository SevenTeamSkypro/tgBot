package seventeam.tgbot.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.DogOwner;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
    List<DogOwner> findAllByProbation(LocalDateTime probation);
    void deleteById(@NotNull Long id);
}