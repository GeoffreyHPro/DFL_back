package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converter.PlayerConverter;
import com.example.demo.dto.PlayerDto;
import com.example.demo.model.Player;
import com.example.demo.reponses.payload.NewPlayer;
import com.example.demo.service.PlayerService;
import com.example.demo.service.UserService;

import org.springframework.security.core.Authentication;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/players")
@SecurityRequirement(name = "Authorization")
@Api(tags = "players", description = "Endpoint")
public class PlayersController {

    @Autowired
    PlayerService playerService;

    @Autowired
    UserService userService;

    @Autowired
    PlayerConverter playerConverter;

    @Operation(summary = "Create new player", description = "Create new user with unique email and a password")
    @PostMapping()
    public ResponseEntity<PlayerDto> createNewPlayer(Authentication authentication,
            @RequestBody NewPlayer levelPlayer) {
        Player playerSaved = playerService.save(levelPlayer.getLevel(), userService.getActiveUser());
        return ResponseEntity.status(200).body(playerConverter.apply(playerSaved));
    }

    @Operation(summary = "Get list of players by user id", description = "")
    @GetMapping("/{userId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByUserId(Authentication authentication,
            @PathVariable String userId) {
        List<Player> listPlayers = playerService.findPlayersByUserId(userId);
        return ResponseEntity.status(200)
                .body(listPlayers.stream().map(player -> playerConverter.apply(player)).toList());
    }

    @Operation(summary = "Get list of players own by active user", description = "")
    @GetMapping()
    public ResponseEntity<List<PlayerDto>> getPlayersByUser(Authentication authentication) {
        List<Player> listPlayers = playerService.findPlayersByUserEmail(authentication.getName());
        System.out.println(authentication.getName());
        return ResponseEntity.status(200)
                .body(listPlayers.stream().map(player -> playerConverter.apply(player)).toList());
    }

    @Operation(summary = "Delete player", description = "")
    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> deletePlayer(Authentication authentication,
            @PathVariable String playerId) {
        playerService.deletePlayer(playerId, userService.getActiveUser());
        return ResponseEntity.status(200).body("");
    }
}