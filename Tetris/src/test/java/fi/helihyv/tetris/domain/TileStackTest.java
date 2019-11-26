
package fi.helihyv.tetris.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import fi.helihyv.tetris.domain.SquareBlock;

/**
 *
 * @author Heli Hyvättinen
 */
public class TileStackTest {
    
    private TileStack tileStack;
    
    public TileStackTest() {
    }
       
    @Before
    public void setUp() {
        
        tileStack = new  TileStack(200,200);
    }
       
    @Test
    public void tileStackRemoveFullRowsRemovesFullRow() {
        
        for (int i = 0; i < 5; i++) {
            tileStack.addBlock(new SquareBlock(40.0*i, 20.0));
        }
        
        tileStack.removeFullRows();
               
        assertTrue(tileStack.getTiles().isEmpty());
    }
    
    @Test 
        public void tileStackRemoveFullRowsReturnsCorrectNumerOfRemovedTiles() {
        
        for (int i = 0; i < 5; i++) {
            tileStack.addBlock(new SquareBlock(40.0*i, 20.0));
        }
        
        assertEquals(20, tileStack.removeFullRows());
    }
    
}
