package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Tile;
import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.TBlock;
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
public class TBlockTest {

    TBlock block;

    @Before
    public void setUp() {

        block = new TBlock(100, 20);
    }

    @Test
    public void tBlockRotatesCorrectlyFromZeroToNinetyDegrees() {

        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);

        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(40, tiles[2].getYCoordinate(), 0.1);
        assertEquals(20, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void tBlockRotatesCorrectlyFromNinetyTo180Degrees() {

        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(140, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(100, tiles[2].getXCoordinate(), 0.1);
        assertEquals(120, tiles[3].getXCoordinate(), 0.1);

        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void tBlockRotatesCorrectlyFrom180To270Degrees() {

        block.rotate();
        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(100, tiles[3].getXCoordinate(), 0.1);

        assertEquals(40, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(0, tiles[2].getYCoordinate(), 0.1);
        assertEquals(20, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void tBlockRotatesCorrectlyFrom270ToZeroDegrees() {

        block.rotate();
        block.rotate();
        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(140, tiles[2].getXCoordinate(), 0.1);
        assertEquals(120, tiles[3].getXCoordinate(), 0.1);

        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(00, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void tBlockFreeAreasNeededToRotateTo90DegreesIncludeAreasBlockIsMovingInto() {

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 40, 20))) {
                found1 = true;
            }
        }

        assertTrue(found1);

    }

    @Test
    public void tBlockFreeAreasNeededToRotateTo180DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(100, 20, 20))) {
                found1 = true;
            }
        }

        assertTrue(found1);
    }

    @Test
    public void tBlockFreeAreasNeededToRotateTo270DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 0, 20))) {
                found1 = true;
            }
        }

        assertTrue(found1);
    }

    @Test
    public void tBlockFreeAreasNeededToRotateToZeroDegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(140, 20, 20))) {
                found1 = true;
            }
        }

        assertTrue(found1);

    }

    @Test
    public void tBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIsZeroDegrees() {

        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void tBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs90Degrees() {

        block.rotate();
        assertEquals(60, block.bottomEdge(120), 0.1);
    }

    @Test
    public void tBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs180Degrees() {

        block.rotate();
        block.rotate();

        assertEquals(60, block.bottomEdge(120), 0.1);
    }

    @Test
    public void tBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs270Degrees() {

        block.rotate();
        block.rotate();
        block.rotate();

        assertEquals(60, block.bottomEdge(120), 0.1);
    }

    @Test
    public void tBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(160, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void tBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(160, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void tBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void tBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(160, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void tBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(120, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void tBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void tBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void tBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }
}
