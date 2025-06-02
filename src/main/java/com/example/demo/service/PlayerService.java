package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LevelPlayerEnum;
import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;

@Service
public class PlayerService {
    
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RandomService randomService;

    public Player save(LevelPlayerEnum levelPlayerEnum){
        return playerRepository.save(randomService.generatePlayer(levelPlayerEnum, 9));
    }
}
