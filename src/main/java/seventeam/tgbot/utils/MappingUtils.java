package seventeam.tgbot.utils;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.dto.OwnerCatDto;
import seventeam.tgbot.dto.OwnerDogDto;
import seventeam.tgbot.model.*;

@Service
public class MappingUtils {
    public CatDto mapToCatDto(Cat entity) {
        CatDto dto = new CatDto();
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
        entity.setCatOwner(null);
        return entity;
    }

    public DogDto mapToDogDto(Dog entity) {
        DogDto dto = new DogDto();
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
        entity.setDogOwner(null);
        return entity;
    }

    public OwnerDogDto mapToDogOwnerDto(DogOwner entity) {
        OwnerDogDto dto = new OwnerDogDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPets(entity.getPets());
        dto.setProbation(entity.getProbation());
        return dto;
    }
    public OwnerCatDto mapToCatOwnerDto(CatOwner entity) {
        OwnerCatDto dto = new OwnerCatDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPets(entity.getPets());
        dto.setProbation(entity.getProbation());
        return dto;
    }

    public DogOwner mapToDogOwner(OwnerDogDto dto) {
        if (dto.getPets() != null) {
            DogOwner entity = new DogOwner();
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setPets(dto.getPets());
            entity.setProbation(dto.getProbation());
            return entity;
        }
        return null;
    }

    public CatOwner mapToCatOwner(OwnerCatDto dto) {
        if (dto.getPets() != null) {
            CatOwner entity = new CatOwner();
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