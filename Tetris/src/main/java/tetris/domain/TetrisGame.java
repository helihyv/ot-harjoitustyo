
package tetris.domain;

import java.util.List;
import java.util.Arrays;

/**
 *
 * @author Heli Hyvättinen
 */
public class TetrisGame implements Game {
    
    
    
    private int gameAreaWidth;
    private int gameAreaHeight;
    private double tileWidth;
    private Block currentBlock;

    public TetrisGame() {
        this.gameAreaWidth = 600;
        this.gameAreaHeight = 900;
        this.tileWidth = 20;
        this.currentBlock = 
                new SquareBlock(gameAreaWidth/2-tileWidth, tileWidth);
    }

    @Override
    public int getGameAreaHeight() {
        return gameAreaHeight;
    }

    @Override
    public int getGameAreaWidth() {
        return gameAreaWidth;
    }

    @Override
    public List<Tile> getTiles() {
        return Arrays.asList(currentBlock.getTiles()); 
    }
    
    @Override
    public void startGame() {
        
    }
    
    @Override
    public void moveBlockLeft() {
        if (currentBlock.leftEdge() >= tileWidth) {
            currentBlock.moveLeft();
        }
    }
        
    @Override
    public void moveBlockRight() {
        if (currentBlock.rightEdge() <= gameAreaWidth - tileWidth) {
            currentBlock.moveRight();
        }
    }   
}
