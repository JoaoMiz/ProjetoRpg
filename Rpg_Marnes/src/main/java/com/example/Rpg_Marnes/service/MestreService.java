package com.example.Rpg_Marnes.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.model.Mestre;
import com.example.Rpg_Marnes.model.Monstro;
import com.example.Rpg_Marnes.model.Npc;
import com.example.Rpg_Marnes.repository.FichaRepository;
import com.example.Rpg_Marnes.repository.MestreRepository;
import com.example.Rpg_Marnes.repository.MonstroRepository;
import com.example.Rpg_Marnes.repository.NpcRepository;

import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;

@Service
public class MestreService {

    private MonstroRepository monstroRepository;
    private NpcRepository npcRepository;
    private MestreRepository mestreRepository;
    private FichaRepository fichaRepository;
    public Mestre mestre;

    
    public int rolarDados(int numLados, int qntDados, int bonus) {
        int resultado = bonus;
        for (int i = 0; i < qntDados; i++) {
            resultado += (int) (Math.random() * numLados) + 1; // Gera números entre 1 e numLados
        }

        return resultado;
    }
      

    public void MonstroService(MonstroRepository monstroRepository) {
        this.monstroRepository = monstroRepository;
    }

    public Monstro criarMonstro(Monstro monstro) {
        return monstroRepository.save(monstro);
    }
    public Npc criarNpc(Npc npc) {
        return npcRepository.save(npc);
    }

    
    @Autowired
    public MestreService(FichaRepository fichaRepository) {
        this.fichaRepository = fichaRepository;
    }

    public List<Ficha> getTodasFichas() {
        return fichaRepository.findAll();
    }

    public void zerarIniciativas() {
        List<Ficha> fichas = fichaRepository.findAll();
        for (Ficha ficha : fichas) {
            ficha.setIniciativa(0);
        }
        fichaRepository.saveAll(fichas);
    }

    
}

