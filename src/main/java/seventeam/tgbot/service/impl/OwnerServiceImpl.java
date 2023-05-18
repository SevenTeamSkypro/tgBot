package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.model.*;
import seventeam.tgbot.repository.CatOwnerRepository;
import seventeam.tgbot.repository.DogOwnerRepository;
import seventeam.tgbot.service.ReportService;
import seventeam.tgbot.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServiceImpl implements UserService {
    private final DogOwnerRepository dogOwnerRepository;
    private final CatOwnerRepository catOwnerRepository;
    private final DogServiceImpl dogService;
    private final CatServiceImpl catService;

    public OwnerServiceImpl(DogOwnerRepository dogOwnerRepository, CatOwnerRepository catOwnerRepository, DogServiceImpl dogService, CatServiceImpl catService) {
        this.dogOwnerRepository = dogOwnerRepository;
        this.catOwnerRepository = catOwnerRepository;
        this.catService = catService;
        this.dogService = dogService;
    }

    public User createOwner(Long id, String firstName, String lastName, String phoneNumber, Long SHELTER_ID,
                            Pet pet) {
        List<Dog> dogs = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        if (SHELTER_ID == 1) {
            Dog dog = (Dog) dogService.getPet(pet.getId());
            dogs.add(dog);
            return dogOwnerRepository.save(new DogOwner(id, firstName, lastName, phoneNumber, dogs,
                    ReportService.reportingPeriod));
        } else {
            Cat cat = (Cat) catService.getPet(pet.getId());
            cats.add(cat);
            return catOwnerRepository.save(new CatOwner(id, firstName, lastName, phoneNumber, cats,
                    ReportService.reportingPeriod));
        }
    }

    @Override
    public User createUser(Long id, String firstName, String lastName, String phoneNumber) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return catOwnerRepository.getReferenceById(id);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {
    }
}
