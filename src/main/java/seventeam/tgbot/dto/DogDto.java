package seventeam.tgbot.dto;

import seventeam.tgbot.model.Pet;

public class DogDto extends Pet {
    private Long id;
    private String name;
    private String breed;
    private Integer age;
    private String suit;
    private String gender;

    public DogDto(Long id, String name, String breed, Integer age, String suit, String gender) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.suit = suit;
        this.gender = gender;
    }

    public DogDto() {

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    @Override
    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return " " + gender +
                " id " + id +
                ", кличка " + name +
                ", порода " + breed +
                ", возраст " + age +
                ", окрас " + suit;
    }
}
