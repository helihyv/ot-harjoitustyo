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
public class MirrorLBlock extends Block {

    public MirrorLBlock(double xCoordinate, double tileWidth) {
        super(tileWidth);

        tiles[0] = new TetrisTile(xCoordinate + tileWidth, 0, tileWidth);
        tiles[1] = new TetrisTile(xCoordinate + tileWidth, tileWidth, tileWidth);
        tiles[2] = new TetrisTile(xCoordinate + tileWidth, 2 * tileWidth, tileWidth);
        tiles[3] = new TetrisTile(xCoordinate, 2 * tileWidth, tileWidth);
    }

    @Override
    protected void updateOrientation() {

        double x = tiles[1].getXCoordinate();
        double y = tiles[1].getYCoordinate();

        switch (orientation) {
            case 0:
                tiles[0].setYCoordinate(y - tileWidth);
                tiles[0].setXCoordinate(x);
                tiles[2].setYCoordinate(y + tileWidth);
                tiles[2].setXCoordinate(x);
                tiles[3].setYCoordinate(y + tileWidth);
                tiles[3].setXCoordinate(x - tileWidth);
                break;

            case 90:
                tiles[0].setYCoordinate(y);
                tiles[0].setXCoordinate(x + tileWidth);
                tiles[2].setYCoordinate(y);
                tiles[2].setXCoordinate(x - tileWidth);
                tiles[3].setYCoordinate(y - tileWidth);
                tiles[3].setXCoordinate(x - tileWidth);
                break;

            case 180:
                tiles[0].setYCoordinate(y + tileWidth);
                tiles[0].setXCoordinate(x);
                tiles[2].setYCoordinate(y - tileWidth);
                tiles[2].setXCoordinate(x);
                tiles[3].setYCoordinate(y - tileWidth);
                tiles[3].setXCoordinate(x + tileWidth);
                break;

            case 270:
                tiles[0].setYCoordinate(y);
                tiles[0].setXCoordinate(x - tileWidth);
                tiles[2].setYCoordinate(y);
                tiles[2].setXCoordinate(x + tileWidth);
                tiles[3].setYCoordinate(y + tileWidth);
                tiles[3].setXCoordinate(x + tileWidth);
                break;
        }

    }

    @Override
    public double leftEdge() {

        if (orientation == 0 || orientation == 90) {
            return tiles[3].getXCoordinate();
        }

        return tiles[0].getXCoordinate();
    }

    @Override
    public double rightEdge() {
        if (orientation < 180) {
            return tiles[0].getXCoordinate() + tileWidth;
        }

        return tiles[3].getXCoordinate() + tileWidth;
    }

    @Override
    public double bottomEdge(double columnLeftEdge) {

        switch (orientation) {
            case 0:
                if (columnLeftEdge == tiles[3].getXCoordinate()) {
                    return tiles[3].getYCoordinate() + tileWidth;
                }
                if (columnLeftEdge == tiles[2].getXCoordinate()) {
                    return tiles[2].getYCoordinate() + tileWidth;
                }
                return 0;

            case 90:
                if (columnLeftEdge == tiles[0].getXCoordinate() || columnLeftEdge == tiles[1].getXCoordinate() || columnLeftEdge == tiles[2].getXCoordinate()) {
                    return tiles[0].getYCoordinate() + tileWidth;
                }
                return 0;
            case 180:
                if (columnLeftEdge == tiles[0].getXCoordinate()) {
                    return tiles[0].getYCoordinate() + tileWidth;
                }
                if (columnLeftEdge == tiles[3].getXCoordinate()) {
                    return tiles[3].getYCoordinate() + tileWidth;
                }
                return 0;

            case 270:
                if (columnLeftEdge == tiles[0].getXCoordinate() || columnLeftEdge == tiles[1].getXCoordinate()) {
                    return tiles[0].getYCoordinate() + tileWidth;
                }
                if (columnLeftEdge == tiles[3].getXCoordinate()) {
                    return tiles[3].getYCoordinate() + tileWidth;
                }
                return 0;
        }
        return 0;
    }

    @Override
    public ArrayList<Area> freeAreasNeededToMoveLeft() {

        ArrayList<Area> neededAreas = new ArrayList<>();

        switch (orientation) {
            case 0:
                neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() - tileWidth, 2));
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() - tileWidth, 1));
                break;

            case 90:
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() - tileWidth, 2));
                break;

            case 180:
                neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[2].getXCoordinate() - tileWidth, 3));
                break;

            case 270:
                neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() - tileWidth, 1));
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() - tileWidth, 1));
        }

        return neededAreas;
    }

    @Override
    public ArrayList<Area> freeAreasNeededToMoveRight() {

        ArrayList<Area> neededAreas = new ArrayList<>();

        switch (orientation) {
            case 0:
                neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() + tileWidth, 3));
                break;

            case 90:
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() + tileWidth, 1));
                neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() + tileWidth, 1));
                break;

            case 180:
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() + tileWidth, 1));
                neededAreas.add(createTileWideArea(tiles[1].getYCoordinate(), tiles[1].getXCoordinate() + tileWidth, 2));
                break;

            case 270:
                neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[2].getXCoordinate() + tileWidth, 2));
                break;
        }
        return neededAreas;

    }

    @Override
    public ArrayList<Area> freeAreasNeededToRotate() {

        ArrayList<Area> neededAreas = new ArrayList<>();

        switch (orientation) {

            case 0:
                neededAreas.add(createTileWideArea(tiles[1].getYCoordinate(), tiles[3].getXCoordinate(), 1));
                neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() + tileWidth, 2));
                break;

            case 90:
                neededAreas.add(new Area(tiles[3].getYCoordinate(), tiles[1].getXCoordinate(), tiles[1].getYCoordinate(), tiles[0].getXCoordinate() + tileWidth));
                neededAreas.add(new Area(tiles[1].getYCoordinate() + tileWidth, tiles[1].getXCoordinate(), tiles[1].getYCoordinate() + 2 * tileWidth, tiles[0].getXCoordinate() + tileWidth));
                break;

            case 180:
                neededAreas.add(createTileWideArea(tiles[1].getYCoordinate(), tiles[1].getXCoordinate() - tileWidth, 2));
                neededAreas.add(createTileWideArea(tiles[1].getYCoordinate(), tiles[1].getXCoordinate() + tileWidth, 1));
                break;

            case 270:
                neededAreas.add(new Area(tiles[0].getYCoordinate() - tileWidth, tiles[0].getXCoordinate(), tiles[0].getYCoordinate(), tiles[2].getXCoordinate()));
                neededAreas.add(new Area(tiles[3].getYCoordinate(), tiles[0].getXCoordinate(), tiles[3].getYCoordinate() + tileWidth, tiles[3].getXCoordinate()));
        }
        return neededAreas;
    }

}
