package seventeam.tgbot.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seventeam.tgbot.model.Volunteer;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    void deleteById(@NotNull Long id);

    @NotNull
    @Override
    List<Volunteer> findAll();
}
