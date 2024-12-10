package com.example.Rpg_Marnes.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Chat {
    @Id
    private Long id = 1L;
    @ElementCollection
    private List<String> messages = new ArrayList<>();


}
