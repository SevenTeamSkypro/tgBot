package seventeam.tgbot.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import seventeam.tgbot.model.Volunteer;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    @NotNull
    @Override
    @Transactional
    List<Volunteer> findAll();
    void deleteById(@NotNull Long id);

    Volunteer getByChatId(Long catId);
}
