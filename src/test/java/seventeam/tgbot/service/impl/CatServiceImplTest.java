package seventeam.tgbot.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.repository.ShelterCatRepository;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatServiceImplTest {
    @InjectMocks
    private CatServiceImpl catService;
    @Mock
    private ShelterCatRepository shelterCatRepository;
    @Mock
    private MappingUtils mappingUtils;

    @Test
    void createCat() {
    }

    @Test
    @DisplayName("Проверка получения всех питомцев")
    void getAllPetsTest() {
        List<CatDto> dtoList = new ArrayList<>();
        List<Cat> list = new ArrayList<>();
        CatDto catDto = new CatDto("Мурка", "русская", LocalDate.of(2022, 10, 25), "полосатая", "кошка");
        Cat cat = new Cat("Мурка", "русская", LocalDate.of(2022, 10, 25), "полосатая", "кошка");
        dtoList.add(0, catDto);
        list.add(0, cat);
        when(mappingUtils.mapToCatDto(cat)).thenReturn(catDto);
        when(shelterCatRepository.findAll()).thenReturn(list);
        assertEquals(dtoList, catService.getAllPets());
    }

    @Test
    @DisplayName("Проверка получения питомца по id")
    void getPet() {
        CatDto catDto = new CatDto("Мурка", "русская", LocalDate.of(2022, 10, 25), "полосатая", "кошка");
        Cat cat = new Cat("Мурка", "русская", LocalDate.of(2022, 10, 25), "полосатая", "кошка");
        when(mappingUtils.mapToCatDto(cat)).thenReturn(catDto);
        when(shelterCatRepository.getReferenceById(0L)).thenReturn(cat);
        assertEquals(catDto, catService.getPet(0L));
    }

    @Test
    void deletePet() {
    }
}