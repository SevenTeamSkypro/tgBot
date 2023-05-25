package seventeam.tgbot.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.model.User;
import seventeam.tgbot.model.Volunteer;
import seventeam.tgbot.repository.VolunteerRepository;
import seventeam.tgbot.service.UserService;

import java.util.List;

@Service
public class VolunteerService implements UserService {
    private final TelegramBot telegramBot;
    private final VolunteerRepository volunteerRepository;

    public VolunteerService(TelegramBot telegramBot, VolunteerRepository volunteerRepository) {
        this.telegramBot = telegramBot;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public void createUser(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        volunteerRepository.saveAndFlush(new Volunteer(id, chatId, firstName, lastName, phoneNumber));
    }

    public List<Volunteer> getAll() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteer(String phoneNumber) {
        return volunteerRepository.findFirstByPhoneNumber(phoneNumber);
    }

    public Volunteer getVolunteer(Long chatId) {
        return volunteerRepository.getByChatId(chatId);
    }

    @Override
    public void updateUser(@NotNull User user) {
        Volunteer toUpdate = volunteerRepository.getById(user.getId());
        toUpdate.setFirstName(user.getFirstName());
        toUpdate.setLastName(user.getLastName());
        toUpdate.setPhoneNumber(user.getPhoneNumber());
        volunteerRepository.saveAndFlush(toUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        volunteerRepository.deleteById(id);
    }

    @Transactional
    public void callVolunteer(String phoneNumber) {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        for (Volunteer v : volunteers) {
            SendMessage sendMessage = new SendMessage(v.getChatId(), phoneNumber);
            telegramBot.execute(sendMessage);
        }
    }

    public void sendToVolunteer(Client client, Integer petId) {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        for (Volunteer v : volunteers) {
            SendMessage sendMessage = new SendMessage(v.getChatId(),
                    client.toString() + ", id питомца " + petId.toString());
            telegramBot.execute(sendMessage);
        }
    }
}
