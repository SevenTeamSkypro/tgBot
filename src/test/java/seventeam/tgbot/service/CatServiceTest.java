package seventeam.tgbot.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seventeam.tgbot.dto.CatDto;
import seventeam.tgbot.exceptions.PetNotFoundException;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.repositories.ShelterCatRepository;
import seventeam.tgbot.services.CatService;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {
    @InjectMocks
    private CatService catService;
    @Mock
    private ShelterCatRepository shelterCatRepository;
    @Mock
    private MappingUtils mappingUtils;
    @Mock
    Cat cat = new Cat("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    @Mock
    CatDto catDto = new CatDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");

    @Test
    @DisplayName("Проверка создания питомца")
    void createCat() {
        verify(shelterCatRepository, verificationData -> catService.createCat(cat.getName(), cat.getBreed(),
                cat.getDateOfBirth(), cat.getSuit(), cat.getGender())).saveAndFlush(cat);
    }

    @Test
    @DisplayName("Проверка исключения при получении несуществующего питомца")
    void getPetNotFoundExceptionTest() {
        when(catService.getCat(0L)).thenThrow(PetNotFoundException.class);
        assertThrows(PetNotFoundException.class,
                () -> when(catService.getCat(0L)).thenThrow(PetNotFoundException.class));
    }

    @Test
    @DisplayName("Проверка получения всех питомцев")
    void getAllPetsTest() {
        when(mappingUtils.mapToCatDto(cat)).thenReturn(catDto);
        when(shelterCatRepository.findAll()).thenReturn(List.of(cat));
        assertEquals(List.of(catDto), catService.getAllCats());
    }

    @Test
    @DisplayName("Проверка получения питомца по id")
    void getPet() {
        when(mappingUtils.mapToCatDto(cat)).thenReturn(catDto);
        when(shelterCatRepository.getReferenceById(0L)).thenReturn(cat);
        assertEquals(catDto, catService.getCat(0L));
    }

    @Test
    @DisplayName("Проверка удаления питомца")
    void deletePet() {
        verify(shelterCatRepository, verificationData -> catService.deleteCat(0L)).deleteById(0L);
    }
}