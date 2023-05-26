package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.model.CatOwner;
import seventeam.tgbot.model.ShelterCat;
import seventeam.tgbot.service.impl.CatServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatServiceImpl catService;

    public CatController(CatServiceImpl catService) {
        this.catService = catService;
    }

    @PostMapping("/new")
    public void createCat(@RequestParam(required = false, name = "id") Long id,
                          @RequestParam(required = false, name = "name") String name,
                          @RequestParam(required = false, name = "breed") String breed,
                          @RequestParam(required = false, name = "age") Integer age,
                          @RequestParam(required = false, name = "suit") String suit,
                          @RequestParam(required = false, name = "gender") String gender) {
        catService.createCat(id, name, breed, age, suit, gender);
    }

    @GetMapping("/get")
    public Cat getCat(Long id) {
        return (Cat) catService.getPet(id);
    }

    @GetMapping("/all")
    public List<Cat> getAll() {
        return catService.getAllPets();
    }

    @PutMapping("/put")
    public void updateCat(@RequestParam(required = false, name = "id") Long id,
                          @RequestParam(required = false, name = "name") String name,
                          @RequestParam(required = false, name = "breed") String breed,
                          @RequestParam(required = false, name = "age") Integer age,
                          @RequestParam(required = false, name = "suit") String suit,
                          @RequestParam(required = false, name = "gender") String gender,
                          @RequestParam(required = false, name = "catOwner") CatOwner catOwner,
                          @RequestParam(required = false, name = "shelterCat") ShelterCat shelterCat) {
        catService.update(new Cat(id, name, breed, age, suit, gender, catOwner, shelterCat));
    }

    @DeleteMapping("/del")
    public void deleteCat(@RequestParam(required = false, name = "id") Long id) {
        catService.deletePet(id);
    }
}
