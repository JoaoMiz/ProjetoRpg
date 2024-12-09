package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.repository.FichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FichaService {
    Random random = new Random();

    @Autowired
    private FichaRepository fichaRepository;

    public List<com.example.Rpg_Marnes.model.Ficha> findAll() {
        return fichaRepository.findAll();
    }

    public Ficha findById(Long id){
        return fichaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

/*    public List<Integer> rolagem(int numLados, int qntDados, int bonus){
        List<Integer> resultado = List.of();
        for (int x = 0; x <= qntDados; ++x){
            int randomInt = random.nextInt(numLados);
            System.out.println("Resultado: " + randomInt + " " +bonus);
            resultado.add(randomInt);
            randomInt = 0;
        }
    }*/

    public int rolagem(int numLados, int qntDados, int bonus){
        int randomInt = random.nextInt(numLados);
        int resultado = randomInt+bonus;
        System.out.println("Resultado: " + randomInt + " " + bonus);
        return resultado;
    }

    public Ficha iniciativa(){
        Ficha ficha = new Ficha();

        ficha.setIniciativa(rolagem(20,1,0));
        return fichaRepository.save(ficha);
    }
}
