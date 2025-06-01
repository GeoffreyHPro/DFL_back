package com.example.demo.model;

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
public class Player {

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
}