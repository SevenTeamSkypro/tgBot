package seventeam.tgbot.model;

import java.util.Objects;

public abstract class User {
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && chatId.equals(user.chatId) && firstName.equals(user.firstName) && Objects.equals(lastName, user.lastName) && phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, firstName, lastName, phoneNumber);
    }
}