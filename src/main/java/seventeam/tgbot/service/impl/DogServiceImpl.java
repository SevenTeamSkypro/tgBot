package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.model.Pet;
import seventeam.tgbot.repository.ShelterDogRepository;
import seventeam.tgbot.service.PetService;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements PetService {
    private final ShelterDogRepository shelterDogRepository;
    private final MappingUtils mappingUtils;
    private List<Dog> dogs = new ArrayList<>();

    public DogServiceImpl(ShelterDogRepository shelterDogRepository, MappingUtils mappingUtils) {
        this.shelterDogRepository = shelterDogRepository;
        this.mappingUtils = mappingUtils;
    }

    public void createDog(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        DogDto dto = new DogDto(name, breed, dateOfBirth, suit, gender);
        Dog dog = mappingUtils.mapToDog(dto);
        dogs = shelterDogRepository.findAll();
        if (!dogs.contains(dog)) {
            shelterDogRepository.saveAndFlush(dog);
        }
    }

    @Override
    public List<DogDto> getAllPets() {
        dogs = shelterDogRepository.findAll();
        return dogs.stream().map(mappingUtils::mapToDogDto).collect(Collectors.toList());
    }

    @Override
    public Pet getPet(Long id) {
        return mappingUtils.mapToDogDto(shelterDogRepository.getReferenceById(id));
    }

    @Override
    public void deletePet(Long id) {
        shelterDogRepository.deleteById(id);
    }
}