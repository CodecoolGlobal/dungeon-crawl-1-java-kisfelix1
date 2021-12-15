package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Dagger extends Item implements Equippable{


    public Dagger(Cell cell) {
        super(cell);
        cell.setItem(this);

    }

    @Override
    public String getTileName() {
        return "dagger";
    }

    @Override
    public String getEquipmentSlot() {
        return "hand";
    }

    @Override
    public String getEquipmentName() {
        return "Dagger";
    }
}
