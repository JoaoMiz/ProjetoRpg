package com.example.Rpg_Marnes.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Monstro extends Ficha{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nivelAmeaça;
}
