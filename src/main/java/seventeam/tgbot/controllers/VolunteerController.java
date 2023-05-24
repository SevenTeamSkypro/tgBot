package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seventeam.tgbot.service.impl.VolunteerService;

@RestController
@RequestMapping("/v")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/get")
    public void getVolunteer(String phoneNumber) {
        volunteerService.getVolunteer(phoneNumber);
    }
}
