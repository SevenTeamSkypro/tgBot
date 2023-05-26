package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.model.*;
import seventeam.tgbot.repository.CatOwnerRepository;
import seventeam.tgbot.repository.DogOwnerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    private final DogOwnerRepository dogOwnerRepository;
    private final CatOwnerRepository catOwnerRepository;
    private final DogServiceImpl dogService;
    private final CatServiceImpl catService;

    public OwnerService(DogOwnerRepository dogOwnerRepository, CatOwnerRepository catOwnerRepository, DogServiceImpl dogService, CatServiceImpl catService) {
        this.dogOwnerRepository = dogOwnerRepository;
        this.catOwnerRepository = catOwnerRepository;
        this.catService = catService;
        this.dogService = dogService;
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
            CatOwner catOwner = new CatOwner(id, firstName, lastName, phoneNumber, cats, ReportService.reportingPeriod);
            catOwnerRepository.save(catOwner);
        }
    }

    public User getOwner(Long id, Long shelterId) {
        if (shelterId == 1) {
            return dogOwnerRepository.getReferenceById(id);
        } else
            return catOwnerRepository.getReferenceById(id);
    }


    public User updateOwner(User user, Long shelterId) {
        if (shelterId == 1) {
            DogOwner dogOwner = dogOwnerRepository.getReferenceById(user.getId());
            dogOwnerRepository.delete(dogOwner);
            return dogOwnerRepository.saveAndFlush((DogOwner) user);
        } else {
            CatOwner catOwner = catOwnerRepository.getReferenceById(user.getId());
            catOwnerRepository.delete(catOwner);
            return catOwnerRepository.saveAndFlush((CatOwner) user);
        }
    }

    public void deleteOwner(User user, Long shelterId) {
        if (shelterId == 1) {
            dogOwnerRepository.delete((DogOwner) user);
        } else {
            catOwnerRepository.delete((CatOwner) user);
        }
    }
}
