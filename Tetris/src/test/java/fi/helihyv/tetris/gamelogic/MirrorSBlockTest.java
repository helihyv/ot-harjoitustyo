package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Tile;
import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.MirrorSBlock;
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
public class MirrorSBlockTest {

    MirrorSBlock block;

    @Before
    public void setUp() {

        block = new MirrorSBlock(100, 20);
    }

    @Test
    public void mirrorSBlockRotatesCorrectlyFromZeroToNinetyDegrees() {

        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(100, tiles[2].getXCoordinate(), 0.1);
        assertEquals(100, tiles[3].getXCoordinate(), 0.1);

        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void mirroSBlockRotatesCorrectlyFromNinetyTo180Degrees() {

        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);

        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(40, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void mirrorSBlockRotatesCorrectlyFrom180To270Degrees() {

        block.rotate();
        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(120, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(100, tiles[2].getXCoordinate(), 0.1);
        assertEquals(100, tiles[3].getXCoordinate(), 0.1);

        assertEquals(0, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(20, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void mirrorSBlockRotatesCorrectlyFrom270ToZeroDegrees() {

        block.rotate();
        block.rotate();
        block.rotate();
        block.rotate();

        Tile[] tiles = block.getTiles();

        assertEquals(100, tiles[0].getXCoordinate(), 0.1);
        assertEquals(120, tiles[1].getXCoordinate(), 0.1);
        assertEquals(120, tiles[2].getXCoordinate(), 0.1);
        assertEquals(140, tiles[3].getXCoordinate(), 0.1);

        assertEquals(20, tiles[0].getYCoordinate(), 0.1);
        assertEquals(20, tiles[1].getYCoordinate(), 0.1);
        assertEquals(40, tiles[2].getYCoordinate(), 0.1);
        assertEquals(40, tiles[3].getYCoordinate(), 0.1);
    }

    @Test
    public void mirrorSBlockFreeAreasNeededToRotateTo90DegreesIncludeAreasBlockIsMovingInto() {

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 0, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(100, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
    }

    @Test
    public void mirrorSBlockFreeAreasNeededToRotateTo180DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 40, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(140, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);

    }

    @Test
    public void mirrorSBlockFreeAreasNeededToRotateTo270DegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 0, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(100, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);

    }

    @Test
    public void mirrorSBlockFreeAreasNeededToRotateToZeroDegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
        block.rotate();
        block.rotate();

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(120, 40, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(140, 40, 20))) {
                found2 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
    }

    @Test
    public void mirrorSBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIsZeroDegrees() {

        assertEquals(60, block.bottomEdge(120), 0.1);
    }

    @Test
    public void mirrorSBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs90Degrees() {

        block.rotate();
        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void mirroSBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs180Degrees() {

        block.rotate();
        block.rotate();

        assertEquals(60, block.bottomEdge(120), 0.1);
    }

    @Test
    public void mirroSBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs270Degrees() {

        block.rotate();
        block.rotate();
        block.rotate();

        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void mirrorSBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorSBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(160, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorSBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorSBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(160, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorSBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorSBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorSBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void mirrorSBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }
}
