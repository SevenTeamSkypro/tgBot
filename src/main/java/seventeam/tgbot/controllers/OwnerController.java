package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.dto.OwnerDto;
import seventeam.tgbot.model.Pet;
import seventeam.tgbot.service.impl.OwnerService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Создание потомка абстрактного класса Owner
     * @param SHELTER_ID указывает объект какого класса-потомка нужно создать DogOwner или CatOwner
     */
    @PostMapping("/new")
    public void createOwner(@RequestParam(required = false, name = "id") Long id,
                            @RequestParam(required = false, name = "chatId") Long chatId,
                            @RequestParam(required = false, name = "firstName") String firstName,
                            @RequestParam(required = false, name = "lastName") String lastName,
                            @RequestParam(required = false, name = "phoneNumber") String phoneNumber,
                            @RequestParam(required = false, name = "SHELTER_ID") Long SHELTER_ID,
                            @RequestParam(required = false, name = "pets") Pet pet) {
        ownerService.createOwner(id, chatId, firstName, lastName, phoneNumber, SHELTER_ID, pet);
    }

    /**
     * Получение потомка абстрактного класса Owner
     * @param shelterId указывает объект какого класса-потомка нужно получить
     * @return DogOwner или CatOwner
     */
    @GetMapping("/get")
    public OwnerDto getOwner(@RequestParam(required = false, name = "id") Long id,
                             @RequestParam(required = false, name = "shelterId") Long shelterId) {
        return ownerService.getOwner(id, shelterId);
    }

    /**
     * Изменение потомка абстрактного класса Owner
     * @param SHELTER_ID указывает объект какого класса-потомка нужно изменить
     */
    @PutMapping("/put")
    public void updateOwner(@RequestParam(required = false, name = "id") Long id,
                            @RequestParam(required = false, name = "chatId") Long chatId,
                            @RequestParam(required = false, name = "firstName") String firstName,
                            @RequestParam(required = false, name = "lastName") String lastName,
                            @RequestParam(required = false, name = "phoneNumber") String phoneNumber,
                            @RequestParam(required = false, name = "SHELTER_ID") Long SHELTER_ID,
                            @RequestParam(required = false, name = "probation") LocalDateTime probation) {
        ownerService.updateOwner(id, chatId, firstName, lastName, phoneNumber, SHELTER_ID, probation);
    }

    /**
     * Удаление потомка абстрактного класса Owner
     * @param shelterId указывает объект какого класса-потомка нужно удалить
     */
    @DeleteMapping("/del")
    public void deleteOwner(Long id, Long shelterId) {
        ownerService.deleteOwner(id, shelterId);
    }
}
