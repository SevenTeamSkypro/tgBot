package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.exceptions.PetNotFoundException;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.repository.ShelterDogRepository;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl {
    private final ShelterDogRepository shelterDogRepository;
    private final MappingUtils mappingUtils;
    private List<Dog> dogs = new ArrayList<>();

    public DogServiceImpl(ShelterDogRepository shelterDogRepository, MappingUtils mappingUtils) {
        this.shelterDogRepository = shelterDogRepository;
        this.mappingUtils = mappingUtils;
    }

    public DogDto createDog(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        DogDto dto = new DogDto(name, breed, dateOfBirth, suit, gender);
        Dog dog = mappingUtils.mapToDog(dto);
        dogs = shelterDogRepository.findAll();
        if (!dogs.contains(dog)) {
            shelterDogRepository.saveAndFlush(dog);
        }
        return dto;
    }

    public List<DogDto> getAllDogs() {
        dogs = shelterDogRepository.findAll();
        return dogs.stream().map(mappingUtils::mapToDogDto).collect(Collectors.toList());
    }

    public DogDto getDog(Long id) {
        try {
            return mappingUtils.mapToDogDto(shelterDogRepository.getReferenceById(id));
        } catch (RuntimeException e) {
            throw new PetNotFoundException();
        }
    }

    public void deleteDog(Long id) {
        try {
            shelterDogRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new PetNotFoundException();
        }
    }
}