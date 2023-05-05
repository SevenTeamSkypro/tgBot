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
    public Client createUser(String firstName, String lastName, String phoneNumber, Long SHELTER_ID) {
        Long id = 0L;
        id++;
        Client client = new Client(id, firstName, lastName, phoneNumber, SHELTER_ID);
        clientRepository.save(client);
        return client;
    }

    @Override
    public User getUser(Long id) {
        return clientRepository.getReferenceById(id);
    }

    @Override
    public User updateUser(User user) {
        clientRepository.delete((Client) user);
        return clientRepository.save((Client) user);
    }

    @Override
    public void deleteUser(User user) {
        clientRepository.delete((Client) user);
    }
}
