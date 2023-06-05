package seventeam.tgbot.model;

public interface User {

    Long getId();

    Long getChatId();

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);
}