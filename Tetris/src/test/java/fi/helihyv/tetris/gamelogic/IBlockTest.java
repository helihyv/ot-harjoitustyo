package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.IBlock;
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
public class IBlockTest {

    IBlock block;

    @Before
    public void setUp() {

        block = new IBlock(100, 20);
    }

    @Test
    public void iBlockRotatesCorrectlyFromHorizontalToVertical() {

        block.rotate();

        for (int i = 0; i < 4; i++) {
            assertEquals(120, block.getTiles()[i].getXCoordinate(), 0.1);
            assertEquals(i * 20, block.getTiles()[i].getYCoordinate(), 0.1);
        }

    }

    @Test
    public void iBlockRotatesCorrectlyFromVerticalToHorizontal() {

        block.rotate();
        block.rotate();

        for (int i = 0; i < 4; i++) {
            assertEquals(20, block.getTiles()[i].getYCoordinate(), 0.1);
            assertEquals(100 + i * 20, block.getTiles()[i].getXCoordinate(), 0.1);
        }
    }

    @Test
    public void iBlockFreeAreasNeededToRotateTo90DegreesIncludeAreasBlockIsMovingInto() {

        ArrayList<Area> areas = block.freeAreasNeededToRotate();

        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;

        for (Area area : areas) {
            if (area.overlaps(new TetrisTile(100, 0, 20))) {
                found1 = true;
            }
            if (area.overlaps(new TetrisTile(140, 40, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(160, 60, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void iBlockFreeAreasNeededToRotateTo180DegreesIncludeAreasBlockIsMovingInto() {

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

            if (area.overlaps(new TetrisTile(160, 20, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void iBlockFreeAreasNeededToRotateTo270DegreesIncludeAreasBlockIsMovingInto() {

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
            if (area.overlaps(new TetrisTile(120, 40, 20))) {
                found2 = true;
            }

            if (area.overlaps(new TetrisTile(120, 60, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void iBlockFreeAreasNeededToRotateToZeroDegreesIncludeAreasBlockIsMovingInto() {

        block.rotate();
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

            if (area.overlaps(new TetrisTile(160, 20, 20))) {
                found3 = true;
            }
        }

        assertTrue(found1);
        assertTrue(found2);
        assertTrue(found3);
    }

    @Test
    public void iBlockBottomEdgeReturnsZeroWhenBlockNotOnColumn() {

        assertEquals(0, block.bottomEdge(20), 0.1);
    }

    @Test
    public void iBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIsZeroDegrees() {

        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void iBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs90Degrees() {

        block.rotate();
        assertEquals(80, block.bottomEdge(120), 0.1);
    }

    @Test
    public void iBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs180Degrees() {

        block.rotate();
        block.rotate();

        assertEquals(40, block.bottomEdge(120), 0.1);
    }

    @Test
    public void iBlockBottomEdgeReturnsCorrectYCoordinateWhenOrientationIs270Degrees() {

        block.rotate();
        block.rotate();
        block.rotate();

        assertEquals(80, block.bottomEdge(120), 0.1);
    }

    @Test
    public void iBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void iBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(180, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void iBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(140, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void iBlockRightEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(180, block.rightEdgeAfterRotate(), 0.1);
    }

    @Test
    public void iBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIsZeroDegrees() {
        assertEquals(120, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void iBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs90Degrees() {
        block.rotate();
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void iLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs180Degrees() {
        block.rotate();
        block.rotate();

        assertEquals(120, block.leftEdgeAfterRotate(), 0.1);
    }

    @Test
    public void iBlockLeftEdgeAfterRotateReturnsCorrectValueWhenOrientationIs270Degrees() {
        block.rotate();
        block.rotate();
        block.rotate();
        assertEquals(100, block.leftEdgeAfterRotate(), 0.1);
    }
}
