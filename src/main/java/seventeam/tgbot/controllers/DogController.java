package seventeam.tgbot.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.services.DogService;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    /**
     * Создание нового питомца типа Dog
     */
    @PostMapping("/new")
    public void createDog(@NotNull @RequestBody DogDto dog) {
        dogService.createDog(dog.getName(), dog.getBreed(), dog.getDateOfBirth(), dog.getSuit(), dog.getGender());
    }

    /**
     * Получение экземпляра класса
     *
     * @return Dog instance
     */
    @GetMapping("/get")
    public DogDto getDog(Long id) {
        return dogService.getDog(id);
    }

    /**
     * Получение всех имеющихся DogDto
     *
     * @return Список DogDto объектов
     */
    @GetMapping("/all")
    public List<DogDto> getAll() {
        return dogService.getAllDogs();
    }

    /**
     * Получение всех питомцев владельца
     *
     * @return List of DogDto
     */
    @GetMapping("/oid")
    public List<DogDto> getAllDogsByOwnerId(Long ownerId) {
        return dogService.getAllDogs(ownerId);
    }

    /**
     * Удаление Dog instance
     */
    @DeleteMapping("/del")
    public void deleteDog(@RequestParam(required = false, name = "id") Long id) {
        dogService.deleteDog(id);
    }
}