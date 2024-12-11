package com.example.Rpg_Marnes.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Rpg_Marnes.model.Mestre;

@Repository
public interface MestreRepository extends JpaRepository<Mestre, Long> {
}
