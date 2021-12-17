package com.codecool.dungeoncrawl;

public enum MapLevel {
//    "/map.txt", "/map2.txt", "/win.txt", "/lose.txt"
    FIRST_MAP("/map.txt"),
    SECOND_MAP("/map2.txt"),
    WIN_MAP("/win.txt"),
    LOSE_MAP("/lose.txt");

    String mapPath;

    MapLevel(String mapPath) {
        this.mapPath = mapPath;
    }

    public String getMapPath() {
        return mapPath;
    }
}
