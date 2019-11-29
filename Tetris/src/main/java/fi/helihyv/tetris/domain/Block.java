
package fi.helihyv.tetris.domain;

import java.util.HashMap;

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

    public TetrisTile[] getTiles() {
        return tiles;
    }
    
    protected HashMap<Double, Double> findSlotsNextToTile(
            TetrisTile tile, 
            boolean left,
            int extendDownwards 
    ) {
     
        HashMap<Double,Double> slots = new HashMap<>();
        
        double offset = left ? -tileWidth : tileWidth;
        
        double y = tile.getYCoordinate() - tile.getYCoordinate() % tileWidth;
        double x = tile.getXCoordinate() + offset;
        
        System.out.println("y: " + y + " x: " + x);
        
        slots.put(y,x);
        
        if (tile.getYCoordinate() % tileWidth != 0) {
            y += tileWidth;
            slots.put(y, x);
        }
        
        for (int i = 0; i < extendDownwards; i++) {
            y += tileWidth;
            slots.put(y, x);
        }
        
        return slots;
    }
    
    public abstract double leftEdge();
    
    public abstract double rightEdge();
    
    public abstract double bottomEdge(double columnLeftEdge);
    
    public abstract HashMap<Double, Double> freeSlotsNeededToMoveLeft();
    
    public abstract HashMap<Double, Double> freeSlotsNeededToMoveRight();

    
}
