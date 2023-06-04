package seventeam.tgbot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.service.impl.ClientServiceImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientServiceImpl clientService;

    Client client = new Client(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");

    @Test
    void getClient() throws Exception {
        when(clientService.getUserByChatId(0L)).thenReturn(client);
        mockMvc.perform(get("/c/get").param("chatId", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0L));
        verify(clientService).getUserByChatId(0L);
    }

    @Test
    void getAll() {
    }

    @Test
    void updateClient() {
    }

    @Test
    void deleteClient() {
    }
}