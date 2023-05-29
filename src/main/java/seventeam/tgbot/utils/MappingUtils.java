package seventeam.tgbot.utils;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.dto.OwnerDto;
import seventeam.tgbot.model.*;

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

    public OwnerDto mapToOwnerDto(Owner entity) {
        OwnerDto dto = new OwnerDto();
        dto.setChatId(entity.getChatId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPets(entity.getPets());
        dto.setProbation(entity.getProbation());
        return dto;
    }

    public Owner mapToOwner(OwnerDto dto) {
        if (dto.getPets() != null && dto.getPets().contains(Cat.class)){
            CatOwner entity = new CatOwner();
            entity.setId(dto.getId());
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setPets(dto.getPets());
            entity.setProbation(dto.getProbation());
            return entity;
        } else if (dto.getPets() != null && dto.getPets().contains(Dog.class)) {
            DogOwner entity = new DogOwner();
            entity.setId(dto.getId());
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setPets(dto.getPets());
            entity.setProbation(dto.getProbation());
            return entity;
        }
        return null;
    }
}