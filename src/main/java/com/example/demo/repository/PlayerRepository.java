package com.example.demo.repository;

import com.example.demo.model.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findByUserId(String userId);

    List<Player> findByUserEmail(String email);

    Optional<Player> findByIdAndUserId(String id, String userId);
}
