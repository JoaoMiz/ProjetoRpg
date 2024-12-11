package com.example.Rpg_Marnes.controller;

import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.model.Mestre;
import com.example.Rpg_Marnes.model.Monstro;
import com.example.Rpg_Marnes.model.Npc;
import com.example.Rpg_Marnes.repository.MonstroRepository;
import com.example.Rpg_Marnes.service.MestreService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mestre")
public class MestreController {

    private final MestreService mestreService;
    
    
    @Autowired
    public MestreController(MestreService mestreService) {
        this.mestreService = mestreService;
    }
    


    @PostMapping("/rolagem")
    public int rolarDados(
            @RequestParam int numLados,@RequestParam int qntDados,@RequestParam int bonus
    ) 
    {
        return mestreService.rolarDados(numLados, qntDados, bonus);
    }
    @PostMapping("/criar-monstro")
    public ResponseEntity<Monstro> criarMonstro(@RequestBody Monstro monstro) {
        Monstro novoMonstro = mestreService.criarMonstro(monstro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMonstro);
    }
    @PostMapping("/criar-npc")
    public ResponseEntity<Npc> criarNpc(@RequestBody Npc npc) {
        Npc novoNpc = mestreService.criarNpc(npc);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoNpc);
    }
    @GetMapping("/fichas")
    public List<Ficha> getFichas() {
        return mestreService.getTodasFichas();
    }

    @PostMapping("/zerar-iniciativas")
    public ResponseEntity<Void> zerarIniciativas() {
        mestreService.zerarIniciativas();
        return ResponseEntity.ok().build();
    }

}
