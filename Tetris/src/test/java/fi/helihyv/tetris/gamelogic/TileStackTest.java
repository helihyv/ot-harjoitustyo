package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.TileStack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import fi.helihyv.tetris.gamelogic.SquareBlock;
import java.util.ArrayList;

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

        tileStack = new TileStack(200, 200);
    }

    @Test
    public void tileStackRemoveFullRowsRemovesFullRow() {

        for (int i = 0; i < 5; i++) {
            tileStack.addBlock(new SquareBlock(40.0 * i, 20.0));
        }

        tileStack.removeFullRows();

        assertTrue(tileStack.getTiles().isEmpty());
    }

    @Test
    public void tileStackRemoveFullRowsReturnsCorrectNumberOfRemovedTiles() {

        for (int i = 0; i < 5; i++) {
            tileStack.addBlock(new SquareBlock(40.0 * i, 20.0));
        }

        assertEquals(20, tileStack.removeFullRows());
    }

    @Test
    public void tileStackTopEdgeReturnsGameHeightIfNoBlocksAdded() {
        assertEquals(200, tileStack.topEdge(0, 0), 0.1);
        System.out.println(tileStack.topEdge(0, 0));
    }

    @Test
    public void tileStackTopEdgeReturnsCorrectHeightWithBlockAdded() {

        Block block = new SquareBlock(0, 20);

        for (int i = 0; i < 180; i++) {
            block.moveDown();
        }
        tileStack.addBlock(block);

        assertEquals(180, tileStack.topEdge(0, 0), 0.1);
    }

    @Test
    public void tileStackTopEdgeReturnsCorrectHeightWithRowBottomEdgeBetweenTiles() {

        Block block1 = new LBlock(0, 20);
        Block block2 = new TBlock(0, 20);

        for (int i = 0; i < 60; i++) {
            block2.moveDown();
        }
        tileStack.addBlock(block1);
        tileStack.addBlock(block2);

        assertEquals(40, tileStack.topEdge(40, 0), 0.1);
    }

    @Test
    public void tileStackAreAreasFreeReturnsTrueIfNoBlocksAdded() {

        ArrayList<Area> areas = new ArrayList();
        areas.add(new Area(105, 10, 110, 15));
        assertTrue(tileStack.areAreasFree(areas));
    }

    @Test
    public void tileStackAreAreasFreeReturnsFalseIfTileInsideArea() {

        tileStack.addBlock(new SquareBlock(100, 20));

        ArrayList<Area> areas = new ArrayList();
        areas.add(new Area(10, 105, 15, 110));

        assertFalse(tileStack.areAreasFree(areas));
    }

    @Test
    public void tileStackAreAreasFreeReturnsTrueIfNoTilesInsideArea() {

        tileStack.addBlock(new SquareBlock(150, 20));

        ArrayList<Area> areas = new ArrayList();
        areas.add(new Area(10, 105, 15, 110));

        assertTrue(tileStack.areAreasFree(areas));
    }

    @Test
    public void tileStackAreAreasFreeReturnsTrueIfBlocksNextToArea() {
        tileStack.addBlock(new SquareBlock(100, 20));
        Block block = new SquareBlock(100, 20);
        for (int i = 0; i < 60; i++) {
            block.moveDown();

        }
        Block block2 = new SquareBlock(120, 20);
        for (int i = 0; i < 40; i++) {
            block2.moveDown();
        }
        ArrayList<Area> areas = new ArrayList<>();
        areas.add(new Area(40, 100, 60, 120));

        assertTrue(tileStack.areAreasFree(areas));
    }
}
