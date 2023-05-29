package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.service.impl.CatServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatServiceImpl catService;

    public CatController(CatServiceImpl catService) {
        this.catService = catService;
    }

    /**
     * Создание нового питомца типа Cat
     */
    @PostMapping("/new")
    public void createCat(@RequestParam(required = false, name = "name") String name,
                          @RequestParam(required = false, name = "breed") String breed,
                          @RequestParam(required = false, name = "dateOfBirth")LocalDate dateOfBirth,
                          @RequestParam(required = false, name = "suit") String suit,
                          @RequestParam(required = false, name = "gender") String gender) {
        catService.createCat(name, breed, dateOfBirth, suit, gender);
    }

    /**
     * Получение экземпляра класса
     *
     * @return CatDto instance
     */
    @GetMapping("/get")
    public CatDto getCat(Long id) {
        return (CatDto) catService.getPet(id);
    }

    /**
     * Получение всех имеющихся CatDto
     *
     * @return Список CatDto объектов
     */
    @GetMapping("/all")
    public List<CatDto> getAll() {
        return catService.getAllPets();
    }

    /**
     * Удаление Cat instance
     */
    @DeleteMapping("/del")
    public void deleteCat(@RequestParam(required = false, name = "id") Long id) {
        catService.deletePet(id);
    }
}