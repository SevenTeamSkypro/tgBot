package seventeam.tgbot.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import seventeam.tgbot.exceptions.ClientNotFoundException;
import seventeam.tgbot.exceptions.NothingToReadException;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.model.User;
import seventeam.tgbot.repository.ClientRepository;
import seventeam.tgbot.service.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ClientServiceImpl implements UserService {
    private final ClientRepository clientRepository;
    private final TelegramBot telegramBot;
    private final VolunteerService volunteerService;

    public ClientServiceImpl(ClientRepository clientRepository, TelegramBot telegramBot, VolunteerService volunteerService) {
        this.clientRepository = clientRepository;
        this.telegramBot = telegramBot;
        this.volunteerService = volunteerService;
    }

    @Override
    public void createUser(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        Client client = new Client(id, chatId, firstName, lastName, phoneNumber);
        if (clientRepository.getByChatId(chatId) == null && volunteerService.getVolunteer(chatId) == null) {
            clientRepository.saveAndFlush(client);
        } else telegramBot.execute(new SendMessage(chatId, "Вы уже зарегистрированы!"));
    }

    public Client getUserByChatId(Long chatId) {
        return clientRepository.getByChatId(chatId);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        try {
            Client toUpdate = clientRepository.getReferenceById(user.getId());
            toUpdate.setFirstName(user.getFirstName());
            toUpdate.setLastName(user.getLastName());
            toUpdate.setPhoneNumber(user.getPhoneNumber());
            clientRepository.saveAndFlush(toUpdate);
        } catch (RuntimeException e) {
            throw new ClientNotFoundException("Клиента с таким id нет!");
        }
    }

    @Override
    public void deleteUser(Long id) {
        clientRepository.deleteById(id);
    }

    public String readFile(String path) {
        try {
            return Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new NothingToReadException("Такого файла нет!");
        }
    }
}
