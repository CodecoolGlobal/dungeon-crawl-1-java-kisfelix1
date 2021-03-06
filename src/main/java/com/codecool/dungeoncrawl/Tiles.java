package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/boring-tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Image awesomeTileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("potion", new Tile(18, 26));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("sword", new Tile(0, 31));
        tileMap.put("dagger", new Tile(2, 28));
        tileMap.put("closed", new Tile(5, 10));
        tileMap.put("open", new Tile(8, 10));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("protector", new Tile(30, 6));
        tileMap.put("christmasTree", new Tile(1, 1));
        tileMap.put("stair", new Tile(3, 6));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y, boolean isFestive) {
        Tile tile = tileMap.get(d.getTileName());
        if (!isFestive) {
            context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                    x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
        } else {
            context.drawImage(awesomeTileset, tile.x, tile.y, tile.w, tile.h,
                    x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
        }

    }
}
