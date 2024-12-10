package com.example.Rpg_Marnes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mestres")
public class Mestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ficha_id", referencedColumnName = "id")
    private Ficha ficha; // Relacionamento com a classe Ficha

    // Construtor padrão
    public Mestre() {
    }

    // Construtor com argumentos
    public Mestre(String email, String senha, Ficha ficha) {
        this.email = email;
        this.senha = senha;
        this.ficha = ficha;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
}
