package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.model.Volunteer;
import seventeam.tgbot.service.impl.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/v")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/get")
    public Volunteer getVolunteer(String phoneNumber) {
        return volunteerService.getVolunteer(phoneNumber);
    }

    @GetMapping("/all")
    public List<Volunteer> getAll() {
        return volunteerService.getAll();
    }

    @PutMapping("/put")
    public void updateVolunteer(@RequestParam(required = false, name = "id") Long id,
                                @RequestParam(required = false, name = "chatId") Long chatId,
                                @RequestParam(required = false, name = "firstName") String firstName,
                                @RequestParam(required = false, name = "lastName") String lastName,
                                @RequestParam(required = false, name = "phoneNumber") String phoneNumber) {
        volunteerService.updateUser(new Volunteer(id, chatId, firstName, lastName, phoneNumber));
    }

    @DeleteMapping("/del")
    public void deleteVolunteer(@RequestParam(required = false, name = "id") Long id) {
        volunteerService.deleteUser(id);
    }
}