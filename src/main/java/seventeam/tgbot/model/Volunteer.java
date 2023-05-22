package seventeam.tgbot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "volunteers")
public class Volunteer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Transient
    private transient Long chatId;
    @Transient
    private transient List<String> phoneNumbers = new ArrayList<>();
    public Volunteer(Long id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Volunteer() {
    }

    public Volunteer(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Long getChatId() {
        return chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(firstName, volunteer.firstName) && Objects.equals(lastName, volunteer.lastName) && Objects.equals(phoneNumber, volunteer.phoneNumber) && Objects.equals(chatId, volunteer.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, phoneNumber, chatId);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", chatId=" + chatId +
                "} " + super.toString();
    }
}
