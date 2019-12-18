package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Tile;
import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.LBlock;
import java.util.ArrayList;
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
public class LBlockTest {

    LBlock block;

    @Before
    public void setUp() {
        block = new LBlock(100, 20);
    }

    @Test
    public void lBlockRotatesCorrectlyFromZeroToNinetyDegrees() {

        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(100, tiles[1].getXCoordinate(), 0.1);
        assertEquals(80, tiles[2].getXCoordinate(), 0.1);
        assertEquals(80, tiles[3].getXCoordinate(), 0.1);

        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void lBlockRotatesCorrectlyFromNinetyTo180Degrees() {

        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(100, tiles[1].getXCoordinate(), 0.1);
        assertEquals(100, tiles[2].getXCoordinate(), 0.1);
        assertEquals(80, tiles[3].getXCoordinate(), 0.1);

        assertEquals(40, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(0, tiles[2].getYCoordinate(), 0.1);
        assertEquals(0, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void lBlockRotatesCorrectlyFrom180To270Degrees() {

        block.rotate();
        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(80, tiles[0].getXCoordinate(), 0.1);
        assertEquals(100, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(120, tiles[3].getXCoordinate(), 0.1);

        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(0, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void lBlockRotatesCorrectlyFrom270ToZeroDegrees() {

        block.rotate();
        block.rotate();
        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(100, tiles[1].getXCoordinate(), 0.1);
        assertEquals(100, tiles[2].getXCoordinate(), 0.1);
        assertEquals(120, tiles[3].getXCoordinate(), 0.1);

        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(40, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void lBlockFreeAreasNeededToRotateTo90DegreesIncludeAreasBlockIsMovingInto() {

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(80, 20, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(80, 40, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(120, 20, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void lBlockFreeAreasNeededToRotateTo180DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(80, 0, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(100, 0, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(100, 40, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void lBlockFreeAreasNeededToRotateTo270DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(80, 20, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(120, 0, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(120, 20, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void lBlockFreeAreasNeededToRotateToZeroDegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(100, 0, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(100, 40, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(120, 40, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void lBlockBottomEdgeReturnsZeroWhenBlockNotOnColumn() {

        assertEquals(0, block.bottomEdge(20), 0.1);
    }

    @Test
    public void lBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIsZeroDegrees() {

        assertEquals(60, block.bottomEdge(120), 0.1);
    }

    @Test
    public void lBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs90Degrees() {

        block.rotate();

        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void lBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs180Degrees() {

        block.rotate();
        block.rotate();

        assertEquals(60, block.bottomEdge(100), 0.1);
    }

    @Test
    public void lBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs270Degrees() {

        block.rotate();
        block.rotate();
        block.rotate();

        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void lBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void lBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(120, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void lBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void lBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void lBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(80, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void lBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(80, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void lBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(80, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void lBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

}
