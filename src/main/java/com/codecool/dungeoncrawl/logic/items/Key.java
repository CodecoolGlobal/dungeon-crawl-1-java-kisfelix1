package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item implements Consumable {


    public Key(Cell cell) {
        super(cell);
        cell.setItem(this);

    }

    @Override
    public String getTileName() {
        return "key";
    }
}
