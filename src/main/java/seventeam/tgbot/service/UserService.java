package seventeam.tgbot.service;

import seventeam.tgbot.model.User;

public interface UserService {

    void createUser(Long id, Long chatId, String firstName, String lastName, String phoneNumber);

    void updateUser(User user);

    void deleteUser(Long id);
}
