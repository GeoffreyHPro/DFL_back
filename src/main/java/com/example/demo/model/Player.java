package com.example.demo.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    private String id;

    private int finishing;
    private int shotPower;
    private int longShot;
    private int dribble;
    private int passes;
    private int interception;
    private int defense;
    private int goalKeeperReflexe;
    private int goalKeeperDiving;

    private String firstName;
    private String lastName;
    private String country;

    @ManyToOne
    User user;

    @Enumerated(EnumType.STRING)
    private LevelPlayerEnum levelPlayer;

    public Player(LevelPlayerEnum levelPlayerEnum, int finishing, int shotPower, int longShot, int dribble, int passes,
            int interception, int defense, int goalKeeperReflexe, int goalKeeperDiving, String firstName,
            String lastName, String country) {
        this.id = UUID.randomUUID().toString();
        this.levelPlayer = levelPlayerEnum;
        this.finishing = finishing;
        this.shotPower = shotPower;
        this.longShot = longShot;
        this.dribble = dribble;
        this.passes = passes;
        this.interception = interception;
        this.defense = defense;
        this.goalKeeperReflexe = goalKeeperReflexe;
        this.goalKeeperDiving = goalKeeperDiving;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
}