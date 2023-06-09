package seventeam.tgbot.services;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.CatOwnerDto;
import seventeam.tgbot.dto.DogOwnerDto;
import seventeam.tgbot.exceptions.OwnerNotFoundException;
import seventeam.tgbot.model.*;
import seventeam.tgbot.repositories.CatOwnerRepository;
import seventeam.tgbot.repositories.DogOwnerRepository;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    private final DogOwnerRepository dogOwnerRepository;
    private final CatOwnerRepository catOwnerRepository;
    private final DogService dogService;
    private final CatService catService;
    private final ClientService clientService;
    private final MappingUtils mappingUtils;

    public OwnerService(DogOwnerRepository dogOwnerRepository, CatOwnerRepository catOwnerRepository, DogService dogService, CatService catService, ClientService clientService, MappingUtils mappingUtils) {
        this.dogOwnerRepository = dogOwnerRepository;
        this.catOwnerRepository = catOwnerRepository;
        this.catService = catService;
        this.dogService = dogService;
        this.clientService = clientService;
        this.mappingUtils = mappingUtils;
    }

    public void createDogOwner(Long clientChatId, Long dogId) {
        List<Dog> dogs = new ArrayList<>();
        Dog dog = mappingUtils.mapToDog(dogService.getDog(dogId));
        Client client = clientService.getClientByChatId(clientChatId);
        dogs.add(dog);
        DogOwner dogOwner = new DogOwner(client.getId(), client.getChatId(), client.getFirstName(),
                client.getLastName(), client.getPhoneNumber(), dogs, ReportService.reportingPeriod);
        dog.setDogOwner(dogOwner);
        dogOwnerRepository.saveAndFlush(dogOwner);
        dogService.deleteDog(dogId);
        clientService.deleteClient(client.getId());
    }

    public void createCatOwner(Long clientChatId, Long catId) {
        List<Cat> cats = new ArrayList<>();
        Cat cat = mappingUtils.mapToCat(catService.getCat(catId));
        Client client = clientService.getClientByChatId(clientChatId);
        cats.add(cat);
        CatOwner catOwner = new CatOwner(client.getId(), client.getChatId(), client.getFirstName(),
                client.getLastName(), client.getPhoneNumber(), cats, ReportService.reportingPeriod);
        cat.setCatOwner(catOwner);
        catOwnerRepository.saveAndFlush(catOwner);
        catService.deleteCat(catId);
        clientService.deleteClient(client.getId());
    }

    public DogOwnerDto getDogOwner(Long id) {
        try {
            return mappingUtils.mapToDogOwnerDto(dogOwnerRepository.getReferenceById(id));
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public CatOwnerDto getCatOwner(Long id) {
        try {
            return mappingUtils.mapToCatOwnerDto(catOwnerRepository.getReferenceById(id));
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public void updateDogOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                               LocalDateTime probation) {
        try {
            DogOwner toUpdate = dogOwnerRepository.getReferenceById(id);
            toUpdate.setChatId(chatId);
            toUpdate.setFirstName(firstName);
            toUpdate.setLastName(lastName);
            toUpdate.setPhoneNumber(phoneNumber);
            toUpdate.setProbation(probation);
            dogOwnerRepository.saveAndFlush(toUpdate);
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public void updateCatOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                               LocalDateTime probation) {
        try {
            CatOwner toUpdate = catOwnerRepository.getReferenceById(id);
            toUpdate.setChatId(chatId);
            toUpdate.setFirstName(firstName);
            toUpdate.setLastName(lastName);
            toUpdate.setPhoneNumber(phoneNumber);
            toUpdate.setProbation(probation);
            catOwnerRepository.saveAndFlush(toUpdate);
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