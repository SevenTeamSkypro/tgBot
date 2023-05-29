package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.service.impl.DogServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    private final DogServiceImpl dogService;

    public DogController(DogServiceImpl dogService) {
        this.dogService = dogService;
    }

    /**
     * Создание нового питомца типа Dog
     */
    @PostMapping("/new")
    public void createDog(@RequestParam(required = false, name = "name") String name,
                          @RequestParam(required = false, name = "breed") String breed,
                          @RequestParam(required = false, name = "dateOfBirth") LocalDate dateOfBirth,
                          @RequestParam(required = false, name = "suit") String suit,
                          @RequestParam(required = false, name = "gender") String gender) {
        dogService.createDog(name, breed, dateOfBirth, suit, gender);
    }

    /**
     * Получение экземпляра класса
     *
     * @return Dog instance
     */
    @GetMapping("/get")
    public DogDto getDog(Long id) {
        return (DogDto) dogService.getPet(id);
    }

    /**
     * Получение всех имеющихся DogDto
     *
     * @return Список DogDto объектов
     */
    @GetMapping("/all")
    public List<DogDto> getAll() {
        return dogService.getAllPets();
    }

    /**
     * Удаление Dog instance
     */
    @DeleteMapping("/del")
    public void deleteDog(@RequestParam(required = false, name = "id") Long id) {
        dogService.deletePet(id);
    }
}