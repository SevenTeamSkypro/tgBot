package seventeam.tgbot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

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
    @Nullable
    private DogOwner dogOwner;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    @Nullable
    private ShelterDog shelterDog;

    public Dog(Long id, String name, String breed, Integer age, String suit, String gender, @org.jetbrains.annotations.Nullable DogOwner dogOwner,
               @org.jetbrains.annotations.Nullable ShelterDog shelterDog) {
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
}
