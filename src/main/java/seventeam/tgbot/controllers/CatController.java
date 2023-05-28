package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.service.impl.CatServiceImpl;

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
     *
     * @param id Передавайте 0, id сгенерирует @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @PostMapping("/new")
    public void createCat(@RequestParam(required = false, name = "id") Long id,
                          @RequestParam(required = false, name = "name") String name,
                          @RequestParam(required = false, name = "breed") String breed,
                          @RequestParam(required = false, name = "age") Integer age,
                          @RequestParam(required = false, name = "suit") String suit,
                          @RequestParam(required = false, name = "gender") String gender) {
        catService.createCat(id, name, breed, age, suit, gender);
    }

    /**
     * Получение экземпляра класса
     *
     * @return Cat instance
     */
    @GetMapping("/get")
    public Cat getCat(Long id) {
        return (Cat) catService.getPet(id);
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