package com.codecool.dungeoncrawl.logic.items;

public interface Equippable {
    String getEquipmentSlot();
    String getEquipmentName();
    float getAttackModifier();
    float getDefenseModifier();
}
