package seventeam.tgbot.service.impl;

import org.springframework.stereotype.Service;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.exceptions.PetNotFoundException;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.repository.ShelterCatRepository;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatServiceImpl {
    private final ShelterCatRepository shelterCatRepository;
    private final MappingUtils mappingUtils;
    private List<Cat> cats = new ArrayList<>();

    public CatServiceImpl(ShelterCatRepository shelterCatRepository, MappingUtils mappingUtils) {
        this.shelterCatRepository = shelterCatRepository;
        this.mappingUtils = mappingUtils;
    }

    public CatDto createCat(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        CatDto dto = new CatDto(name, breed, dateOfBirth, suit, gender);
        Cat cat = mappingUtils.mapToCat(dto);
        cats = shelterCatRepository.findAll();
        if (!cats.contains(cat)) {
            shelterCatRepository.saveAndFlush(cat);
        }
        return dto;
    }

    public List<CatDto> getAllCats() {
        cats = shelterCatRepository.findAll();
        return cats.stream().map(mappingUtils::mapToCatDto).collect(Collectors.toList());
    }

    public CatDto getCat(Long id) {
        try {
            return mappingUtils.mapToCatDto(shelterCatRepository.getReferenceById(id));
        } catch (RuntimeException e) {
            throw new PetNotFoundException();
        }
    }

    public void deleteCat(Long id) {
        try {
            shelterCatRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new PetNotFoundException();
        }
    }
}