package seventeam.tgbot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cats")
public class Cat extends Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Cat(long id, String name, String breed, Integer age, String suit, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
        this.gender = gender;
    }

    public Cat() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return id.equals(cat.id) && name.equals(cat.name) && breed.equals(cat.breed) && age.equals(cat.age) && suit.equals(cat.suit) && gender.equals(cat.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, age, suit, gender);
    }

    @Override
    public String toString() {
        return gender +
                " кличка " + name +
                ", порода " + breed +
                ", возраст " + age +
                ", масть " + suit;
    }
}
