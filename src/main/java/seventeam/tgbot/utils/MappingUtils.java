package seventeam.tgbot.utils;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.model.ShelterCat;
import seventeam.tgbot.model.ShelterDog;

@Service
public class MappingUtils {
    private final ShelterCat shelterCat = new ShelterCat();
    private final ShelterDog shelterDog = new ShelterDog();
    public CatDto mapToCatDto(Cat entity) {
        CatDto dto = new CatDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBreed(entity.getBreed());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setSuit(entity.getSuit());
        dto.setGender(entity.getGender());
        return dto;
    }

    public Cat mapToCat(CatDto dto) {
        Cat entity = new Cat();
        entity.setName(dto.getName());
        entity.setBreed(dto.getBreed());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setSuit(dto.getSuit());
        entity.setGender(dto.getGender());
        entity.setShelterCat(shelterCat);
        entity.setCatOwner(null);
        return entity;
    }

    public DogDto mapToDogDto(Dog entity) {
        DogDto dto = new DogDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBreed(entity.getBreed());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setSuit(entity.getSuit());
        dto.setGender(entity.getGender());
        return dto;
    }

    public Dog mapToDog(DogDto dto) {
        Dog entity = new Dog();
        entity.setName(dto.getName());
        entity.setBreed(dto.getBreed());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setSuit(dto.getSuit());
        entity.setGender(dto.getGender());
        entity.setShelterDog(shelterDog);
        entity.setDogOwner(null);
        return entity;
    }
}