package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Player;
import com.example.demo.reponses.payload.NewPlayer;
import com.example.demo.service.RandomService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/players")
public class PlayersController {

    @Autowired
    RandomService randomService;

    @Operation(summary = "Create new player", description = "Create new user with unique email and a password")
    @PostMapping()
    public ResponseEntity<Player> createNewPlayer(@RequestBody NewPlayer levelPlayer) {

        return ResponseEntity.status(200).body(randomService.generatePlayer(levelPlayer.getLevel(), 9));
    }
}