package com.example.Rpg_Marnes.model;

import lombok.Data;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@Data
public class Ficha {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private int vida;
   private int ca;
   private int sg;
   private int iniciativa;
   private int deslocamento;
   private String descricao;
   private int proficiencia;
}
