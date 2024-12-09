package com.example.Rpg_Marnes.mapper;

import com.example.Rpg_Marnes.dto.PlayerDTO;
import com.example.Rpg_Marnes.model.Player;

import java.util.stream.Collectors;

public class PlayerMapper {

    public static PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setEmail(player.getEmail());

        dto.setFichas(player.getFichas());
        return dto;
    }

    public static Player toEntity(PlayerDTO dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setEmail(dto.getEmail());
        player.setFichas(dto.getFichas());
        return player;
    }
}
