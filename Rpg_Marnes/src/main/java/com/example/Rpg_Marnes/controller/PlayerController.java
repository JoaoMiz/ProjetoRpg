package com.example.Rpg_Marnes.controller;

import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.service.FichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private FichaService fichaService;

    @PostMapping("/criarFicha")
    public Ficha criarFicha(@RequestBody Ficha ficha) {
        return fichaService.criarFichar(ficha);
    }

    @DeleteMapping
    public ResponseEntity<String> excluirFicha(@PathVariable Long id) {
        fichaService.excluirFicha(id);
        return ResponseEntity.ok("Ficha excluída com sucesso.");
    }

    @PatchMapping
    public Ficha editarFicha(@PathVariable Long id, @RequestBody Ficha novosDados) {
        return fichaService.editarFicha(id, novosDados);
    }

    @GetMapping("/ficha/{id}")
    public Ficha buscarFicha(@PathVariable Long id) {
        return fichaService.buscarFicha(id);
    }
}
