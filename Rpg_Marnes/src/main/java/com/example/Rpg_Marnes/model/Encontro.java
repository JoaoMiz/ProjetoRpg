package com.example.Rpg_Marnes.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Encontro {

    @Id
    private Long id;
    @OneToMany
    private List<Ficha> iniciativa;
}
