
package fi.helihyv.tetris.domain;

import java.util.ArrayList;

/**
 *
 * @author Heli Hyvättinen
 */
public abstract class Block {
    
    protected TetrisTile[] tiles;
    protected double tileWidth;
    protected int orientation;

    public Block(double tileWidth) {
        this.tiles = new TetrisTile[4];
        this.tileWidth = tileWidth;
        this.orientation = 0;
    }
    
    
    public void rotate() {
        orientation += 90;
        if (orientation == 360) {
            orientation = 0;
        }
        
        updateOrientation();
    }
    
    protected abstract void updateOrientation();
            
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

    public TetrisTile[] getTiles() {
        return tiles;
    }
        
    public abstract double leftEdge();
    
    public abstract double rightEdge();
    
    public abstract double bottomEdge(double columnLeftEdge);
    
    public abstract ArrayList<Area> freeAreasNeededToMoveLeft();
    
    public abstract ArrayList<Area> freeAreasNeededToMoveRight();
    
    public abstract ArrayList<Area> freeAreasNeededToRotate();

    
}
