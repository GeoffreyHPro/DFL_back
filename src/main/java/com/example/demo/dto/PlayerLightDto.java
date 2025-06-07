package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.model.LevelPlayerEnum;

import jakarta.validation.constraints.NotNull;

public record PlayerLightDto(
                @NotNull String id,
                @NotNull LevelPlayerEnum levelPlayer,
                @NotNull String firstName,
                @NotNull String lastName,
                @NotNull String country,
                @NotNull int overall) implements Serializable {
}
