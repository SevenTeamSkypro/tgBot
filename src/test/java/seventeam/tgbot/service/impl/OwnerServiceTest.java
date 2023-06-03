package seventeam.tgbot.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seventeam.tgbot.dto.OwnerDto;
import seventeam.tgbot.model.Cat;
import seventeam.tgbot.model.CatOwner;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.model.DogOwner;
import seventeam.tgbot.repository.CatOwnerRepository;
import seventeam.tgbot.repository.DogOwnerRepository;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {
    @Mock
    private DogOwnerRepository dogOwnerRepository;
    @Mock
    private CatOwnerRepository catOwnerRepository;
    @Mock
    private DogServiceImpl dogService;
    @Mock
    private CatServiceImpl catService;
    @InjectMocks
    private OwnerService ownerService;
    @Mock
    private MappingUtils mappingUtils;
    @Mock
    Dog dog = new Dog("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    @Mock
    Cat cat = new Cat("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    @Mock
    DogOwner dogOwner = new DogOwner(0L, 0L, "firstName", "lastName", "7_xxx_xxx_xx_xx", List.of(dog), LocalDateTime.now());
    @Mock
    OwnerDto ownerDto = new OwnerDto(0L, 0L, "firstName", "lastName", "7_xxx_xxx_xx_xx", List.of(dog), LocalDateTime.now());
    @Mock
    CatOwner catOwner = new CatOwner(0L, 0L, "firstName", "lastName", "7_xxx_xxx_xx_xx", List.of(cat), LocalDateTime.now());

    @Test
    @DisplayName("Проверка создания владельца")
    void createOwner() {
        verify(dogOwnerRepository, verificationData -> ownerService.createOwner(dogOwner.getId(), dogOwner.getChatId(),
                dogOwner.getFirstName(), dogOwner.getLastName(), dogOwner.getPhoneNumber(), 1L, dog)).saveAndFlush(dogOwner);
        verify(catOwnerRepository, verificationData -> ownerService.createOwner(catOwner.getId(), catOwner.getChatId(),
                catOwner.getFirstName(), catOwner.getLastName(), catOwner.getPhoneNumber(), 2L, cat)).saveAndFlush(catOwner);
    }

    @Test
    @DisplayName("Проверка получения владельца")
    void getOwner() {
        when(dogOwnerRepository.getReferenceById(0L)).thenReturn(dogOwner);
        when(mappingUtils.mapToOwnerDto(dogOwner)).thenReturn(ownerDto);
        assertEquals(ownerDto, ownerService.getOwner(0L, 1L));
        when(catOwnerRepository.getReferenceById(0L)).thenReturn(catOwner);
        when(mappingUtils.mapToOwnerDto(catOwner)).thenReturn(ownerDto);
        assertEquals(ownerDto, ownerService.getOwner(0L, 2L));
    }

    @Test
    @DisplayName("Проверка обновления владельца")
    void updateOwner() {
        when(dogOwnerRepository.getReferenceById(0L)).thenReturn(dogOwner);
        verify(dogOwnerRepository, verificationData -> ownerService.updateOwner(dogOwner.getId(), dogOwner.getChatId(),
                dogOwner.getFirstName(), dogOwner.getLastName(), dogOwner.getPhoneNumber(), 1L,
                dogOwner.getProbation())).saveAndFlush(dogOwner);
        when(catOwnerRepository.getReferenceById(0L)).thenReturn(catOwner);
        verify(catOwnerRepository, verificationData -> ownerService.updateOwner(catOwner.getId(), catOwner.getChatId(),
                catOwner.getFirstName(), catOwner.getLastName(), catOwner.getPhoneNumber(), 2L,
                catOwner.getProbation())).saveAndFlush(catOwner);
    }

    @Test
    @DisplayName("Проверка удаления владельца")
    void deleteOwner() {
        verify(dogOwnerRepository, verificationData -> ownerService.deleteOwner(0L, 1L)).deleteById(0L);
        verify(catOwnerRepository, verificationData -> ownerService.deleteOwner(0L, 2L)).deleteById(0L);
    }
}