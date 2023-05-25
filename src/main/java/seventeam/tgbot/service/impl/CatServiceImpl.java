package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.model.CatOwner;
import seventeam.tgbot.model.Pet;
import seventeam.tgbot.model.ShelterCat;
import seventeam.tgbot.repository.ShelterCatRepository;
import seventeam.tgbot.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatServiceImpl implements PetService {
    private final ShelterCatRepository shelterCatRepository;
    private List<Cat> cats = new ArrayList<>();
    public CatServiceImpl(ShelterCatRepository shelterCatRepository) {
        this.shelterCatRepository = shelterCatRepository;
    }

    public void createCat(Long id, String name, String breed, Integer age, String suit, String gender,
                          CatOwner catOwner, ShelterCat shelterCat) {
        Cat cat = new Cat(id, name, breed, age, suit, gender, catOwner, shelterCat);
        cats = shelterCatRepository.findAll();
        if (!cats.contains(cat)) {
            cats.add(Math.toIntExact(id), cat);
            shelterCatRepository.saveAndFlush(cat);
        }
    }

    @Override
    public List<Cat> getAllPets() {
        return shelterCatRepository.findAll();
    }
    
    @Override
    public List<Pet> getEqualsPets(String breed, Integer age, String suit, String gender) {
        cats = shelterCatRepository.findAll();
        return cats.stream().filter(cat -> cat.getBreed().equals(breed)
                && cat.getAge() <= age
                && cat.getSuit().equals(suit)
                && cat.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public Pet getPet(Long id) {
        return shelterCatRepository.getReferenceById(id);
    }

    @Override
    public void update(Pet pet) {
        Cat toUpdate = shelterCatRepository.getReferenceById(pet.getId());
        toUpdate.setAge(pet.getAge());
        shelterCatRepository.saveAndFlush(toUpdate);
    }

    @Override
    public void deletePet(Long id) {
        shelterCatRepository.deleteById(id);
    }
}
