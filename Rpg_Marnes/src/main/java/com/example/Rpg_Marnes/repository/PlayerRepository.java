package com.example.Rpg_Marnes.repository;

import com.example.Rpg_Marnes.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
