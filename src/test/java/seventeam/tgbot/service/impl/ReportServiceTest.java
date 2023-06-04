package seventeam.tgbot.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import seventeam.tgbot.model.Report;
import seventeam.tgbot.repository.ReportRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    @InjectMocks
    private ReportService reportService;
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private Report report;

    @Test
    @DisplayName("Проверка создания отчёта")
    void createReport() {
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        verify(reportRepository, verificationData -> reportService.createReport(update)).saveAndFlush(report);
    }

    @Test
    @DisplayName("Проверка получения всех отчётов")
    void getAll() {
        when(reportRepository.findAll()).thenReturn(List.of(report));
        assertEquals(List.of(report), reportService.getAll());
    }
}