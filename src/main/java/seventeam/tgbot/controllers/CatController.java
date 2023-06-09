package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.services.CatService;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    /**
     * Создание нового питомца типа Cat
     */
    @PostMapping("/new")
    public void createCat(@RequestBody CatDto catDto) {
        catService.createCat(catDto.getName(), catDto.getBreed(), catDto.getDateOfBirth(), catDto.getSuit(), catDto.getGender());
    }

    /**
     * Получение экземпляра класса
     *
     * @return CatDto instance
     */
    @GetMapping("/get")
    public CatDto getCat(Long id) {
        return catService.getCat(id);
    }

    /**
     * Получение всех имеющихся CatDto
     *
     * @return Список CatDto объектов
     */
    @GetMapping("/all")
    public List<CatDto> getAll() {
        return catService.getAllCats();
    }

    /**
     * Удаление Cat instance
     */
    @DeleteMapping("/del")
    public void deleteCat(@RequestParam(required = false, name = "id") Long id) {
        catService.deleteCat(id);
    }
}