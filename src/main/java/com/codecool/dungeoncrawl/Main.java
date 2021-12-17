package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Enemy;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private final static MapLevel[] maps = {MapLevel.FIRST_MAP, MapLevel.SECOND_MAP, MapLevel.WIN_MAP, MapLevel.WIN_MAP };
    private int currentLevel = 0;
    int centerX;
    int centerY;
    private boolean isFestive = false;
    ArrayList<Enemy> enemies = new ArrayList<>();
    GameMap map = MapLoader.loadMap(enemies, maps[currentLevel]);
    Canvas canvas = new Canvas(
            Math.min(map.getWidth(), 45) * Tiles.TILE_WIDTH,
            Math.min(map.getHeight(), 45) * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label nameLabel = new Label(map.getPlayer().getName());
    Label healthLabel = new Label();
    Label potionLabel = new Label();
    Label keyLabel = new Label();
    Label handLabel = new Label();
    Button pickUpButton = new Button("Pick Up Item (F)");
    Button usePotionButton = new Button("Use Potion (H)");
    Button festiveButton = new Button("CLICK ME (T)");
    Label festiveText = new Label();
    Button restartButton = new Button("RESTART (R)");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        centerX = (int) (canvas.getWidth() / (Tiles.TILE_WIDTH * 2));
        centerY = (int) (canvas.getHeight() / (Tiles.TILE_WIDTH * 2));
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Name: "), 0, 0);
        ui.add(nameLabel, 1, 0);
        ui.add(new Label("Health: "), 0, 1);
        ui.add(healthLabel, 1, 1);
        ui.add(new Label(" "), 0, 1);
        ui.add(new Label("Inventory:"), 0, 2);
        ui.add(new Label("Potions: "), 0, 3);
        ui.add(potionLabel, 1, 3);
        ui.add(new Label("Keys: "), 0, 4);
        ui.add(keyLabel, 1, 4);
        ui.add(new Label(" "), 0, 5);
        ui.add(new Label("Equipment:"), 0, 6);
        ui.add(new Label("Hand: "), 0, 7);
        ui.add(handLabel, 1, 7);
        ui.add(new Label(" "), 0, 8);
        ui.add(pickUpButton, 0, 9);
        pickUpButton.setFocusTraversable(false);
        pickUpButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> handleKeyCode(KeyCode.F));
        ui.add(usePotionButton, 0, 10);
        usePotionButton.setFocusTraversable(false);
        usePotionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> handleKeyCode(KeyCode.H));
        ui.add(new Label(" "), 0, 11);
        ui.add(festiveText, 0, 12);
        ui.add(festiveButton, 0, 13);
        festiveButton.setFocusTraversable(false);
        festiveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> handleKeyCode(KeyCode.T));
        ui.add(new Label(" "), 0, 14);
        ui.add(restartButton, 0, 15);
        restartButton.setVisible(false);
        restartButton.setFocusTraversable(false);
        restartButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> handleKeyCode(KeyCode.R));


        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        handleKeyCode(keyEvent.getCode());
    }

    private void handleKeyCode(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                map.getPlayer().move(0, -1);
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                break;
            case F:
                map.getPlayer().pickUpItem();
                break;
            case T:
                isFestive = !isFestive;
                break;
            case H:
                map.getPlayer().consumeHpBottle();
                break;
            case R:
                currentLevel = 0;
                enemies = new ArrayList<>();
                map = MapLoader.loadMap(enemies, maps[currentLevel]);
                break;
        }
        cleanDeadEnemies();
        enemies.forEach(enemy -> enemy.aiMove());
        checkStairs();
        checkPlayerHealth();
        refresh();

    }

    private void checkPlayerHealth() {
        if(!map.getPlayer().isAlive()){
            enemies = new ArrayList<>();
            map = MapLoader.loadMap(enemies, MapLevel.LOSE_MAP);
        }
    }

    private void checkStairs() {
        if(map.getPlayer().getCell().getType().equals(CellType.STAIR)){
            currentLevel++;
            enemies = new ArrayList<>();
            map = MapLoader.loadMap(enemies, maps[currentLevel]);
        }
    }

    private void cleanDeadEnemies() {
        int index = 0;
        while (index < enemies.size() && enemies.get(index).isAlive()) {
            index++;
        }
        if (index < enemies.size()) {
            enemies.remove(index);
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int xOffset = map.getPlayer().getX() < centerX ? 0 : map.getPlayer().getX() - centerX;
        int yOffset = map.getPlayer().getY() < centerY ? 0 : map.getPlayer().getY() - centerY;
        for (int x = 0; x + xOffset < map.getWidth(); x++) {
            for (int y = 0; y + yOffset < map.getHeight(); y++) {
                Cell cell = map.getCell(x + xOffset, y + yOffset);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y, isFestive);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, isFestive);
                } else if (cell.getDecoration() != null) {
                    Tiles.drawTile(context, cell.getDecoration(), x, y, isFestive);
                } else {
                    Tiles.drawTile(context, cell, x, y, isFestive);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        potionLabel.setText("" + map.getPlayer().getPotionNumber());
        keyLabel.setText("" + (map.getPlayer().isHaveKey() ? "1" : "0"));
        handLabel.setText("" + (map.getPlayer().getEquipment("hand") == null ?
                "Empty" :
                map.getPlayer().getEquipmentName("hand")));
        festiveText.setText("" + (!isFestive ? "Feeling festive? " : "Fuck christmas? "));
        restartButton.setVisible(currentLevel > 1);
    }
}
