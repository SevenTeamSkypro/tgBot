package seventeam.tgbot.service;

import seventeam.tgbot.model.Pet;

public interface PetService {
    void createPet(String name, String breed, Integer age, String suit);

    Pet getPet(String breed, Integer age, String suit);

    Pet getPet(Long id);

    void updateAge(Integer age);

    void deletePet(Long id);
}
