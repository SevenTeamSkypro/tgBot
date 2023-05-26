package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.exceptions.PetNotFoundException;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.model.Pet;
import seventeam.tgbot.repository.ShelterCatRepository;
import seventeam.tgbot.service.PetService;
import seventeam.tgbot.utils.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatServiceImpl implements PetService {
    private final ShelterCatRepository shelterCatRepository;
    private final MappingUtils mappingUtils;
    private List<Cat> cats = new ArrayList<>();
    public CatServiceImpl(ShelterCatRepository shelterCatRepository, MappingUtils mappingUtils) {
        this.shelterCatRepository = shelterCatRepository;
        this.mappingUtils = mappingUtils;
    }

    public void createCat(Long id, String name, String breed, Integer age, String suit, String gender) {
        CatDto dto = new CatDto(id, name, breed, age, suit, gender);
        Cat cat = mappingUtils.mapToCat(dto);
        cats = shelterCatRepository.findAll();
        if (!cats.contains(cat)) {
            cats.add(Math.toIntExact(id), cat);
            shelterCatRepository.saveAndFlush(cat);
        }
    }

    @Override
    public List<CatDto> getAllPets() {
        cats = shelterCatRepository.findAll();
        return cats.stream().map(mappingUtils::mapToCatDto).collect(Collectors.toList());
    }

    @Override
    public Pet getPet(Long id) {
        return mappingUtils.mapToCatDto(shelterCatRepository.getReferenceById(id));
    }

    @Override
    public void update(Pet pet) {
        try {
            Cat toUpdate = shelterCatRepository.getReferenceById(pet.getId());
            toUpdate.setAge(pet.getAge());
            shelterCatRepository.saveAndFlush(toUpdate);
        } catch (Exception e) {
            throw new PetNotFoundException();
        }

    }

    @Override
    public void deletePet(Long id) {
        shelterCatRepository.deleteById(id);
    }
}
