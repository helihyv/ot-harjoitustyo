
package tetris.domain;

/**
 *
 * @author Heli Hyvättinen
 */
public abstract class Block {
    
    protected TetrisTile[] tiles;
    protected double tileWidth;

    public Block( double tileWidth) {
        this.tiles = new TetrisTile[4];
        this.tileWidth = tileWidth;
    }
    
    
    public abstract void rotate() ;
            
    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            if (!tiles[i].moveLeft()) {
                break;
            }
        }
    }

    public void moveRight() {
        for (int i=0; i<4; i++) {
            if (!tiles[i].moveRight()) {
                break;
            };
        }
    }

    public TetrisTile[] getTiles() {
        return tiles;
    }
    
    
    
}
