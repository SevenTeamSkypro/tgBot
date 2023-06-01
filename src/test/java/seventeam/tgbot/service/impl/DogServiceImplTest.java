package seventeam.tgbot.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.repository.ShelterDogRepository;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {
    @InjectMocks
    private DogServiceImpl dogService;
    @Mock
    private ShelterDogRepository shelterDogRepository;
    @Mock
    MappingUtils mappingUtils;

    @Test
    @DisplayName("Проверка получения всех питомцев")
    void getAllPets() {
        List<DogDto> dtoList = new ArrayList<>();
        List<Dog> list = new ArrayList<>();
        DogDto dogDto = new DogDto("Лео", "такса", LocalDate.of(2016, 10, 25), "леопард", "кобель");
        Dog dog = new Dog("Лео", "такса", LocalDate.of(2016, 10, 25), "леопард", "кобель");
        dtoList.add(0, dogDto);
        list.add(0, dog);
        when(mappingUtils.mapToDogDto(dog)).thenReturn(dogDto);
        when(shelterDogRepository.findAll()).thenReturn(list);
        assertEquals(dtoList, dogService.getAllPets());
    }

    @Test
    @DisplayName("Проверка получения питомца по id")
    void getPet() {
        DogDto dogDto = new DogDto("Лео", "такса", LocalDate.of(2016, 10, 25), "леопард", "кобель");
        Dog dog = new Dog("Лео", "такса", LocalDate.of(2016, 10, 25), "леопард", "кобель");
        when(mappingUtils.mapToDogDto(dog)).thenReturn(dogDto);
        when(shelterDogRepository.getReferenceById(0L)).thenReturn(dog);
        assertEquals(dogDto, dogService.getPet(0L));
    }
}