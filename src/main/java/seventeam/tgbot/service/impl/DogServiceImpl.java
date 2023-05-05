package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.model.DogOwner;
import seventeam.tgbot.model.Pet;
import seventeam.tgbot.repository.ShelterDogRepository;
import seventeam.tgbot.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements PetService {
    private final ShelterDogRepository shelterDogRepository;
    private List<Dog> dogs = new ArrayList<>();

    public DogServiceImpl(ShelterDogRepository shelterDogRepository) {
        this.shelterDogRepository = shelterDogRepository;
    }

    public void createDog(Long id, String name, String breed, Integer age, String suit, String gender,
                        DogOwner dogOwner) {
        Dog dog = new Dog(id, name, breed, age, suit, gender, dogOwner);
        dogs = shelterDogRepository.findAll();
        if (!dogs.contains(dog)) {
            dogs.add(Math.toIntExact(id), dog);
            shelterDogRepository.saveAndFlush(dog);
        }
    }

    @Override
    public List<Dog> getAllPets() {
        return dogs = shelterDogRepository.findAll();
    }

    @Override
    public List<Pet> getPets(String breed, Integer age, String suit, String gender) {
        dogs = shelterDogRepository.findAll();
        return dogs.stream().filter(dog -> dog.getBreed().equals(breed)
                && dog.getAge().equals(age)
                && dog.getSuit().equals(suit)
                && dog.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public Pet getPet(Long id) {
        dogs = shelterDogRepository.findAll();
        return dogs.get(Math.toIntExact(id));
    }

    @Override
    public void update(Pet pet) {
        dogs = shelterDogRepository.findAll();
        dogs.remove(Math.toIntExact(pet.getId()));
        dogs.add(Math.toIntExact(pet.getId()), (Dog) pet);
    }

    @Override
    public void deletePet(Long id) {
        dogs.remove(Math.toIntExact(id));
    }
}
