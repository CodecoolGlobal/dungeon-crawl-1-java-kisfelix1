package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public abstract class Enemy extends Actor {
    protected static Random random = new Random();

    public Enemy(Cell cell) {
        super(cell);
    }

    public abstract void aiMove();

}
