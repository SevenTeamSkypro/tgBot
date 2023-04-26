package seventeam.tgbot.service;

import seventeam.tgbot.model.Dog;
import seventeam.tgbot.model.Pet;

import java.util.List;

public interface PetService {

    void createPet(Long id, String name, String breed, Integer age, String suit, String gender);

    List<Pet> getPets(String breed, Integer age, String suit, String gender);

    Pet getPet(Long id);

    void update(Pet pet);

    void deletePet(Long id);
}
