package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Actor {
    private final String name = "Hero";
    private final HashMap<String, Item> equipment = new HashMap<>() {{
        put("hand", null);
    }};
    private final ArrayList<Item> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        health = 100;
    }
    public String getName(){
        return name;
    }
    public String getTileName() {
        return "player";
    }

    public void consumeHpBottle() {
        int index = 0;
        while (index < inventory.size() && inventory.get(index).getClass() != Potion.class) {
            index++;
        }
        if (index < inventory.size()) {
            modifyHealth(((Potion) inventory.get(index)).getHealthOnConsume());
            inventory.remove(index);
        }
    }

    private void giveToInventory(Item item) {
        inventory.add(item);
    }

    public void pickUpItem() {
        if (cell.getItem() != null) {
            if (cell.getItem() instanceof Equippable) {
                setEquipment((Equippable)cell.getItem());
            } else {
                giveToInventory(cell.getItem());
                cell.deleteItem();
            }
        }
    }

    public int getPotionNumber() {
        int res = 0;
        for (Item item : inventory) {
            if (item.getClass() == Potion.class) {
                res++;
            }
        }
        return res;
    }
    public boolean isHaveKey() {
        for (Item item : inventory) {
            if (item.getClass() == Key.class) {
                return true;
            }
        }
        return false;
    }

    public void removeKey() {
        int index = 99;  // For IntelliJ's fear from this index integer might be not initialized I gave it a random number
        for (Item item : inventory) {
            if (item.getClass() == Key.class) {
                index = inventory.indexOf(item);
            }
        }
        inventory.remove(index);
    }

    public Item getEquipment(String equipmentPart) {
        return equipment.get(equipmentPart);
    }

    public String getEquipmentName(String equipmentPart) {
        return ((Equippable)getEquipment(equipmentPart)).getEquipmentName();
    }

    public void setEquipment(Equippable equippable) {
        if (this.equipment.get(equippable.getEquipmentSlot()) != null) {
            cell.setItem(this.equipment.get(equippable.getEquipmentSlot()));
        }
        else{
            cell.deleteItem();
        }
        this.equipment.put(equippable.getEquipmentSlot(), (Item)equippable);
    }
    public boolean isAlive(){
        return health > 0;
    }
}
