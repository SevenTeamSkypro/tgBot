package seventeam.tgbot.dto;

import lombok.Getter;
import lombok.Setter;
import seventeam.tgbot.model.Cat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class OwnerCatDto {
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Cat> pets;
    private LocalDateTime probation;

    public OwnerCatDto(Long id, Long chatId, String firstName, String lastName, String phoneNumber, List<Cat> pets, LocalDateTime probation) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
        this.probation = probation;
    }

    public OwnerCatDto(Long chatId, String firstName, String lastName, String phoneNumber, List<Cat> pets) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
    }

    public OwnerCatDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerCatDto that = (OwnerCatDto) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(pets, that.pets) && Objects.equals(probation, that.probation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, firstName, lastName, phoneNumber, pets, probation);
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
