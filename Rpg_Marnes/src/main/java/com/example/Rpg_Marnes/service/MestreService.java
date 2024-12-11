package com.example.Rpg_Marnes.service;

import org.springframework.stereotype.Service;

@Service
public class MestreService {

    
    public int rolarDados(int numLados, int qntDados, int bonus) {
        int resultado = bonus;
        for (int i = 0; i < qntDados; i++) {
            resultado += (int) (Math.random() * numLados) + 1; // Gera números entre 1 e numLados
        }

        return resultado;
    }
}
