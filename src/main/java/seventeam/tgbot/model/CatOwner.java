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
@Table(name = "cat_owners")
public class CatOwner extends User {
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
    @Column(name = "pets")
    @OneToMany(mappedBy = "catOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> pets;
    @Column(name = "probation")
    private LocalDateTime probation;

    public CatOwner() {
    }

    public CatOwner(Long id, String firstName, String lastName, String phoneNumber, List<Cat> pets, LocalDateTime probation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
        this.probation = probation;
    }

    public CatOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber, Long id1, Long chatId1, String firstName1, String lastName1, String phoneNumber1, List<Cat> pets, LocalDateTime probation) {
        super(id, chatId, firstName, lastName, phoneNumber);
        this.id = id1;
        this.chatId = chatId1;
        this.firstName = firstName1;
        this.lastName = lastName1;
        this.phoneNumber = phoneNumber1;
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
        CatOwner catOwner = (CatOwner) o;
        return Objects.equals(id, catOwner.id) && firstName.equals(catOwner.firstName) && lastName.equals(catOwner.lastName) && phoneNumber.equals(catOwner.phoneNumber) && pets.equals(catOwner.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, phoneNumber, pets);
    }
}
