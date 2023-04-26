package seventeam.tgbot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dogs")
public class Dog extends Pet {
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

    public Dog(Long id, String name, String breed, Integer age, String suit, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
        this.gender = gender;
    }

    public Dog() {
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
        Dog dog = (Dog) o;
        return id.equals(dog.id) && name.equals(dog.name) && breed.equals(dog.breed) && age.equals(dog.age) && suit.equals(dog.suit) && gender.equals(dog.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, age, suit, gender);
    }

    @Override
    public String toString() {
        return gender +
                ", кличка " + name +
                ", порода " + breed +
                ", возраст " + age +
                ", масть " + suit;
    }
}
