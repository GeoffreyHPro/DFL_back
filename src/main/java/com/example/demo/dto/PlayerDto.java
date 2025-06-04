package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.model.LevelPlayerEnum;

import jakarta.validation.constraints.NotNull;

public record PlayerDto (
    @NotNull String id,
    @NotNull LevelPlayerEnum levelPlayer,
    @NotNull PlayerStatsDto playerStats,
    @NotNull String firstName,
    @NotNull String lastName,
    @NotNull String country,
    @NotNull String userId

) implements Serializable {
}
