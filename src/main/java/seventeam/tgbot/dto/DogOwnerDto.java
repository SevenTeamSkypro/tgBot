package seventeam.tgbot.dto;

import lombok.Getter;
import lombok.Setter;
import seventeam.tgbot.model.Dog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
public class DogOwnerDto {
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Dog> pets;
    private LocalDateTime probation;

    public DogOwnerDto(Long id, Long chatId, String firstName, String lastName, String phoneNumber, List<Dog> pets,
                       LocalDateTime probation) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
        this.probation = probation;
    }

    public DogOwnerDto(Long chatId, String firstName, String lastName, String phoneNumber, List<Dog> pets,
                       LocalDateTime probation) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
        this.probation = probation;
    }

    public DogOwnerDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DogOwnerDto ownerDto = (DogOwnerDto) o;
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
