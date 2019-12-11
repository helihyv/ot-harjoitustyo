package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Block;
import java.util.ArrayList;

/**
 *
 * @author Heli Hyvättinen
 */
public class SquareBlock extends Block {

    public SquareBlock(double xCoordinate, double tileWidth) {
        super(tileWidth);
        tiles[0] = new TetrisTile(xCoordinate, 0, tileWidth);
        tiles[1] = new TetrisTile(xCoordinate, tileWidth, tileWidth);
        tiles[2] = new TetrisTile(xCoordinate + tileWidth, 0, tileWidth);
        tiles[3] = new TetrisTile(xCoordinate + tileWidth, tileWidth, tileWidth);
    }

    @Override
    public void updateOrientation() {

    }

    @Override
    public double leftEdge() {
        return tiles[0].getXCoordinate();
    }

    @Override
    public double rightEdge() {
        return tiles[2].getXCoordinate() + tileWidth - 1;
    }

    @Override
    public double bottomEdge(double columnLeftEdge) {
        if (columnLeftEdge == tiles[0].getXCoordinate()
                || columnLeftEdge == tiles[2].getXCoordinate()) {

            return tiles[3].getYCoordinate() + tileWidth;
        } else {
            return 0;
        }
    }

    @Override
    public ArrayList<Area> freeAreasNeededToMoveLeft() {

        ArrayList<Area> neededAreas = new ArrayList<>();

        neededAreas.add(new Area(
                tiles[0].getYCoordinate(),
                tiles[0].getXCoordinate() - tileWidth,
                tiles[0].getYCoordinate() + 2 * tileWidth,
                tiles[0].getXCoordinate()
        ));

        return neededAreas;
    }

    @Override
    public ArrayList<Area> freeAreasNeededToMoveRight() {

        ArrayList<Area> neededAreas = new ArrayList<>();

        neededAreas.add(new Area(
                tiles[1].getYCoordinate(),
                tiles[3].getXCoordinate() + tileWidth,
                tiles[3].getYCoordinate() + tileWidth,
                tiles[3].getXCoordinate() + 2 * tileWidth
        ));

        return neededAreas;

    }

    @Override
    public ArrayList<Area> freeAreasNeededToRotate() {
        return new ArrayList<>();
    }

    @Override
    public double leftEdgeAfterRotate() {
        return leftEdge();
    }

    @Override
    public double rightEdgeAfterRotate() {
        return rightEdge();
    }

}
