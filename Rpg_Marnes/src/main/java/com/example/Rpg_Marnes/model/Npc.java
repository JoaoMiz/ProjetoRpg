package com.example.Rpg_Marnes.model;

import lombok.Data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Data
public class Npc extends Ficha {
    @Id
    private Long id;

    private List<String> inventario;
    private int xp;
}
