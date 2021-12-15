package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
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
    ArrayList<Enemy> enemies = new ArrayList<>();
    GameMap map = MapLoader.loadMap(enemies);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label potionLabel = new Label();
    Label keyLabel = new Label();
    Label handLabel = new Label();
    Button pickUpButton = new Button("Pick Up Item (F)");
    Button usePotionButton = new Button("Use Potion (H)");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label(" "),0,1);
        ui.add(new Label("Inventory:"),0, 2);
        ui.add(new Label("Potions: "), 0, 3);
        ui.add(potionLabel, 1, 3);
        ui.add(new Label("Keys: "),0, 4);
        ui.add(keyLabel, 1, 4);
        ui.add(new Label(" "),0,5);
        ui.add(new Label("Equipment:"),0,6);
        ui.add(new Label("Hand: "), 0, 7);
        ui.add(handLabel, 1, 7);
        ui.add(new Label(" "),0,8);
        ui.add(pickUpButton, 0, 9);
        pickUpButton.setFocusTraversable(false);
        pickUpButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> handleKeyCode(KeyCode.F));
        ui.add(usePotionButton, 0, 10);
        usePotionButton.setFocusTraversable(false);
        usePotionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> handleKeyCode(KeyCode.H));


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
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                break;
            case F:
                map.getPlayer().pickUpItem();
                refresh();
                break;
            case H:
                map.getPlayer().consumeHpBottle();
                refresh();
                break;
        }
        cleanDeadEnemies();
        enemies.forEach(enemy -> enemy.aiMove());
    }

    private void cleanDeadEnemies() {
        int index = 0;
        while(index < enemies.size() && enemies.get(index).isAlive()){
            index++;
        }
        if(index < enemies.size()){
            enemies.remove(index);
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else if (cell.getDecoration() != null) {
                    Tiles.drawTile(context, cell.getDecoration(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        potionLabel.setText("" + map.getPlayer().getPotionNumber());
        keyLabel.setText("" + (map.getPlayer().isHaveKey() ? "1" : "0"));
        handLabel.setText("" + (map.getPlayer().getEquipment("hand") == null ?
                "Empty" :
                map.getPlayer().getEquipmentName("hand")));
    }
}
