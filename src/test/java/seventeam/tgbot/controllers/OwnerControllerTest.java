package seventeam.tgbot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.model.Dog;
import seventeam.tgbot.model.DogOwner;
import seventeam.tgbot.service.impl.DogServiceImpl;
import seventeam.tgbot.service.impl.OwnerService;
import seventeam.tgbot.utils.MappingUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OwnerService ownerService;
    @MockBean
    private DogServiceImpl dogService;
    @MockBean
    private MappingUtils mappingUtils;

    Dog dog = new Dog(0L, "Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    Client client = new Client(0L, 0L,"FirstName", "LastName", "7_xxx_xxx_xx_xx");
    DogOwner dogOwner = new DogOwner(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx", List.of(dog), LocalDateTime.now());
    @Test
    void createDogOwner() throws Exception {
        when(dogService.getDog(0L)).thenReturn(mappingUtils.mapToDogDto(dog));
        when(ownerService.createDogOwner(client.getChatId(), dog.getId())).thenReturn(dogOwner);
        mockMvc.perform(post("/dogOwner/new"))
                .andExpect(status().isOk());
        verify(ownerService).createDogOwner(client.getChatId(), dog.getId());
    }

    @Test
    void getOwner() {
    }

    @Test
    void updateOwner() {
    }

    @Test
    void deleteOwner() {
    }
}