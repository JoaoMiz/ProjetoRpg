package com.example.Rpg_Marnes.model;

import java.util.List;

import com.example.Rpg_Marnes.repository.FichaRepository;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mestres")
public class Mestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;     

    
}
