 
package tetris.domain;

/**
 *
 * @author Heli Hyvättinen
 */
public class SquareBlock extends Block {

    public SquareBlock(double xCoordinate, double tileWidth) {
        super(tileWidth);
        tiles[0] = new TetrisTile(xCoordinate,0,tileWidth);
        tiles[1] = new TetrisTile(xCoordinate, tileWidth, tileWidth);
        tiles[2] = new TetrisTile(xCoordinate + tileWidth,0,tileWidth);
        tiles[3] = new TetrisTile(xCoordinate + tileWidth,tileWidth,tileWidth);
    } 
    
    public void rotate() {
        
    }
    
    
}
