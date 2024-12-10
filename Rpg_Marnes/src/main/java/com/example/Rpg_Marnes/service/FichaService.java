//package com.example.Rpg_Marnes.service;
//
//import com.example.Rpg_Marnes.model.Ficha;
//import com.example.Rpg_Marnes.model.Monstro;
//import com.example.Rpg_Marnes.model.Npc;
//import com.example.Rpg_Marnes.repository.MonstroRepository;
//import com.example.Rpg_Marnes.repository.NpcRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Random;
//
//@Service
//public class FichaService {
//    Random random = new Random();
//
//    @Autowired
//    private NpcRepository npcRepository;
//
//    @Autowired
//    private MonstroRepository monstroRepository;
//
//    public List<Npc> findAllNpc(){return npcRepository.findAll();}
//
//    public List<Monstro> findAllMonstro(){return monstroRepository.findAll();}
//
//    public Npc findNpcById(Long id){
//        return npcRepository.findById(id).orElseThrow(() -> new RuntimeException("Ficha não encontrado"));
//    }
//
//    public Ficha findMonstroById(Long id){
//        return npcRepository.findById(id).orElseThrow(() -> new RuntimeException("Monstro não encontrado"));
//    }
//
///*    public List<Integer> rolagem(int numLados, int qntDados, int bonus){
//        List<Integer> resultado = List.of();
//        for (int x = 0; x <= qntDados; ++x){
//            int randomInt = random.nextInt(numLados);
//            System.out.println("Resultado: " + randomInt + " " +bonus);
//            resultado.add(randomInt);
//            randomInt = 0;
//        }
//    }*/
//
//    public int rolagem(int numLados, int qntDados, int bonus){
//        int randomInt = random.nextInt(numLados);
//        int resultado = randomInt+bonus;
//        System.out.println("Resultado: " + randomInt + " " + bonus);
//        return resultado;
//    }
//
//    public Npc npcIniciativa(){
//        Npc npc = new Npc();
//
//        npc.setIniciativa(rolagem(20,1,0));
//        return npcRepository.save(npc);
//    }
//
//    public Monstro MonstroIniciativa(){
//        Monstro monstro = new Monstro();
//
//        monstro.setIniciativa(rolagem(20,1,0));
//        return monstroRepository.save(monstro);
//    }
//
//    public Npc criarNpc(Npc npc){return npcRepository.save(npc);}
//
//    public void deletarNpc(Long id){
//        Npc npc = new Npc();
//        npcRepository.delete(npc);
//    }
//
//    public void deletarMonstro(Long id){
//        Monstro monstro = new Monstro();
//        monstroRepository.delete(monstro);
//    }
//
//    public Monstro criarMonstro(Monstro monstro){return monstroRepository.save(monstro);}
//
//    public Npc adicionarItem(Long id, String item){
//        Npc npc = findNpcById(id);
//        List<String> invent = npc.getInventario();
//        invent.add(item);
//        npc.setInventario(invent);
//        return npcRepository.save(npc);
//    }
//
//}
