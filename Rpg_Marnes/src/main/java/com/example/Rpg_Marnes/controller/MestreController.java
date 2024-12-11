package com.example.Rpg_Marnes.controller;

import com.example.Rpg_Marnes.service.MestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mestre")
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
}
