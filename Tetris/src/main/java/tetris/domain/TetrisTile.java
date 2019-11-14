
package tetris.domain;

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
    
    public boolean moveLeft() {
      return false;  
    }

    public boolean moveRight() {
        return false;
    }

    @Override
    public double getXCoordinate() {
        return x;
    }

    @Override
    public double getYCoordinate() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
        
    }
    
    
}

