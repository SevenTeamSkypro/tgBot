package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.model.Pet;
import seventeam.tgbot.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CatService implements PetService {

    private final List<Cat> cats = new ArrayList<>();

    @Override
    public void createPet(Long id, String name, String breed, Integer age, String suit, String gender) {
        Cat cat = new Cat(id, name, breed, age, suit, gender);
        if (!cats.contains(cat)) {
            cats.add(Math.toIntExact(id), cat);
        }
    }

    @Override
    public List<Pet> getPets(String breed, Integer age, String suit, String gender) {
        return cats.stream().filter(cat -> cat.getBreed().equals(breed)
                && cat.getAge().equals(age)
                && cat.getSuit().equals(suit)
                && cat.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public Pet getPet(Long id) {
        return cats.get(Math.toIntExact(id));
    }

    @Override
    public void update(Pet pet) {
        cats.remove(Math.toIntExact(pet.getId()));
        cats.add(Math.toIntExact(pet.getId()), (Cat) pet);
    }

    @Override
    public void deletePet(Long id) {
        cats.remove(Math.toIntExact(id));
    }
}
