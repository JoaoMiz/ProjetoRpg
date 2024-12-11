package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.dto.PlayerDTO;
import com.example.Rpg_Marnes.mapper.PlayerMapper;
import com.example.Rpg_Marnes.model.Ficha;
import com.example.Rpg_Marnes.model.Player;
import com.example.Rpg_Marnes.repository.FichaRepository;
import com.example.Rpg_Marnes.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FichaRepository fichaRepository;

    public Player criarPlayer(Player player) {
        validarCamposObrigatorios(player);

        return playerRepository.save(player);
    }

    private void validarCamposObrigatorios(Player player) {
        if (player.getEmail() == null || player.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail deve ser preenchido.");
        }
    }

    public Player editarPlayer(Long id, Player novosDados) {
        Player playerExistente = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player não encontrado."));

        if (novosDados.getEmail() != null && !novosDados.getEmail().trim().isEmpty()) {
            playerExistente.setEmail(novosDados.getEmail());
        }

        validarCamposObrigatorios(playerExistente);

        return playerRepository.save(playerExistente);
    }

    public PlayerDTO buscarPlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player não encontrado."));
        return PlayerMapper.toDTO(player);
    }

    public void excluirPlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new IllegalArgumentException("Player não encontrado.");
        }
        playerRepository.deleteById(id);
    }

    public Ficha criarFicha(Ficha ficha) {
        validarCamposObrigatoriosFicha(ficha);
        return fichaRepository.save(ficha);
    }

    public Ficha editarFicha(Long id, Ficha novosDados) {
        Ficha fichaExistente = fichaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ficha não encontrada."));

        if (novosDados.getDescricao() != null && !novosDados.getDescricao().trim().isEmpty()) {
            fichaExistente.setDescricao(novosDados.getDescricao());
        }
        if (novosDados.getVida() > 0) {
            fichaExistente.setVida(novosDados.getVida());
        }
        if (novosDados.getCa() > 0) {
            fichaExistente.setCa(novosDados.getCa());
        }
        if (novosDados.getSg() > 0) {
            fichaExistente.setSg(novosDados.getSg());
        }
        if (novosDados.getIniciativa() > 0) {
            fichaExistente.setIniciativa(novosDados.getIniciativa());
        }
        if (novosDados.getDeslocamento() > 0) {
            fichaExistente.setDeslocamento(novosDados.getDeslocamento());
        }
        if (novosDados.getPericias() != null && !novosDados.getPericias().isEmpty()) {
            fichaExistente.setPericias(novosDados.getPericias());
        }
        if (novosDados.getProficiencia() > 0) {
            fichaExistente.setProficiencia(novosDados.getProficiencia());
        }
        if (novosDados.getForca() > 0) {
            fichaExistente.setForca(novosDados.getForca());
        }
        if (novosDados.getInte() > 0) {
            fichaExistente.setInte(novosDados.getInte());
        }
        if (novosDados.getCar() > 0) {
            fichaExistente.setCar(novosDados.getCar());
        }
        if (novosDados.getCon() > 0) {
            fichaExistente.setCon(novosDados.getCon());
        }
        if (novosDados.getSab() > 0) {
            fichaExistente.setSab(novosDados.getSab());
        }
        if (novosDados.getDex() > 0) {
            fichaExistente.setDex(novosDados.getDex());
        }

        return fichaRepository.save(fichaExistente);
    }

    public Ficha buscarFicha(Long id) {
        return fichaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ficha não encontrada."));
    }

    public void excluirFicha(Long id) {
        if (!fichaRepository.existsById(id)) {
            throw new IllegalArgumentException("Ficha não encontrada.");
        }
        fichaRepository.deleteById(id);
    }

    private void validarCamposObrigatoriosFicha(Ficha ficha) {
        if (ficha.getDescricao() == null || ficha.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da ficha deve ser preenchida.");
        }
        if (ficha.getVida() <= 0) {
            throw new IllegalArgumentException("A vida da ficha deve ser maior que zero.");
        }
        if (ficha.getCa() <= 0) {
            throw new IllegalArgumentException("O CA (Classe de Armadura) deve ser maior que zero.");
        }
        if (ficha.getProficiencia() <= 0) {
            throw new IllegalArgumentException("A proficiência deve ser maior que zero.");
        }
    }
}
