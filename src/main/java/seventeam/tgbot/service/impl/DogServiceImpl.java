package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.model.Pet;
import seventeam.tgbot.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements PetService {
    private final List<Dog> dogs = new ArrayList<>();
    @Override
    public void createPet(Long id, String name, String breed, Integer age, String suit, String gender) {
        Dog dog = new Dog(id, name, breed, age, suit, gender);
        if (!dogs.contains(dog)) {
            dogs.add(Math.toIntExact(id), dog);
        }
    }

    @Override
    public List<Pet> getPets(String breed, Integer age, String suit, String gender) {
        return dogs.stream().filter(dog -> dog.getBreed().equals(breed)
                && dog.getAge().equals(age)
                && dog.getSuit().equals(suit)
                && dog.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public Pet getPet(Long id) {
        return dogs.get(Math.toIntExact(id));
    }

    @Override
    public void update(Pet pet) {
        dogs.remove(Math.toIntExact(pet.getId()));
        dogs.add(Math.toIntExact(pet.getId()), (Dog) pet);
    }

    @Override
    public void deletePet(Long id) {
        dogs.remove(Math.toIntExact(id));
    }
}
