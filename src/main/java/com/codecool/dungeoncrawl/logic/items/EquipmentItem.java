package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class EquipmentItem extends Item implements Equippable {
    private EquipmentType type;

    public EquipmentItem(Cell cell, EquipmentType type) {
        super(cell);
        this.type = type;
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    @Override
    public SlotType getEquipmentSlot() {
        return type.getSlot();
    }

    @Override
    public String getEquipmentName() {
        return type.getName();
    }

    @Override
    public float getAttackModifier() {
        return type.getModifier();
    }

    @Override
    public float getDefenseModifier() {
        return 1;
    }
}
