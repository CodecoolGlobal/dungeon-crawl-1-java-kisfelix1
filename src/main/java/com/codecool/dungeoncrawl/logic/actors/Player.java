package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Potion;

import java.util.ArrayList;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        health = 100;
    }

    private ArrayList<Item> inventory = new ArrayList();

    public String getTileName() {
        return "player";
    }

    public void consumeHpBottle() {
        int consumed =0;
        for (Item item : inventory) {
            if (item.getClass() == Potion.class && consumed<1) {
                inventory.remove(item);
                consumed++;
            }
        }
    }

    private void giveToInventory(Item item) {
        inventory.add(item);
    }

    public void pickUpItem() {
        if (cell.getItem() != null) {
            giveToInventory(cell.getItem());
            cell.deleteItem();
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
}
