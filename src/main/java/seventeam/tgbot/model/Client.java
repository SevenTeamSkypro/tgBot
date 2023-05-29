package seventeam.tgbot.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public Client(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Client() {
    }

    @Override
    @Transactional
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Transactional
    public Long getChatId() {
        return chatId;
    }

    @Override
    @Transactional
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    @Transactional
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    @Transactional
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return id.equals(client.id) && firstName.equals(client.firstName) && Objects.equals(lastName, client.lastName) && phoneNumber.equals(client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, phoneNumber);
    }

    @Override
    public String toString() {
        return "Клиент: " +
                "id " + id +
                ", Имя " + firstName +
                ", Фамилия " + lastName +
                ", номер телефона +" + phoneNumber;
    }
}