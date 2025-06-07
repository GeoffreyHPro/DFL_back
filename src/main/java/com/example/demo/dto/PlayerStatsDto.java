package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

public record PlayerStatsDto(
                @NotNull int finishing,
                @NotNull int shotPower,
                @NotNull int longShot,
                @NotNull int dribble,
                @NotNull int passes,
                @NotNull int interception,
                @NotNull int defense,
                @NotNull int goalKeeperReflexe,
                @NotNull int goalKeeperDiving) implements Serializable {
}