package seventeam.tgbot.dto;

import seventeam.tgbot.model.Pet;

import java.time.LocalDate;

public class CatDto extends Pet {
    private Long id;
    private String name;
    private String breed;
    private LocalDate dateOfBirth;
    private String suit;
    private String gender;

    public CatDto(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
    }

    public CatDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return " " + gender +
                " id: " + id +
                ", кличка: " + name +
                ", порода: " + breed +
                ", дата рождения: " + dateOfBirth +
                ", окрас: " + suit;
    }
}