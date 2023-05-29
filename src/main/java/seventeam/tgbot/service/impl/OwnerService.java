package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.OwnerDto;
import seventeam.tgbot.exceptions.OwnerNotFoundException;
import seventeam.tgbot.model.*;
import seventeam.tgbot.repository.CatOwnerRepository;
import seventeam.tgbot.repository.DogOwnerRepository;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    private final DogOwnerRepository dogOwnerRepository;
    private final CatOwnerRepository catOwnerRepository;
    private final DogServiceImpl dogService;
    private final CatServiceImpl catService;
    private final MappingUtils mappingUtils;

    public OwnerService(DogOwnerRepository dogOwnerRepository, CatOwnerRepository catOwnerRepository, DogServiceImpl dogService, CatServiceImpl catService, MappingUtils mappingUtils) {
        this.dogOwnerRepository = dogOwnerRepository;
        this.catOwnerRepository = catOwnerRepository;
        this.catService = catService;
        this.dogService = dogService;
        this.mappingUtils = mappingUtils;
    }

    public void createOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber, Long SHELTER_ID, Pet pet) {
        List<Dog> dogs = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        if (SHELTER_ID == 1) {
            Dog dog = (Dog) dogService.getPet(pet.getId());
            dogs.add(dog);
            DogOwner dogOwner = new DogOwner(id, chatId, firstName, lastName, phoneNumber, dogs, ReportService.reportingPeriod);
            dogOwnerRepository.save(dogOwner);
        } else {
            Cat cat = (Cat) catService.getPet(pet.getId());
            cats.add(cat);
            CatOwner catOwner = new CatOwner(id, chatId, firstName, lastName, phoneNumber, cats,
                    ReportService.reportingPeriod);
            catOwnerRepository.save(catOwner);
        }
    }

    public OwnerDto getOwner(Long id, Long shelterId) {
        if (shelterId == 1) {
            return mappingUtils.mapToOwnerDto(dogOwnerRepository.getReferenceById(id));
        } else {
            return mappingUtils.mapToOwnerDto(catOwnerRepository.getReferenceById(id));
        }
    }

    public void updateOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                            Long SHELTER_ID, LocalDateTime probation) {
        if (SHELTER_ID == 1) {
            try {
                DogOwner toUpdate = dogOwnerRepository.getReferenceById(id);
                toUpdate.setChatId(chatId);
                toUpdate.setFirstName(firstName);
                toUpdate.setLastName(lastName);
                toUpdate.setPhoneNumber(phoneNumber);
                toUpdate.setProbation(probation);
                dogOwnerRepository.saveAndFlush(toUpdate);
            } catch (RuntimeException e) {
                throw new OwnerNotFoundException("Владельца с таким id нет!");
            }
        } else {
            try {
                CatOwner toUpdate = catOwnerRepository.getReferenceById(id);
                toUpdate.setChatId(chatId);
                toUpdate.setFirstName(firstName);
                toUpdate.setLastName(lastName);
                toUpdate.setPhoneNumber(phoneNumber);
                toUpdate.setProbation(probation);
                catOwnerRepository.saveAndFlush(toUpdate);
            } catch (RuntimeException e) {
                throw new OwnerNotFoundException("Владельца с таким id нет!");
            }
        }
    }

    public void deleteOwner(Long id, Long shelterId) {
        if (shelterId == 1) {
            dogOwnerRepository.deleteById(id);
        } else {
            catOwnerRepository.deleteById(id);
        }
    }
}