package seventeam.tgbot.service;

import seventeam.tgbot.model.Pet;

import java.util.List;

public interface PetService {

    List<?> getAllPets();

    List<Pet> getPets(String breed, Integer age, String suit, String gender);

    Pet getPet(Long id);

    void update(Pet pet);

    void deletePet(Long id);
}
