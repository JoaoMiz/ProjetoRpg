package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.model.Chat;
import com.example.Rpg_Marnes.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    private final Long CHAT_ID = 1L;

    public Chat getChat() {
        return chatRepository.findById(CHAT_ID).orElseGet(() -> {
            Chat chat = new Chat();
            chat.setId(CHAT_ID);
            chatRepository.save(chat);
            return chat;
        });
    }

    public List<String> ExibirChat() {
        return getChat().getMessages();
    }

    public void AdicionarMensagem(String message) {
        Chat chat = getChat();
        chat.getMessages().add(message);
        chatRepository.save(chat);
    }

    public void LimparChat() {
        Chat chat = getChat();
        chat.getMessages().clear();
        chatRepository.save(chat);
    }
}
