package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Enemy {
    public Skeleton(Cell cell) {
        super(cell);
        health = 20;
    }

    @Override
    public void aiMove() {
        int randomX = random.nextInt(-1,2);
        int randomY = random.nextInt(-1,2);
        if (!(randomX == 0 && randomY ==0) && !(Math.abs(randomX) == 1 && Math.abs(randomY) == 1)) {
            move(randomX, randomY);
        }
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


}
