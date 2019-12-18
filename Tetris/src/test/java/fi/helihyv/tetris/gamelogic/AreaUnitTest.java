package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Area;
import fi.helihyv.tetris.gamelogic.TetrisTile;
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
public class AreaUnitTest {

    Area area;

    @Before
    public void setUp() {

        area = new Area(100, 200, 300, 400);
    }

    @Test
    public void areaOverlapsReturnsTrueIfTileIsInsideArea() {

        TetrisTile tile = new TetrisTile(320, 160, 20);

        assertTrue(area.overlaps(tile));
    }

    @Test
    public void areaOverLapsReturnsFalseIfTileIsOutsideArea() {
        TetrisTile tile = new TetrisTile(1000, 1000, 20);

        assertFalse(area.overlaps(tile));
    }
}
