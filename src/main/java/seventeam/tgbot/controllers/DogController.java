package seventeam.tgbot.controllers;

import org.springframework.web.bind.annotation.*;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.service.impl.DogServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    private final DogServiceImpl dogService;

    public DogController(DogServiceImpl dogService) {
        this.dogService = dogService;
    }

    @PostMapping("/new")
    public void createDog(@RequestParam(required = false, name = "id") Long id,
                          @RequestParam(required = false, name = "name") String name,
                          @RequestParam(required = false, name = "breed") String breed,
                          @RequestParam(required = false, name = "age") Integer age,
                          @RequestParam(required = false, name = "suit") String suit,
                          @RequestParam(required = false, name = "gender") String gender) {
        dogService.createDog(id, name, breed, age, suit, gender);
    }

    @GetMapping("/get")
    public DogDto getDog(Long id) {
        return (DogDto) dogService.getPet(id);
    }

    @GetMapping("/all")
    public List<DogDto> getAll() {
        return dogService.getAllPets();
    }

    @DeleteMapping("/del")
    public void deleteDog(@RequestParam(required = false, name = "id") Long id) {
        dogService.deletePet(id);
    }
}