package fi.helihyv.tetris.gamelogic;

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
    
    protected void setTilePositions(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {
        
        tiles[0].setYCoordinate(y0);
        tiles[0].setXCoordinate(x0);
        tiles[1].setYCoordinate(y1);
        tiles[1].setXCoordinate(x1);
        tiles[2].setYCoordinate(y2);
        tiles[2].setXCoordinate(x2);
        tiles[3].setYCoordinate(y3);
        tiles[3].setXCoordinate(x3);
    }
    
    public abstract double leftEdge();
    
    public abstract double rightEdge();
    
    public abstract double bottomEdge(double columnLeftEdge);
    
    public abstract ArrayList<Area> freeAreasNeededToMoveLeft();
    
    public abstract ArrayList<Area> freeAreasNeededToMoveRight();
    
    public abstract ArrayList<Area> freeAreasNeededToRotate();
    
    protected Area createTileWideArea(double topY, double leftX, int lengthInTiles) {
        
        return new Area(topY, leftX, topY + tileWidth * lengthInTiles, leftX + tileWidth);
    }
}
