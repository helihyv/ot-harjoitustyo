
package tetris.domain;

import java.util.HashMap;

/**
 *
 * @author Heli Hyvättinen
 */
public abstract class Block {
    
    protected TetrisTile[] tiles;
    protected double tileWidth;

    public Block(double tileWidth) {
        this.tiles = new TetrisTile[4];
        this.tileWidth = tileWidth;
    }
    
    
    public abstract void rotate();
            
    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            tiles[i].moveLeft();            
        }
    }

    public void moveRight() {
        for (int i = 0; i < 4; i++) {
            tiles[i].moveRight();
        }
    }
    
    public void moveDown() {
        for (int i = 0; i < 4; i++) {
            tiles[i].moveDown();
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }
    
    public abstract double leftEdge();
    
    public abstract double rightEdge();
    
    public abstract double bottomEdge(double columnLeftEdge);
    
    public abstract HashMap<Double, Double> freeSlotsNeededToMoveLeft();
    
    public abstract HashMap<Double, Double> freeSlotsNeededToMoveRight();

    
}
