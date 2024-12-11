package com.example.Rpg_Marnes.dto;

import com.example.Rpg_Marnes.model.Ficha;
import lombok.Data;

import java.util.List;

@Data
public class PlayerDTO {

    private Long id;
    private String email;
    private List<Ficha> fichas;
}
