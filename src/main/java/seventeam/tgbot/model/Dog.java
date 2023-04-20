package seventeam.tgbot.model;

import java.util.Objects;

public class Dog extends Pet {
    private final String name;
    private final String breed;
    private final int age;
    private final String suit;

    public Dog(String name, String breed, int age, String suit) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return age == dog.age && name.equals(dog.name) && breed.equals(dog.breed) && suit.equals(dog.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, breed, age, suit);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", suit='" + suit + '\'' +
                "} ";
    }
}
