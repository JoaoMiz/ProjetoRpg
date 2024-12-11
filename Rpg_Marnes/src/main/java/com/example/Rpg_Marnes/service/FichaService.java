package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.repository.FichaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FichaService {


/*    public List<Integer> rolagem(int numLados, int qntDados, int bonus){
       List<Integer> resultado = List.of();
       for (int x = 0; x <= qntDados; ++x){
           int randomInt = random.nextInt(numLados);
           System.out.println("Resultado: " + randomInt + " " +bonus);
           resultado.add(randomInt);
           randomInt = 0;
       }
   }*/

    @Autowired
    private FichaRepository fichaRepository;


    // Criação ou atualização de uma ficha
    public Ficha saveFicha(Ficha ficha) {
        return fichaRepository.save(ficha);
    }

    // Obter todas as fichas
    public List<Ficha> getAllFichas() {
        return fichaRepository.findAll();
    }

    // Obter uma ficha por ID
    public Optional<Ficha> getFichaById(Long id) {
        return fichaRepository.findById(id);
    }

    // Deletar uma ficha por ID
    public void deleteFicha(Long id) {
        fichaRepository.deleteById(id);
    }

    public Ficha updateFicha(Long id, Ficha fichaDetails) {
        // Verifica se a ficha existe
        Optional<Ficha> fichaOptional = fichaRepository.findById(id);
        if (fichaOptional.isPresent()) {
            Ficha ficha = fichaOptional.get();
            // Atualiza os campos da ficha com os dados fornecidos
            ficha.setVida(fichaDetails.getVida());
            ficha.setCa(fichaDetails.getCa());
            ficha.setSg(fichaDetails.getSg());
            ficha.setIniciativa(fichaDetails.getIniciativa());
            ficha.setDeslocamento(fichaDetails.getDeslocamento());
            ficha.setDescricao(fichaDetails.getDescricao());
            ficha.setProficiencia(fichaDetails.getProficiencia());
            // Salva as mudanças
            return fichaRepository.save(ficha);
        } else {
            
            return null; 
        }
    }
   

}
