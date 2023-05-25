package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.service.impl.ClientServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/c")
public class ClientController {
    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/get")
    public Client getClient(Long chatId) {
        return clientService.getUserByChatId(chatId);
    }

    @GetMapping("/all")
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @PutMapping("/put")
    public void updateClient(@RequestParam(required = false, name = "id") Long id,
                             @RequestParam(required = false, name = "chatId") Long chatId,
                             @RequestParam(required = false, name = "firstName") String firstName,
                             @RequestParam(required = false, name = "lastName") String lastName,
                             @RequestParam(required = false, name = "phoneNumber") String phoneNumber) {
        clientService.updateUser(new Client(id, chatId, firstName, lastName, phoneNumber));
    }

    @DeleteMapping("/del")
    public void deleteClient(@RequestParam(required = false, name = "id") Long id) {
        clientService.deleteUser(id);
    }
}