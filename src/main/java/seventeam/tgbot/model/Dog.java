package seventeam.tgbot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "dogs")
public class Dog implements Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "breed", nullable = false)
    private String breed;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "suit", nullable = false)
    private String suit;
    @Column(name = "gender", nullable = false)
    private String gender;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    @Nullable
    private DogOwner dogOwner;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    @Nullable
    private ShelterDog shelterDog;

    public Dog(Long id, String name, String breed, LocalDate dateOfBirth, String suit, String gender,
               @org.jetbrains.annotations.Nullable DogOwner dogOwner,
               @org.jetbrains.annotations.Nullable ShelterDog shelterDog) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
        this.dogOwner = dogOwner;
        this.shelterDog = shelterDog;
    }

    public Dog(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
    }

    public Dog() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(id, dog.id) && Objects.equals(name, dog.name) && Objects.equals(breed, dog.breed) && Objects.equals(dateOfBirth, dog.dateOfBirth) && Objects.equals(suit, dog.suit) && Objects.equals(gender, dog.gender) && Objects.equals(dogOwner, dog.dogOwner) && Objects.equals(shelterDog, dog.shelterDog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, dateOfBirth, suit, gender, dogOwner, shelterDog);
    }

    @Override
    public String toString() {
        return id + ", кличка: " + name +
                ", порода: " + breed +
                ", дата рождения: " + dateOfBirth +
                ", масть: " + suit +
                " " + gender;
    }
}