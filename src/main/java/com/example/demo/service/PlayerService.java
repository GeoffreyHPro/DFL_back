package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.LevelPlayerEnum;
import com.example.demo.model.Player;
import com.example.demo.model.User;
import com.example.demo.repository.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RandomService randomService;

    @Autowired
    UserService userService;

    public Player save(LevelPlayerEnum levelPlayerEnum, User user) {
        return playerRepository.save(randomService.generatePlayer(levelPlayerEnum, 9, user));
    }

    public List<Player> findPlayersByUserId(String userId) {
        return playerRepository.findByUserId(userId);
    }

    public List<Player> findPlayersByUserEmail(String email) {
        User user = (User) userService.loadUserByUsername(email);
        return playerRepository.findByUserId(user.getId());
    }

    public void deletePlayer(String playerId, User user) {
        Player playerFound = getPlayer(playerId, user.getId());
        playerRepository.delete(playerFound);
    }

    public Player getPlayer(String playerId, String userId) {
        return playerRepository.findByIdAndUserId(playerId, userId).orElseThrow(ResourceNotFoundException::new);
    }
}
