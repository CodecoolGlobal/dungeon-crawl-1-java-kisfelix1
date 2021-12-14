package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Potion extends Item implements Consumable{
    private final int healthOnConsume = 10;

    public Potion(Cell cell) {
        super(cell);
        cell.setItem(this);
    }

    public String getTileName() {
        return "potion";
    }

    @Override
    public void consumeItem(Player player) {
    }
}
