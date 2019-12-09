/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.domain;

import java.util.ArrayList;

/**
 *
 * @author Heli Hyvättinen
 */
public class MirrorSBlock extends Block {

    public MirrorSBlock(double xCoordinate, double tileWidth) {
        super(tileWidth);

        tiles[0] = new TetrisTile(xCoordinate, tileWidth, tileWidth);
        tiles[1] = new TetrisTile(xCoordinate + tileWidth, tileWidth, tileWidth);
        tiles[2] = new TetrisTile(xCoordinate + tileWidth, 2 * tileWidth, tileWidth);
        tiles[3] = new TetrisTile(xCoordinate + tileWidth * 2, 2 * tileWidth, tileWidth);

    }

    @Override
    protected void updateOrientation() {

        double y = tiles[1].getYCoordinate();
        double x = tiles[1].getXCoordinate();

        if (orientation % 180 == 0) {

            tiles[0].setYCoordinate(y);
            tiles[0].setXCoordinate(x - tileWidth);
            tiles[2].setYCoordinate(y + tileWidth);
            tiles[2].setXCoordinate(x);
            tiles[3].setYCoordinate(y + tileWidth);
            tiles[3].setXCoordinate(x + tileWidth);

        } else {
            tiles[0].setYCoordinate(y - tileWidth);
            tiles[0].setXCoordinate(x);
            tiles[2].setYCoordinate(y);
            tiles[2].setXCoordinate(x - tileWidth);
            tiles[3].setYCoordinate(y + tileWidth);
            tiles[3].setXCoordinate(x - tileWidth);
        }

    }

    @Override
    public double leftEdge() {

        if (orientation % 180 == 0) {
            return tiles[0].getXCoordinate();
        } else {
            return tiles[3].getXCoordinate();
        }

    }

    @Override
    public double rightEdge() {
        if (orientation % 180 == 0) {
            return tiles[3].getXCoordinate() + tileWidth;
        }

        return tiles[0].getXCoordinate() + tileWidth;

    }

    @Override
    public double bottomEdge(double columnLeftEdge) {

        if (columnLeftEdge == tiles[3].getXCoordinate()) {
            return tiles[3].getYCoordinate() + tileWidth;
        }

        if (orientation % 180 == 0) {

            if (columnLeftEdge == tiles[0].getXCoordinate()) {
                return tiles[0].getYCoordinate() + tileWidth;
            }

            if (columnLeftEdge == tiles[2].getXCoordinate()) {
                return tiles[2].getYCoordinate() + tileWidth;
            }
        } else if (columnLeftEdge == tiles[1].getXCoordinate()) {
            return tiles[1].getYCoordinate() + tileWidth;
        }

        return 0;

    }

    @Override
    public ArrayList<Area> freeAreasNeededToMoveLeft() {
        ArrayList<Area> neededAreas = new ArrayList<>();

        neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() - tileWidth, 1));

        int areaHeight = orientation % 180 == 0 ? 1 : 2;

        neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[2].getXCoordinate() - tileWidth, areaHeight));

        return neededAreas;
    }

    @Override
    public ArrayList<Area> freeAreasNeededToMoveRight() {
        ArrayList<Area> neededAreas = new ArrayList<>();

        neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() + tileWidth, 1));

        if (orientation % 180 == 0) {
            neededAreas.add(createTileWideArea(tiles[1].getYCoordinate(), tiles[1].getXCoordinate() + tileWidth, 1));
        } else {
            neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() + tileWidth, 2));
        }

        return neededAreas;
    }

    @Override
    public ArrayList<Area> freeAreasNeededToRotate() {
        ArrayList<Area> neededAreas = new ArrayList<>();

        if (orientation % 180 == 0) {
            neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[0].getXCoordinate(), 1));
            neededAreas.add(new Area(tiles[0].getYCoordinate() - tileWidth, tiles[0].getXCoordinate(), tiles[1].getYCoordinate(), tiles[1].getXCoordinate() + tileWidth));
        } else {
            neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[2].getXCoordinate(), 1));
            neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), +tiles[0].getXCoordinate() + tileWidth, 3));
            neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() + tileWidth, 1));
        }

        return neededAreas;
    }

}
