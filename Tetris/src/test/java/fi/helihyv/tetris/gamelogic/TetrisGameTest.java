
package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.TetrisGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heli Hyvättinen
 */
public class TetrisGameTest {
    
    private TetrisGame game;
    private double[] currentBlockTilesStartXPositions;
    private double width;

    public TetrisGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        game = new TetrisGame();
        currentBlockTilesStartXPositions = new double [4];
        
        for (int i = 0; i < 4; i++) {
            currentBlockTilesStartXPositions[i] = game.getTiles().get(i).getXCoordinate();
        }
        
        width = game.getTiles().get(0).getWidth();

    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void moveBlockLeftMovesAllBlockTilesLeftIfRoomAvailable() {
                
        game.moveBlockLeft();
        
        for (int i = 0; i <4; i++) {
            assertEquals(
                    currentBlockTilesStartXPositions[i] - width,
                    game.getTiles().get(i).getXCoordinate(),
                    0.1
            );
        }
    }
    
    @Test
    public void moveBlockLeftDoesNotMoveTilesLeftIfNoRoomAvailable() {
        
        for (int i=0; i < 20; i++) {
            game.moveBlockLeft();
        }
        
        double[] coordinates = new double [4];
        
        for (int i = 0; i < 4; i++) {
            coordinates[i] = game.getTiles().get(i).getXCoordinate();
        }
        
        game.moveBlockLeft();
        
        for (int i = 0; i < 4; i++) {
            assertEquals(
                coordinates[i],
                game.getTiles().get(i).getXCoordinate(),
                0.1);
        }
        
    }
    
    @Test
    public void moveBlockRightMovesAllBlockTilesRightIfRoomAvailable() {
        
        game.moveBlockRight();
        for (int i=0; i <4; i++) {
            assertEquals(
                    currentBlockTilesStartXPositions[i] + width,
                    game.getTiles().get(i).getXCoordinate(),
                    0.1);
        }
    }
    
    @Test
    public void moveBlockRightDoesNotMoveTilesRightIfBorderOfGameAreaReached() {
        for (int i = 0; i < 20; i++) {
            game.moveBlockRight();
        }
        
        double[] coordinates = new double [4];
        
        for (int i = 0; i < 4; i++) {
            coordinates[i] = game.getTiles().get(i).getXCoordinate();
        }
        
        game.moveBlockRight();
        
        for (int i = 0; i < 4; i++) {
            assertEquals(
                coordinates[i],
                game.getTiles().get(i).getXCoordinate(),
                0.1);
        }
        
  
    }
    
    
    
}