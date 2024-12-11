package com.example.Rpg_Marnes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Monstro extends Ficha {
    @Id
    private Long id;
    private String nivelAmeaça;
}
