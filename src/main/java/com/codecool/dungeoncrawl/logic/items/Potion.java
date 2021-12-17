package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Potion extends Item implements Consumable{
    private final int healthOnConsume = 10;

    public Potion(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "potion";
    }

    public int getHealthOnConsume() {
        return healthOnConsume;
    }
}
