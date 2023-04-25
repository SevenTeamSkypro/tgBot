package seventeam.tgbot.model;

import java.util.Objects;

public class Cat extends Pet {
    private final Long id;
    private final String name;
    private final String breed;
    private final Integer age;
    private final String suit;

    private final String gender;

    public Cat(long id, String name, String breed, Integer age, String suit, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
        this.gender = gender;
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
