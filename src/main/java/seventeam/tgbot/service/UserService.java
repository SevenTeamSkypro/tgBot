package seventeam.tgbot.service;

import seventeam.tgbot.model.User;

public interface UserService {
    void createUser(Long id, String firstName, String lastName, String phoneNumber);

    User getUser(Long id);

    User updateUser(User user);

    void deleteUser(User user);
}
