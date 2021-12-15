package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Ghost extends Enemy {
    private final GameMap map;

    public Ghost(Cell cell, GameMap map) {
        super(cell);
        health = 20;
        this.map = map;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void aiMove() {
        Player player = map.getPlayer();
        int directionalXDistanceFromPlayer = player.getX() - cell.getX();
        int directionalYDistanceFromPlayer = player.getY() - cell.getY();
        int xDistanceFromPlayer = Math.abs(directionalXDistanceFromPlayer);
        int yDistanceFromPlayer = Math.abs(directionalYDistanceFromPlayer);
        if (xDistanceFromPlayer > yDistanceFromPlayer) {
            move(directionalXDistanceFromPlayer/xDistanceFromPlayer, 0);
        }
        else{
            move(0, directionalYDistanceFromPlayer/yDistanceFromPlayer);
        }
    }
}
