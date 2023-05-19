package seventeam.tgbot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dogs")
public class Dog extends Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "breed", nullable = false)
    private String breed;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "suit", nullable = false)
    private String suit;
    @Column(name = "gender", nullable = false)
    private String gender;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private DogOwner dogOwner;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    private ShelterDog shelterDog;

    public Dog(Long id, String name, String breed, Integer age, String suit, String gender, DogOwner dogOwner,
               ShelterDog shelterDog) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
        this.gender = gender;
        this.dogOwner = dogOwner;
        this.shelterDog = shelterDog;
    }

    @Override
    public String toString() {
        return id + ", кличка: " + name +
                ", порода: " + breed +
                ", возраст: " + age +
                ", масть: " + suit +
                " " + gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dog dog = (Dog) o;
        return getId() != null && Objects.equals(getId(), dog.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
