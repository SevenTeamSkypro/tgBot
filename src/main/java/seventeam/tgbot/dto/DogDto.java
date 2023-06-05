package seventeam.tgbot.dto;

import seventeam.tgbot.model.Pet;

import java.time.LocalDate;
import java.util.Objects;

public class DogDto implements Pet {
    private Long id;
    private String name;
    private String breed;
    private LocalDate dateOfBirth;
    private String suit;
    private String gender;

    public DogDto(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
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

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogDto dogDto = (DogDto) o;
        return Objects.equals(id, dogDto.id) && Objects.equals(name, dogDto.name) && Objects.equals(breed, dogDto.breed) && Objects.equals(dateOfBirth, dogDto.dateOfBirth) && Objects.equals(suit, dogDto.suit) && Objects.equals(gender, dogDto.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, dateOfBirth, suit, gender);
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