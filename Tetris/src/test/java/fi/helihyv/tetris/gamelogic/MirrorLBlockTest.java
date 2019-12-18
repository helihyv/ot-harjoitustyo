package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Tile;
import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.MirrorLBlock;
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
public class MirrorLBlockTest {

    MirrorLBlock block;

    @Before
    public void setUp() {

        block = new MirrorLBlock(100, 20);
    }

    @Test
    public void mirrorLBlockRotatesCorrectlyFromZeroToNinetyDegrees() {

        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(140, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(100, tiles[2].getXCoordinate(), 0.1);
        assertEquals(100, tiles[3].getXCoordinate(), 0.1);

        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(0, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void mirrorLBlockRotatesCorrectlyFromNinetyTo180Degrees() {

        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);

        assertEquals(40, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(0, tiles[2].getYCoordinate(), 0.1);
        assertEquals(0, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void mirrorLBlockRotatesCorrectlyFrom180To270Degrees() {

        block.rotate();
        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(140, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);

        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void mirrorLBlockRotatesCorrectlyFrom270ToZeroDegrees() {

        block.rotate();
        block.rotate();
        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(100, tiles[3].getXCoordinate(), 0.1);

        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(40, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void mirrorLBlockFreeAreasNeededToRotateTo90DegreesIncludeAreasBlockIsMovingInto() {

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(100, 20, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(100, 0, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(140, 20, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void mirrorLBlockFreeAreasNeededToRotateTo180DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 0, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(140, 0, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(140, 40, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void mirrorLBlockFreeAreasNeededToRotateTo270DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(100, 20, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(140, 20, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(140, 40, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void mirrorLBlockFreeAreasNeededToRotateToZeroDegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 0, 20))) {
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
    public void mirrorLBlockBottomEdgeReturnsZeroWhenBlockNotOnColumn() {

        assertEquals(0, block.bottomEdge(20), 0.1);
    }

    @Test
    public void mirrorLBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIsZeroDegrees() {

        assertEquals(60, block.bottomEdge(120), 0.1);
    }

    @Test
    public void mirrorLBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs90Degrees() {

        block.rotate();
        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void mirrorLBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(60, block.bottomEdge(120), 0.1);
    }

    @Test
    public void mirrorLBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();

        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void mirrorLBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(160, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorLBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(160, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorLBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(160, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorLBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorLBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorLBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(120, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorLBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorLBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

}
