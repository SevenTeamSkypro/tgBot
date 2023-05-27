package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import seventeam.tgbot.service.impl.ClientServiceImpl;

@RestController
public class Controller {
    private final ClientServiceImpl clientService;

    public Controller(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String hello() {
        return "Бот запущен!";
    }

    @GetMapping("/")
    public String info() {
        return clientService.readFile("src/main/resources/draw/info.txt");
    }
}