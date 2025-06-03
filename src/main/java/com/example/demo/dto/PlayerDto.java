package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.model.LevelPlayerEnum;

import jakarta.validation.constraints.NotNull;

public record PlayerDto (
    @NotNull String id,
    @NotNull LevelPlayerEnum levelPlayer,
    @NotNull int finishing,
    @NotNull int shotPower,
    @NotNull int longShot,
    @NotNull int dribble, 
    @NotNull int passes,
    @NotNull int interception,
    @NotNull int defense,
    @NotNull int goalKeeperReflexe,
    @NotNull int goalKeeperDiving,
    @NotNull String firstName,
    @NotNull String lastName,
    @NotNull String country,
    @NotNull String userId

) implements Serializable {
}
