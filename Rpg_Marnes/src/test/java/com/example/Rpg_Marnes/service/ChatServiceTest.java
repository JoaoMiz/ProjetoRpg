package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.model.Chat;
import com.example.Rpg_Marnes.repository.ChatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    @InjectMocks
    private ChatService chatService;

    @Mock
    private ChatRepository chatRepository;

    private final Long CHAT_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChat_WhenChatExists() {
        Chat chat = new Chat();
        chat.setId(CHAT_ID);
        when(chatRepository.findById(CHAT_ID)).thenReturn(Optional.of(chat));

        Chat retrievedChat = chatService.getChat();

        assertNotNull(retrievedChat, "O chat não deve ser nulo.");
        assertEquals(CHAT_ID, retrievedChat.getId(), "O ID do chat deve ser igual ao esperado.");
        verify(chatRepository, times(1)).findById(CHAT_ID);
    }

    @Test
    void testGetChat_WhenChatDoesNotExist() {
        when(chatRepository.findById(CHAT_ID)).thenReturn(Optional.empty());
        when(chatRepository.save(any(Chat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Chat retrievedChat = chatService.getChat();

        assertNotNull(retrievedChat, "O chat não deve ser nulo.");
        assertEquals(CHAT_ID, retrievedChat.getId(), "O ID do chat deve ser igual ao esperado.");
        verify(chatRepository, times(1)).findById(CHAT_ID);
        verify(chatRepository, times(1)).save(any(Chat.class));
    }

    @Test
    void testExibirChat() {
        Chat chat = new Chat();
        chat.setId(CHAT_ID);
        chat.setMessages(new ArrayList<>(List.of("Mensagem 1", "Mensagem 2")));
        when(chatRepository.findById(CHAT_ID)).thenReturn(Optional.of(chat));

        List<String> messages = chatService.ExibirChat();

        assertNotNull(messages, "A lista de mensagens não deve ser nula.");
        assertEquals(2, messages.size(), "O número de mensagens deve ser igual ao esperado.");
        assertTrue(messages.contains("Mensagem 1"), "A mensagem deve estar presente no chat.");
        verify(chatRepository, times(1)).findById(CHAT_ID);
    }

    @Test
    void testAdicionarMensagem() {
        Chat chat = new Chat();
        chat.setId(CHAT_ID);
        chat.setMessages(new ArrayList<>());
        when(chatRepository.findById(CHAT_ID)).thenReturn(Optional.of(chat));
        when(chatRepository.save(any(Chat.class))).thenReturn(chat);

        chatService.AdicionarMensagem("Nova mensagem");

        assertEquals(1, chat.getMessages().size(), "O número de mensagens deve ser atualizado.");
        assertTrue(chat.getMessages().contains("Nova mensagem"), "A nova mensagem deve ser adicionada ao chat.");
        verify(chatRepository, times(1)).findById(CHAT_ID);
        verify(chatRepository, times(1)).save(chat);
    }

    @Test
    void testLimparChat() {
        Chat chat = new Chat();
        chat.setId(CHAT_ID);
        chat.setMessages(new ArrayList<>(List.of("Mensagem 1", "Mensagem 2")));
        when(chatRepository.findById(CHAT_ID)).thenReturn(Optional.of(chat));
        when(chatRepository.save(any(Chat.class))).thenReturn(chat);

        chatService.LimparChat();

        assertTrue(chat.getMessages().isEmpty(), "As mensagens do chat devem ser limpas.");
        verify(chatRepository, times(1)).findById(CHAT_ID);
        verify(chatRepository, times(1)).save(chat);
    }
}
