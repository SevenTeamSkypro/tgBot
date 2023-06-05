package seventeam.tgbot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dog_owners")
public class DogOwner implements Owner {
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

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPets() {
        return Collections.singletonList((Dog) pets);
    }

    public void setPets(List<Pet> pets) {
        this.pets = Collections.singletonList((Dog) pets);
    }

    public LocalDateTime getProbation() {
        return probation;
    }

    public void setProbation(LocalDateTime probation) {
        this.probation = probation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogOwner dogOwner = (DogOwner) o;
        return Objects.equals(id, dogOwner.id) && Objects.equals(chatId, dogOwner.chatId) && Objects.equals(firstName, dogOwner.firstName) && Objects.equals(lastName, dogOwner.lastName) && Objects.equals(phoneNumber, dogOwner.phoneNumber) && Objects.equals(pets, dogOwner.pets) && Objects.equals(probation, dogOwner.probation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, firstName, lastName, phoneNumber, pets, probation);
    }
}