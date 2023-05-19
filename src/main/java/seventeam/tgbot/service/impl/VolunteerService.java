package seventeam.tgbot.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
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
    public void createUser(Long id, String firstName, String lastName, String phoneNumber) {
        volunteerRepository.saveAndFlush(new Volunteer(id, firstName, lastName, phoneNumber));
    }

    @Override
    public User getUser(Long id) {
        return volunteerRepository.getReferenceById(id);
    }

    @Override
    public User updateUser(@NotNull User user) {
        Volunteer volunteer = volunteerRepository.getReferenceById(user.getId());
        volunteerRepository.delete(volunteer);
        return volunteerRepository.saveAndFlush((Volunteer) user);
    }

    @Override
    public void deleteUser(User user) {
        volunteerRepository.delete((Volunteer) user);
    }

    public void callVolunteer(Long chatId, String phoneNumber) {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        if (volunteers != null) {
            for (Volunteer v : volunteers) {
                SendMessage sendMessage = new SendMessage(v.getChatId(), phoneNumber);
                telegramBot.execute(sendMessage);
            }
        } else new SendMessage(chatId, "Все волонтёры заняты");
    }
}
