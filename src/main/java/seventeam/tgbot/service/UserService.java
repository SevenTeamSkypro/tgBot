package seventeam.tgbot.service;

import seventeam.tgbot.model.User;

public interface UserService {

    void createUser(Long id, Long chatId, String firstName, String lastName, String phoneNumber);

    void createUser(Long id, String firstName, String lastName, String phoneNumber, Long chatId);

    User updateUser(User user);

    void deleteUser(User user);
}
