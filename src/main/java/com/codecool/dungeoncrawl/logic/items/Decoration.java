package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public class Decoration implements Drawable {
    public Decoration(Cell cell) {
        cell.setDecoration(this);

    }

    @Override
    public String getTileName() {
        return "christmasTree";
    }
}
