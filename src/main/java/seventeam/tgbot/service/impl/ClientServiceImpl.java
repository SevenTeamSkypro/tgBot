package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.model.User;
import seventeam.tgbot.repository.ClientRepository;
import seventeam.tgbot.service.UserService;

@Service
public class ClientServiceImpl implements UserService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void createUser(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        Client client = new Client(id, chatId, firstName, lastName, phoneNumber);
        clientRepository.saveAndFlush(client);
    }

    @Override
    public void createUser(Long id, String firstName, String lastName, String phoneNumber, Long chatId) {

    }

    public Client getUserByChatId(Long chatId) {
        return clientRepository.getByChatId(chatId);
    }

    @Override
    public Client updateUser(User user) {
        clientRepository.delete((Client) user);
        return clientRepository.saveAndFlush((Client) user);
    }

    @Override
    public void deleteUser(User user) {
        clientRepository.delete((Client) user);
    }
}
