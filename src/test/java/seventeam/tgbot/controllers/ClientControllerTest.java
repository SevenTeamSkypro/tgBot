package seventeam.tgbot.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.service.impl.ClientServiceImpl;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @DisplayName("Проверка получения клиента по chatId")
    void getClient() throws Exception {
        when(clientService.getUserByChatId(0L)).thenReturn(client);
        mockMvc.perform(get("/c/get").param("chatId", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0L));
        verify(clientService).getUserByChatId(0L);
    }

    @Test
    @DisplayName("Проверка получения всех клиентов")
    void getAll() throws Exception {
        when(clientService.getAll()).thenReturn(List.of(client));
        mockMvc.perform(get("/c/all"))
                .andExpect(status().isOk());
        verify(clientService).getAll();
    }

    @Test
    @DisplayName("Проверка обновления клиента")
    void updateClient() throws Exception {
        when(clientService.getUserByChatId(0L)).thenReturn(client);
        mockMvc.perform(put("/c/put")
                        .param("id", "0")
                        .param("chatId", "0")
                        .param("firstName", "FirstName")
                        .param("lastName", "LastName")
                        .param("phoneNumber", "7_xxx_xxx_xx_xx"))
                .andExpect(status().isOk());
        verify(clientService).updateUser(client);
    }

    @Test
    @DisplayName("Проверка удаления клиента")
    void deleteClient() throws Exception {
        mockMvc.perform(delete("/c/del")
                        .param("id", "0"))
                .andExpect(status().isOk());
        verify(clientService).deleteUser(0L);
    }
}