package seventeam.tgbot.model;

import java.util.Objects;

public class Cat extends Pet {
    private final long id;
    private final String name;
    private final String breed;
    private final int age;
    private final String suit;

    public Cat(long id, String name, String breed, int age, String suit) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
    }

    public long getId() {
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
    public int getAge() {
        return age;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return id == cat.id && age == cat.age && name.equals(cat.name) && breed.equals(cat.breed) && suit.equals(cat.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, age, suit);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", suit='" + suit + '\'' +
                "} ";
    }
}
