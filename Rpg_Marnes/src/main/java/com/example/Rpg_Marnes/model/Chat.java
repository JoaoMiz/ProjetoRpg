package com.example.Rpg_Marnes.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="chat")
@Data
public class Chat {
    @Id
    private Long id;
    @ElementCollection
    private List<String> messages = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }
    public List<String> getMessages() {
        return messages;
    }


}
