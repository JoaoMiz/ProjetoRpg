package com.example.Rpg_Marnes.controller;


import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.service.FichaService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ficha")
@CrossOrigin
public class FichaController {

   @Autowired
   private FichaService fichaService;


    @Autowired
    public FichaController(FichaService fichaService) {
        this.fichaService = fichaService;
    }

    // Criar ou atualizar uma ficha
    @PostMapping
    public ResponseEntity<Ficha> createFicha(@RequestBody Ficha ficha) {
        Ficha savedFicha = fichaService.saveFicha(ficha);
        return ResponseEntity.ok(savedFicha);
    }

    // Obter todas as fichas
    @GetMapping
    public ResponseEntity<List<Ficha>> getAllFichas() {
        List<Ficha> fichas = fichaService.getAllFichas();
        return ResponseEntity.ok(fichas);
    }

    // Obter uma ficha por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ficha> getFichaById(@PathVariable Long id) {
        Optional<Ficha> ficha = fichaService.getFichaById(id);
        return ficha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar uma ficha por ID
    @DeleteMapping("/deletarFicha/{id}")
    public ResponseEntity<Void> deleteFicha(@PathVariable Long id) {
        fichaService.deleteFicha(id);
        return ResponseEntity.noContent().build();
    }

    // Atualizar uma ficha
    @PutMapping("/{id}")
    public ResponseEntity<Ficha> updateFicha(@PathVariable Long id, @RequestBody Ficha fichaDetails) {
        Ficha updatedFicha = fichaService.updateFicha(id, fichaDetails);
        if (updatedFicha != null) {
            return ResponseEntity.ok(updatedFicha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
