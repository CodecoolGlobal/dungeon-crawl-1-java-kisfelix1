package com.codecool.dungeoncrawl.logic.items;

public enum EquipmentType {
    SWORD("sword", SlotType.HAND, "Sword", 1.5F),
    DAGGER("dagger",SlotType.HAND, "Dagger", 1.2F);

    private String tileName;
    private SlotType slot;
    private String name;
    private float modifier;

    EquipmentType(String tileName, SlotType slot, String name, float modifier) {
        this.tileName = tileName;
        this.slot = slot;
        this.name = name;
        this.modifier = modifier;
    }

    public SlotType getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public float getModifier() {
        return modifier;
    }

    public String getTileName() {
        return tileName;
    }

}
