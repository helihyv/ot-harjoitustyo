package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Block;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Heli Hyvättinen
 */
public class TBlock extends Block {

    public TBlock(double xCoordinate, double tileWidth) {
        super(tileWidth);

        tiles[0] = new TetrisTile(xCoordinate, tileWidth, tileWidth);
        tiles[1] = new TetrisTile(xCoordinate + tileWidth, tileWidth, tileWidth);
        tiles[2] = new TetrisTile(xCoordinate + 2 * tileWidth, tileWidth, tileWidth);
        tiles[3] = new TetrisTile(xCoordinate + tileWidth, 0, tileWidth);
    }

    @Override
    protected void updateOrientation() {

        double y = tiles[1].getYCoordinate();
        double x = tiles[1].getXCoordinate();

        switch (orientation) {
            case 0:
                setTilePositions(x - tileWidth, y, x, y, x + tileWidth, y, x, y - tileWidth);
                break;
            case 90:
                setTilePositions(x, y - tileWidth, x, y, x, y + tileWidth, x + tileWidth, y);
                break;
            case 180:
                setTilePositions(x + tileWidth, y, x, y, x - tileWidth, y, x, y + tileWidth);
                break;

            case 270:
                setTilePositions(x, y + tileWidth, x, y, x, y - tileWidth, x - tileWidth, y);

        }
    }

    @Override
    public double leftEdge() {

        if (orientation <= 90) {
            return tiles[0].getXCoordinate();
        }
        if (orientation == 180) {
            return tiles[2].getXCoordinate();
        }
        return tiles[3].getXCoordinate();

    }

    @Override
    public double rightEdge() {
        if (orientation >= 180) {
            return tiles[0].getXCoordinate() + tileWidth;
        }
        if (orientation == 90) {
            return tiles[3].getXCoordinate() + tileWidth;
        }
        return tiles[2].getXCoordinate() + tileWidth;

    }

    @Override
    public double bottomEdge(double columnLeftEdge) {

        if (columnLeftEdge == tiles[0].getXCoordinate()) {
            if (orientation != 90) {
                return tiles[0].getYCoordinate() + tileWidth;
            }

            return tiles[2].getYCoordinate() + tileWidth;
        }

        if (columnLeftEdge == tiles[3].getXCoordinate()) {

            if (orientation != 0) {
                return tiles[3].getYCoordinate() + tileWidth;

            }
            return tiles[1].getYCoordinate() + tileWidth;

        }

        if (columnLeftEdge == tiles[2].getXCoordinate()) {
            return tiles[2].getYCoordinate() + tileWidth;
        }

        return 0;
    }

    @Override
    public ArrayList<Area> freeAreasNeededToMoveLeft() {
        ArrayList<Area> neededAreas = new ArrayList<>();

        if (orientation != 90) {
            neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() - tileWidth, 1));
        } else {
            neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() - tileWidth, 3));
        }

        if (orientation % 270 == 0) {
            neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() - tileWidth, 1));
        }

        if (orientation >= 180) {
            neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[2].getXCoordinate() - tileWidth, 1));
        }

        return neededAreas;
    }

    @Override
    public ArrayList<Area> freeAreasNeededToMoveRight() {

        ArrayList neededAreas = new ArrayList<>();

        if (orientation != 270) {
            neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[3].getXCoordinate() + tileWidth, 1));
        } else {
            neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[2].getXCoordinate() + tileWidth, 3));
        }

        if (orientation <= 180) {
            neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[2].getXCoordinate() + tileWidth, 1));
        }

        if (orientation == 90 || orientation == 180) {
            neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[0].getXCoordinate() + tileWidth, 1));
        }

        return neededAreas;
    }

    @Override
    public ArrayList<Area> freeAreasNeededToRotate() {
        ArrayList neededAreas = new ArrayList<>();

        switch (orientation) {
            case 0:
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[0].getXCoordinate(), 1));
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[2].getXCoordinate(), 1));
                neededAreas.add(new Area(tiles[1].getYCoordinate() + tileWidth, tiles[1].getXCoordinate(), tiles[1].getYCoordinate() + 2 * tileWidth, tiles[2].getXCoordinate() + tileWidth));
                break;
            case 90:
                neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[3].getXCoordinate(), 1));
                neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[3].getXCoordinate(), 1));
                neededAreas.add(createTileWideArea(tiles[1].getYCoordinate(), tiles[1].getXCoordinate() - tileWidth, 2));
                break;
            case 180:
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[0].getXCoordinate(), 1));
                neededAreas.add(createTileWideArea(tiles[3].getYCoordinate(), tiles[2].getXCoordinate(), 1));
                neededAreas.add(new Area(tiles[2].getYCoordinate() - tileWidth, tiles[2].getXCoordinate(), tiles[2].getYCoordinate(), tiles[0].getXCoordinate()));
                break;
            case 270:
                neededAreas.add(createTileWideArea(tiles[0].getYCoordinate(), tiles[3].getXCoordinate(), 1));
                neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[3].getXCoordinate(), 1));
                neededAreas.add(createTileWideArea(tiles[2].getYCoordinate(), tiles[2].getXCoordinate() + tileWidth, 2));
                break;

        }
        return neededAreas;
    }

    @Override
    public double leftEdgeAfterRotate() {
        
        if (orientation == 0) {
            return leftEdge() + tileWidth;
        }
        
        if (orientation == 90) {
            return leftEdge() - tileWidth;
        }
        
        return leftEdge(); 
    }

    @Override
    public double rightEdgeAfterRotate() {
        if (orientation == 180) {
            return rightEdge() - tileWidth;
        }
        
        if (orientation == 270) {
            return rightEdge() + tileWidth;
        }
        
        return rightEdge();
    }
    
    

}
