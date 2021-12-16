package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (isActorOnCell(nextCell) && considerActorOnCell(nextCell)) {
            attackActor(nextCell.getActor());
        } else if (isClosedDoorOnCell(nextCell)) {
            if (((Player) cell.getActor()).isHaveKey()) {
                nextCell.setType(CellType.OPEN);
                ((Player) cell.getActor()).removeKey();
            }
        }else if (considerWallOnCell(nextCell) && !isActorOnCell(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private boolean considerActorOnCell(Cell nextCell) {
        return !nextCell.getActor().getClass().equals(this.getClass());
    }

    private boolean considerWallOnCell(Cell nextCell) {
        return !isWallOnCell(nextCell) || this.getClass().equals(Ghost.class);
    }

    protected void attackActor(Actor actorToAttack) {
        actorToAttack.modifyHealth(-10);
        if (actorToAttack.getCell().getActor() != null) {
            modifyHealth(-5);
        }
    }

    public void modifyHealth(int amountOfHealth) {
        health += amountOfHealth;
        if (!isAlive()) {
            cell.setActor(null);
        }
    }

    public boolean isAlive() {
        return health >= 0;
    }

    protected boolean isActorOnCell(Cell nextCell) {
        return nextCell.getActor() != null;
    }

    protected boolean isWallOnCell(Cell nextCell) {
        return nextCell.getType().equals(CellType.WALL);
    }

    private boolean isClosedDoorOnCell(Cell nextCell) {
        return nextCell.getType().equals(CellType.CLOSED);
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
