package seventeam.tgbot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seventeam.tgbot.exceptions.VolunteerNotFoundException;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.model.Volunteer;
import seventeam.tgbot.repositories.VolunteerRepository;

import java.util.List;

@Service
public class VolunteerService {
    private final TelegramBot telegramBot;
    private final VolunteerRepository volunteerRepository;

    public VolunteerService(TelegramBot telegramBot, VolunteerRepository volunteerRepository) {
        this.telegramBot = telegramBot;
        this.volunteerRepository = volunteerRepository;
    }

    public void createVolunteer(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        volunteerRepository.saveAndFlush(new Volunteer(id, chatId, firstName, lastName, phoneNumber));
    }

    public List<Volunteer> getAll() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteer(String phoneNumber) {
        return volunteerRepository.findFirstByPhoneNumber(phoneNumber);
    }

    public Volunteer getVolunteer(Long chatId) {
        try {
            return volunteerRepository.getByChatId(chatId);
        } catch (RuntimeException e) {
            throw new VolunteerNotFoundException();
        }
    }

    public void updateVolunteer(@NotNull Volunteer volunteer) {
        try {
            Volunteer toUpdate = volunteerRepository.getById(volunteer.getId());
            toUpdate.setFirstName(volunteer.getFirstName());
            toUpdate.setLastName(volunteer.getLastName());
            toUpdate.setPhoneNumber(volunteer.getPhoneNumber());
            volunteerRepository.saveAndFlush(toUpdate);
        } catch (RuntimeException e) {
            throw new VolunteerNotFoundException();
        }
    }

    public void deleteVolunteer(Long id) {
        try {
            volunteerRepository.deleteById(id);
        }catch (RuntimeException e) {
            throw new VolunteerNotFoundException();
        }
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
        if (client != null) {
            for (Volunteer v : volunteers) {
                SendMessage sendMessage = new SendMessage(v.getChatId(),
                        client + ", id питомца " + petId.toString());
                telegramBot.execute(sendMessage);
            }
        }
    }
}