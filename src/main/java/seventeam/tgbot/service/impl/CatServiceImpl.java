package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.model.CatOwner;
import seventeam.tgbot.model.Pet;
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
                        CatOwner catOwner) {
        Cat cat = new Cat(id, name, breed, age, suit, gender, catOwner);
        cats = shelterCatRepository.findAll();
        if (!cats.contains(cat)) {
            cats.add(Math.toIntExact(id), cat);
            shelterCatRepository.saveAndFlush(cat);
        }
    }

    @Override
    public List<Cat> getAllPets() {
        return cats = shelterCatRepository.findAll();
    }

    @Override
    public List<Pet> getPets(String breed, Integer age, String suit, String gender) {
        cats = shelterCatRepository.findAll();
        return cats.stream().filter(cat -> cat.getBreed().equals(breed)
                && cat.getAge().equals(age)
                && cat.getSuit().equals(suit)
                && cat.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public Pet getPet(Long id) {
        cats = shelterCatRepository.findAll();
        return cats.get(Math.toIntExact(id));
    }

    @Override
    public void update(Pet pet) {
        cats = shelterCatRepository.findAll();
        cats.remove(Math.toIntExact(pet.getId()));
        cats.add(Math.toIntExact(pet.getId()), (Cat) pet);
        shelterCatRepository.saveAndFlush((Cat) pet);
    }

    @Override
    public void deletePet(Long id) {
        cats = shelterCatRepository.findAll();
        cats.remove(Math.toIntExact(id));
    }
}
