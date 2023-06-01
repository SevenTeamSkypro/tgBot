package seventeam.tgbot.dto;

import seventeam.tgbot.model.Pet;
import seventeam.tgbot.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OwnerDto extends User {
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Pet> pets;
    private LocalDateTime probation;

    public OwnerDto(Long id, Long chatId, String firstName, String lastName, String phoneNumber, List<Pet> pets,
                    LocalDateTime probation) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
        this.probation = probation;
    }

    public OwnerDto(Long chatId, String firstName, String lastName, String phoneNumber, List<Pet> pets,
                    LocalDateTime probation) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
        this.probation = probation;
    }

    public OwnerDto() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDateTime getProbation() {
        return probation;
    }

    public void setProbation(LocalDateTime probation) {
        this.probation = probation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OwnerDto ownerDto = (OwnerDto) o;
        return Objects.equals(id, ownerDto.id) && Objects.equals(chatId, ownerDto.chatId) && Objects.equals(firstName, ownerDto.firstName) && Objects.equals(lastName, ownerDto.lastName) && Objects.equals(phoneNumber, ownerDto.phoneNumber) && Objects.equals(pets, ownerDto.pets) && Objects.equals(probation, ownerDto.probation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, chatId, firstName, lastName, phoneNumber, pets, probation);
    }

    @Override
    public String toString() {
        return "OwnerDto{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pets=" + pets +
                ", probation=" + probation;
    }
}
