package seventeam.tgbot.service;

import seventeam.tgbot.model.Client;
import seventeam.tgbot.model.User;

public interface UserService {
    Client createUser(String firstName, String lastName, String phoneNumber, Long SHELTER_ID);

    User getUser(Long id);

    User updateUser(User user);

    void deleteUser(User user);
}
