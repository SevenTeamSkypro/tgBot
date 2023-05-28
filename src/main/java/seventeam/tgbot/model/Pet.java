package seventeam.tgbot.model;

import java.time.LocalDate;

public abstract class Pet {
    private Long id;
    private String name;
    private String breed;
    private LocalDate dateOfBirth;
    private String suit;
    private String gender;

    public Pet() {
    }

    public Pet(Long id, String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

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

    public Long getId() {
        return id;
    }
}