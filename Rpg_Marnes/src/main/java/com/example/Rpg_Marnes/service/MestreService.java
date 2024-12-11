package com.example.Rpg_Marnes.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.model.Monstro;
import com.example.Rpg_Marnes.model.Npc;
import com.example.Rpg_Marnes.repository.FichaRepository;
import com.example.Rpg_Marnes.repository.MonstroRepository;
import com.example.Rpg_Marnes.repository.NpcRepository;



@Service
public class MestreService {

    private MonstroRepository monstroRepository;
    private NpcRepository npcRepository;
    private FichaRepository fichaRepository;
    @Autowired
    private ChatService chatService;

    // Construtor com injeção de dependências
    @Autowired
    public MestreService(MonstroRepository monstroRepository, NpcRepository npcRepository, FichaRepository fichaRepository) {
        this.monstroRepository = monstroRepository;
        this.npcRepository = npcRepository;
        this.fichaRepository = fichaRepository;
    }

    public int rolarDados(int numLados, int qntDados, int bonus) {
        int resultado = bonus;
        StringBuilder mensagem = new StringBuilder("Rolagem de Dados: ");
        
        for (int i = 0; i < qntDados; i++) {
            int roll = (int) (Math.random() * numLados) + 1; // Gera um número entre 1 e numLados
            resultado += roll;
            mensagem.append("D" + numLados + " = " + roll + " ");
        }
        
        
        mensagem.append("+ Bônus: " + bonus + " = Total: " + resultado);
        
    
        chatService.AdicionarMensagem(mensagem.toString());

        return resultado;
    }

    public Monstro criarMonstro(Monstro monstro) {
        return monstroRepository.save(monstro);
    }

    public Npc criarNpc(Npc npc) {
        return npcRepository.save(npc);
    }

    public List<Ficha> getTodasFichas() {
        return fichaRepository.findAll();
    }

    public void zerarIniciativas() {
        List<Ficha> fichas = fichaRepository.findAll();

   
    if (fichas.isEmpty()) {
        System.out.println("Nenhuma ficha encontrada.");
        return;  
    }

    for (Ficha ficha : fichas) {
        ficha.setIniciativa(0);
    }

    
    fichaRepository.saveAll(fichas);
    System.out.println("Iniciativas zeradas para todas as fichas.");
}
}

