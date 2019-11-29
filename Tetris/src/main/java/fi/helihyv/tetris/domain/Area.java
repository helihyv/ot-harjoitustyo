/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.domain;

/**
 *
 * @author Heli Hyvättinen
 */
public class Area {
    
    private double topY;
    private double leftX;
    private double bottomY;
    private double rightX;

    public Area(double topY, double leftX, double bottomY, double rightX) {
        this.topY = topY;
        this.leftX = leftX;
        this.bottomY = bottomY;
        this.rightX = rightX;
    }
    
    public boolean overlaps(TetrisTile tile) {
        
        System.out.println("topy" + topY);
        System.out.println("leftX" + leftX);
        System.out.println("bottomy " + bottomY);
        System.out.println("rightX" + rightX);
        System.out.println("tileY " + tile.getYCoordinate());
        System.out.println("tilex " + tile.getXCoordinate());

        
       return ((tile.getXCoordinate() + tile.getWidth() > leftX 
               && tile.getXCoordinate() < rightX) 
           && (tile.getYCoordinate() + tile.getWidth() > topY 
                && tile.getYCoordinate() < bottomY)); 
    }
    
}
