package seventeam.tgbot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "dog_owners")
public class DogOwner extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "pets", nullable = false)
    @OneToMany(mappedBy = "dogOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> pets;
    @Column(name = "probation")
    private LocalDateTime probation;

    public DogOwner() {
    }

    public DogOwner(Long id, String firstName, String lastName, String phoneNumber, List<Dog> pets, LocalDateTime probation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
        this.probation = probation;
    }

    public DogOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber, List<Dog> pets,
                    LocalDateTime probation) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
        this.probation = probation;
    }

    public LocalDateTime getProbation() {
        return probation;
    }

    public Long getChatId() {
        return chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DogOwner dogOwner = (DogOwner) o;
        return Objects.equals(id, dogOwner.id) && firstName.equals(dogOwner.firstName) && lastName.equals(dogOwner.lastName) && phoneNumber.equals(dogOwner.phoneNumber) && pets.equals(dogOwner.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, phoneNumber, pets);
    }
}