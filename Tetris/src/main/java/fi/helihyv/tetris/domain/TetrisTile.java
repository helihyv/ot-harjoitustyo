
package fi.helihyv.tetris.domain;

/**
 *
 * @author Heli Hyvättinen
 */
public class TetrisTile implements Tile {
    
    private double x;
    private double y;
    
    private double width;

    public TetrisTile(double x, double y, double width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }
    
    public void moveLeft() {
        this.x -= this.width;  
    }

    public void moveRight() {
        this.x += this.width;
    }
    
    public void moveDown() {
        this.y += 1;
    }

    @Override
    public double getXCoordinate() {
        return x;
    }

    @Override
    public double getYCoordinate() {
        return y;
    }

    public void setYCoordinate(double y) {
        this.y = y;
    }

    public void setXCoordinate(double x) {
        this.x = x;
    }
    
    

    @Override
    public double getWidth() {
        return width;
        
    }
    
    public void moveDownByTileWidth() {
        this.y += width;
    }
    
    
}

