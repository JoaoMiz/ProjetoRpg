package com.example.Rpg_Marnes.controller;


import com.example.Rpg_Marnes.model.Mestre;
import com.example.Rpg_Marnes.service.MestreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mestre")
public class MestreController {
    
    @Autowired
    public MestreController(MestreService mestreService) {
        this.mestreService = mestreService;
    }
    private MestreService mestreService;

    @PostMapping
    public ResponseEntity<Mestre> createFicha(@RequestBody Mestre mestre) {
        Mestre savedMestre = mestreService.saveMestre(mestre);
        return ResponseEntity.ok(savedMestre);
    }
    @PostMapping("/rolagem")
    public int rolarDados(
            @RequestParam int numLados, @RequestParam int qntDados, @RequestParam int bonus) {
        return mestreService.rolarDados(numLados, qntDados, bonus);
    }

}
