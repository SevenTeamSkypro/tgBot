package seventeam.tgbot.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seventeam.tgbot.dto.DogDto;
import seventeam.tgbot.exceptions.PetNotFoundException;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.repository.ShelterDogRepository;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {
    @InjectMocks
    private DogServiceImpl dogService;
    @Mock
    private ShelterDogRepository shelterDogRepository;
    @Mock
    private MappingUtils mappingUtils;
    @Mock
    Dog dog = new Dog("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    @Mock
    DogDto dogDto = new DogDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");

    @Test
    @DisplayName("Проверка создания питомца")
    void createDog() {
        verify(shelterDogRepository, verificationData -> dogService.createDog(dog.getName(), dog.getBreed(),
                dog.getDateOfBirth(), dog.getSuit(), dog.getGender())).saveAndFlush(dog);
    }

    @Test
    @DisplayName("Проверка исключения при получении несуществующего питомца")
    void getPetNotFoundExceptionTest() {
        when(dogService.getPet(0L)).thenThrow(PetNotFoundException.class);
        assertThrows(PetNotFoundException.class,
                () -> when(dogService.getPet(0L)).thenThrow(PetNotFoundException.class));
    }

    @Test
    @DisplayName("Проверка получения всех питомцев")
    void getAllPets() {
        when(mappingUtils.mapToDogDto(dog)).thenReturn(dogDto);
        when(shelterDogRepository.findAll()).thenReturn(List.of(dog));
        assertEquals(List.of(dogDto), dogService.getAllPets());
    }

    @Test
    @DisplayName("Проверка получения питомца по id")
    void getPet() {
        when(mappingUtils.mapToDogDto(dog)).thenReturn(dogDto);
        when(shelterDogRepository.getReferenceById(0L)).thenReturn(dog);
        assertEquals(dogDto, dogService.getPet(0L));
    }

    @Test
    @DisplayName("Проверка удаления питомца")
    void deletePet() {
        verify(shelterDogRepository, verificationData -> dogService.deletePet(0L)).deleteById(0L);
    }
}