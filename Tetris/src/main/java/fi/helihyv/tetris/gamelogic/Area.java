/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.TetrisTile;

/**
 *
 * @author Heli Hyvättinen
 */
class Area {

    private double topY;
    private double leftX;
    private double bottomY;
    private double rightX;

    Area(double topY, double leftX, double bottomY, double rightX) {
        this.topY = topY;
        this.leftX = leftX;
        this.bottomY = bottomY;
        this.rightX = rightX;
    }

    boolean overlaps(TetrisTile tile) {

        return ((tile.getXCoordinate() + tile.getWidth() > leftX
                && tile.getXCoordinate() < rightX)
                && (tile.getYCoordinate() + tile.getWidth() > topY
                && tile.getYCoordinate() < bottomY));
    }

}
