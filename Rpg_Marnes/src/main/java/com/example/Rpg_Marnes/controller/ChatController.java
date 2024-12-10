package com.example.Rpg_Marnes.controller;

import com.example.Rpg_Marnes.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping
    public List<String> getChat() {
        return chatService.ExibirChat();
    }
    @PutMapping
        public void AdicionarMensagem(@RequestParam String message){
            chatService.AdicionarMensagem(message);
        };

    @PutMapping("/clear")
    public void LimparChat(){
        chatService.LimparChat();
    }
    }




