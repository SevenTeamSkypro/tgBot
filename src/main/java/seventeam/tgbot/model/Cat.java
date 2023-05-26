package seventeam.tgbot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "cats")
public class Cat extends Pet {
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
    private CatOwner catOwner;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    @Nullable
    private ShelterCat shelterCat;

    public Cat(Long id, String name, String breed, Integer age, String suit, String gender, @org.jetbrains.annotations.Nullable CatOwner catOwner,
               @org.jetbrains.annotations.Nullable ShelterCat shelterCat) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
        this.gender = gender;
        this.catOwner = catOwner;
        this.shelterCat = shelterCat;
    }

    public Cat() {
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
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) && name.equals(cat.name) && breed.equals(cat.breed) && age.equals(cat.age) && suit.equals(cat.suit) && gender.equals(cat.gender) && Objects.equals(catOwner, cat.catOwner) && Objects.equals(shelterCat, cat.shelterCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, age, suit, gender, catOwner, shelterCat);
    }
}
