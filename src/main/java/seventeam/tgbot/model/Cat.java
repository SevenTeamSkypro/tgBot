package seventeam.tgbot.model;

import java.util.Objects;

public class Cat extends Pet{
    private final String name;
    private final String breed;
    private final int age;
    private final String suit;

    public Cat(String name, String breed, int age, String suit) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return age == cat.age && name.equals(cat.name) && breed.equals(cat.breed) && suit.equals(cat.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, breed, age, suit);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", suit='" + suit + '\'' +
                "} ";
    }
}
