package fi.helihyv.tetris.gamelogic;

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
import fi.helihyv.tetris.gamelogic.TetrisTile;

public class TetrisTileTest {

    TetrisTile tile;

    @Before
    public void setUp() {
        tile = new TetrisTile(100, 100, 20);
    }

    @Test
    public void moveLeftChangesTetrisTileXCoordinateCorrectly() {
        tile.moveLeft();
        assertEquals(80, tile.getXCoordinate(), 0.1);
    }

    @Test
    public void moveRightChangesTetrisTileXCoordinateCorrectly() {
        tile.moveRight();
        assertEquals(120, tile.getXCoordinate(), 0.1);
    }
}
