package seventeam.tgbot.model;

import java.util.Objects;

public class Dog extends Pet {

    private final long id;
    private final String name;
    private final String breed;
    private final int age;
    private final String suit;

    public Dog(long id, String name, String breed, int age, String suit) {
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
        Dog dog = (Dog) o;
        return id == dog.id && age == dog.age && name.equals(dog.name) && breed.equals(dog.breed) && suit.equals(dog.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, age, suit);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", suit='" + suit + '\'' +
                "} ";
    }

    public static void main(String[] args) {
        Dog dog = new Dog(1, "Leo", "Taxe", 7, "brown leopard");
        System.out.println(dog.getName());
    }
}
