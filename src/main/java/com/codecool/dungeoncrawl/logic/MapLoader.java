package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(List<Enemy> enemies, String mapName) {
        InputStream is = MapLoader.class.getResourceAsStream(mapName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine();

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            enemies.add(new Skeleton(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            enemies.add(new Ghost(cell, map));
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            enemies.add(new Protector(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Potion(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Decoration(cell);
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new Dagger(cell);
                            break;
                        case 'O':
                            cell.setType(CellType.OPEN);
                            break;
                        case 'C':
                            cell.setType(CellType.CLOSED);
                            break;
                        case 'L':
                            cell.setType(CellType.STAIR);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
