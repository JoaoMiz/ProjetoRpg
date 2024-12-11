package com.example.Rpg_Marnes.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.Rpg_Marnes.model.Mestre;
import com.example.Rpg_Marnes.repository.MestreRepository;






@Service
public class MestreService {
    @Autowired
    private MestreRepository mestreRepository;
    @Autowired
    private ChatService chatService;

   

    public int rolarDados(int numLados, int qntDados, int bonus) {
        int resultado = bonus;
        StringBuilder mensagem = new StringBuilder("Rolagem de Dados: ");
        
        for (int i = 0; i < qntDados; i++) {
            int roll = (int) (Math.random() * numLados) + 1; // Gera um número entre 1 e numLados
            resultado += roll;
            mensagem.append("D" + numLados + " = " + roll + " ");
        }
        
        
        mensagem.append("+ Bônus: " + bonus + " = Total: " + resultado);
        
    
        chatService.AdicionarMensagem(mensagem.toString());

        return resultado;
    }
     public Mestre saveMestre(Mestre mestre) {
        return mestreRepository.save(mestre);
    }

    
}


