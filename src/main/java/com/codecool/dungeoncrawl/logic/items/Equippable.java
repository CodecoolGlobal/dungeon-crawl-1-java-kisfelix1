package com.codecool.dungeoncrawl.logic.items;

public interface Equippable {
    SlotType getEquipmentSlot();
    String getEquipmentName();
    float getAttackModifier();
    float getDefenseModifier();
}
