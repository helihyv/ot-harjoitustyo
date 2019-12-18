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
import fi.helihyv.tetris.gamelogic.SquareBlock;

public class SquareBlockTest {

    private SquareBlock block;

    private double[] startXPositions;

    @Before
    public void setUp() {

        block = new SquareBlock(100, 20);

        startXPositions = new double[4];
        for (int i = 0; i < 4; i++) {
            startXPositions[i] = block.getTiles()[i].getXCoordinate();
        }
    }

    @Test
    public void moveLeftMovesAllTilesCorrectDistanceLeft() {

        block.moveLeft();

        for (int i = 0; i < 4; i++) {
            assertEquals(
                    startXPositions[i] - 20,
                    block.getTiles()[i].getXCoordinate(),
                    0.1
            );
        }
    }

    @Test
    public void moveRightMovesAllTilesCorrectDistanceRight() {

        block.moveRight();

        for (int i = 0; i < 4; i++) {
            assertEquals(
                    startXPositions[i] + 20,
                    block.getTiles()[i].getXCoordinate(),
                    0.1
            );
        }
    }
}
