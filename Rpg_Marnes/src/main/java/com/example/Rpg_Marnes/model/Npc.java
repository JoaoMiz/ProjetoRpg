package com.example.Rpg_Marnes.model;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Npc extends Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Geração automática do ID
    private Long id;

    private List<String> inventario;
    private int xp;

    // Getters e setters
}