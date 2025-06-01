package com.example.demo.reponses.payload;

import com.example.demo.model.LevelPlayerEnum;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class NewPlayer {
    
    private LevelPlayerEnum level;

    public NewPlayer(LevelPlayerEnum level){
        this.level = level;
    }
}
