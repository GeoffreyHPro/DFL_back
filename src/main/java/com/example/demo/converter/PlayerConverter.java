package com.example.demo.converter;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PlayerDto;
import com.example.demo.dto.PlayerLightDto;
import com.example.demo.dto.PlayerStatsDto;
import com.example.demo.model.Player;

@Service
public class PlayerConverter {

    public PlayerDto apply(Player player) {
        if (player == null) {
            return null;
        }

        PlayerStatsDto playerStatsDto = new PlayerStatsDto(player.getFinishing(),
                player.getShotPower(),
                player.getLongShot(),
                player.getDribble(),
                player.getPasses(),
                player.getInterception(),
                player.getDefense(),
                player.getGoalKeeperReflexe(),
                player.getGoalKeeperDiving());

        return new PlayerDto(
                player.getId(),
                player.getLevelPlayer(),
                playerStatsDto,
                player.getFirstName(),
                player.getLastName(),
                player.getCountry(),
                player.getUser().getId());
    }

    public PlayerLightDto applyLight(Player player) {
        if (player == null) {
            return null;
        }

        return new PlayerLightDto(
                player.getId(),
                player.getLevelPlayer(),
                player.getFirstName(),
                player.getLastName(),
                player.getCountry());
    }
}
