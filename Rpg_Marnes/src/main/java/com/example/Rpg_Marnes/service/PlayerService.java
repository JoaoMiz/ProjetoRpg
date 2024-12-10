package com.example.Rpg_Marnes.service;

import com.example.Rpg_Marnes.dto.PlayerDTO;
import com.example.Rpg_Marnes.mapper.PlayerMapper;
import com.example.Rpg_Marnes.model.Player;
import com.example.Rpg_Marnes.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

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


}
