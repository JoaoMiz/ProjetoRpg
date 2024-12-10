package com.example.Rpg_Marnes.controller;


import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.model.Monstro;
import com.example.Rpg_Marnes.model.Npc;
import com.example.Rpg_Marnes.service.FichaService;
import org.apache.tomcat.util.modeler.modules.ModelerSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ficha")
@CrossOrigin
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @GetMapping("/npc")
    public List<Npc> getAllNpc(){return fichaService.findAllNpc();}

    @GetMapping("/monstro")
    public List<Monstro> getAllMonstro(){return fichaService.findAllMonstro();}

    @PutMapping("/rolagem/{numLados}/{qntDados}/{bonus}")
    public void rolagem(@PathVariable int numLados, @PathVariable int qntDados, @PathVariable int bonus){
        fichaService.rolagem(numLados,qntDados,bonus);
    }

    @PutMapping("/npc/{id}/iniciativa")
    public Npc setIniciativaNpc(@PathVariable Long id){return fichaService.npcIniciativa();}

    @PutMapping("/monstro/{id}/iniciativa")
    public Monstro setIniciativaMonstro(@PathVariable Long id){
        return fichaService.MonstroIniciativa();
    }

    @PutMapping("/{id}/invent")
    public Npc setInventario(@RequestBody String invent, @PathVariable Long id){
        return fichaService.adicionarItem(id,invent);
    }

    @DeleteMapping("monstro/{id}")
    public ResponseEntity<Void> apagarMonstro(@PathVariable Long id){
        fichaService.deletarMonstro(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("npc/{id}")
    public ResponseEntity<Void> apagarNpc(@PathVariable Long id) {
        fichaService.deletarNpc(id);
        return ResponseEntity.noContent().build();
    }
}
