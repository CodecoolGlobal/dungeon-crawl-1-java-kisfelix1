package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item implements Equippable{

    public Sword(Cell cell) {
        super(cell);
        cell.setItem(this);
    }

    @Override
    public String getTileName() {
        return "sword";
    }

    @Override
    public String getEquipmentSlot() {
        return "hand";
    }

    @Override
    public String getEquipmentName() {
        return "Sword";
    }

    @Override
    public float getAttackModifier() {
        return 1.4f;
    }

    @Override
    public float getDefenseModifier() {
        return 1;
    }
}
