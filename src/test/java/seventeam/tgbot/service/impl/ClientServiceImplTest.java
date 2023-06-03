package seventeam.tgbot.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import seventeam.tgbot.exceptions.ClientNotFoundException;
import seventeam.tgbot.model.Client;
import seventeam.tgbot.model.Volunteer;
import seventeam.tgbot.repository.ClientRepository;
import seventeam.tgbot.repository.VolunteerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @InjectMocks
    private VolunteerService volunteerService;
    @InjectMocks
    private ClientServiceImpl clientService;
    @Mock
    private VolunteerRepository volunteerRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private TelegramBot telegramBot;
    @Mock
    Client client = new Client(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");
    @Mock
    Volunteer volunteer = new Volunteer(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");

    @BeforeEach
    public void init() {
        telegramBot = Mockito.mock(TelegramBot.class);
        clientService = new ClientServiceImpl(clientRepository, telegramBot, volunteerService);
    }

    @Test
    @DisplayName("Проверка создания клиента")
    void createUser() {
        verify(clientRepository, verificationData -> clientService.createUser(client.getId(), client.getChatId(),
                client.getFirstName(), client.getLastName(), client.getPhoneNumber())).saveAndFlush(client);
    }

    @Test
    @DisplayName("Проверка получения клиента по chatId")
    void getUserByChatId() {
        when(clientRepository.getByChatId(0L)).thenReturn(client);
        assertEquals(client, clientService.getUserByChatId(0L));
    }

    @Test
    @DisplayName("Проверка получения всех клиентов")
    void getAll() {
        when(clientRepository.findAll()).thenReturn(List.of(client));
        assertEquals(List.of(client), clientService.getAll());
    }

    @Test
    @DisplayName("Проверка обновления клиента")
    void updateUser() {
        when(clientRepository.getReferenceById(0L)).thenReturn(client);
        verify(clientRepository, verificationData -> clientService.updateUser(client)).saveAndFlush(client);
    }

    @Test
    @DisplayName("Проверка удаления клиента")
    void deleteUser() {
        verify(clientRepository, verificationData -> clientService.deleteUser(0L)).deleteById(0L);
    }

    @Test
    @DisplayName("Проверка исключения при получении несуществующего клиента")
    public void getClientNotFoundExceptionTest() {
        when(clientRepository.getByChatId(0L)).thenThrow(ClientNotFoundException.class);
        assertThrows(ClientNotFoundException.class,
                () -> when(clientService.getUserByChatId(0L)).thenThrow(ClientNotFoundException.class));
    }

    @Test
    @DisplayName("Проверка считывания из файла")
    void readFile() {
        assertEquals("test", clientService.readFile("src/main/resources/draw/test.txt"));
    }
}