package com.example.Rpg_Marnes.controller;

import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.service.FichaService;
import com.example.Rpg_Marnes.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/criarFicha")
    public Ficha criarFicha(@RequestBody Ficha ficha) {
        return playerService.criarFicha(ficha);
    }

    @DeleteMapping
    public ResponseEntity<String> excluirFicha(@PathVariable Long id) {
        playerService.excluirFicha(id);
        return ResponseEntity.ok("Ficha excluída com sucesso.");
    }

    @PatchMapping
    public Ficha editarFicha(@PathVariable Long id, @RequestBody Ficha novosDados) {
        return playerService.editarFicha(id, novosDados);
    }

    @GetMapping("/ficha/{id}")
    public Ficha buscarFicha(@PathVariable Long id) {
        return playerService.buscarFicha(id);
    }
}
