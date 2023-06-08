package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.OwnerCatDto;
import seventeam.tgbot.dto.OwnerDogDto;
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
    private final ClientServiceImpl clientService;
    private final MappingUtils mappingUtils;

    public OwnerService(DogOwnerRepository dogOwnerRepository, CatOwnerRepository catOwnerRepository, DogServiceImpl dogService, CatServiceImpl catService, ClientServiceImpl clientService, MappingUtils mappingUtils) {
        this.dogOwnerRepository = dogOwnerRepository;
        this.catOwnerRepository = catOwnerRepository;
        this.catService = catService;
        this.dogService = dogService;
        this.clientService = clientService;
        this.mappingUtils = mappingUtils;
    }

    public DogOwner createDogOwner(Long clientChatId, Long dogId) {
        List<Dog> dogs = new ArrayList<>();
        Dog dog = mappingUtils.mapToDog(dogService.getDog(dogId));
        Client client = clientService.getClientByChatId(clientChatId);
        dogs.add(dog);
        DogOwner dogOwner = new DogOwner(client.getId(), client.getChatId(), client.getFirstName(),
                client.getLastName(), client.getPhoneNumber(), dogs, ReportService.reportingPeriod);
        dog.setDogOwner(dogOwner);
        dogOwnerRepository.saveAndFlush(dogOwner);
        dogService.deleteDog(dogId);
        return dogOwner;
    }

    public CatOwner createCatOwner(Long clientChatId, Long catId) {
        List<Cat> cats = new ArrayList<>();
        Cat cat = mappingUtils.mapToCat(catService.getCat(catId));
        Client client = clientService.getClientByChatId(clientChatId);
        cats.add(cat);
        CatOwner catOwner = new CatOwner(client.getId(), client.getChatId(), client.getFirstName(),
                client.getLastName(), client.getPhoneNumber(), cats, ReportService.reportingPeriod);
        cat.setCatOwner(catOwner);
        catOwnerRepository.saveAndFlush(catOwner);
        catService.deleteCat(catId);
        return catOwner;
    }

    public OwnerDogDto getDogOwner(Long id) {
        try {
            return mappingUtils.mapToDogOwnerDto(dogOwnerRepository.getReferenceById(id));
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public OwnerCatDto getCatOwner(Long id) {
        try {
            return mappingUtils.mapToCatOwnerDto(catOwnerRepository.getReferenceById(id));
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public DogOwner updateDogOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                                   LocalDateTime probation) {
        try {
            DogOwner toUpdate = dogOwnerRepository.getReferenceById(id);
            toUpdate.setChatId(chatId);
            toUpdate.setFirstName(firstName);
            toUpdate.setLastName(lastName);
            toUpdate.setPhoneNumber(phoneNumber);
            toUpdate.setProbation(probation);
            dogOwnerRepository.saveAndFlush(toUpdate);
            return toUpdate;
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }
public CatOwner updateCatOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                                   LocalDateTime probation) {
        try {
            CatOwner toUpdate = catOwnerRepository.getReferenceById(id);
            toUpdate.setChatId(chatId);
            toUpdate.setFirstName(firstName);
            toUpdate.setLastName(lastName);
            toUpdate.setPhoneNumber(phoneNumber);
            toUpdate.setProbation(probation);
            catOwnerRepository.saveAndFlush(toUpdate);
            return toUpdate;
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public void deleteDogOwner(Long id) {
        try {
            dogOwnerRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public void deleteCatOwner(Long id) {
        try {
            catOwnerRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }
}