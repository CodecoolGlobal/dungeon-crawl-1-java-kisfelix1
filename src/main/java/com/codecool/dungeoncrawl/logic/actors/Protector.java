package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Protector extends Enemy {
    public Protector(Cell cell) {
        super(cell);
        health = 20;
    }

    @Override
    public String getTileName() {
        return "protector";
    }

    @Override
    public void aiMove() {
    }
}
