package seventeam.tgbot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cat_owners")
public class CatOwner implements Owner {
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

    public CatOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber, List<Cat> pets, LocalDateTime probation) {
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
        return Collections.singletonList((Cat) pets);
    }

    public void setPets(List<Pet> pets) {
        this.pets = Collections.singletonList((Cat) pets);
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
        CatOwner catOwner = (CatOwner) o;
        return Objects.equals(id, catOwner.id) && Objects.equals(chatId, catOwner.chatId) && Objects.equals(firstName, catOwner.firstName) && Objects.equals(lastName, catOwner.lastName) && Objects.equals(phoneNumber, catOwner.phoneNumber) && Objects.equals(pets, catOwner.pets) && Objects.equals(probation, catOwner.probation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, firstName, lastName, phoneNumber, pets, probation);
    }
}