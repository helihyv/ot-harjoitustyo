/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.domain;

import java.util.HashMap;

/**
 *
 * @author Heli Hyvättinen
 */
public class IBlock extends Block {
    

    public IBlock(double xCoordinate, double tileWidth) {
        super(tileWidth);
               
        for (int i = 0; i < 4; i++) {
            tiles[i] = new TetrisTile(xCoordinate + i * tileWidth, 0, tileWidth);
        }

    }
    
    

    @Override
    public void rotate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double leftEdge() {
        
        return tiles[0].getXCoordinate();
               
    }

    @Override
    public double rightEdge() {
       return tiles[3].getXCoordinate() + tileWidth;
    }

    @Override
    public double bottomEdge(double columnLeftEdge) {
        
        for (int i = 0; i < 4; i++) {
            if (tiles[i].getXCoordinate() == columnLeftEdge) {
                return tiles[3].getYCoordinate() + tileWidth;
            }
        }
        
        return 0;
      
    }

    @Override
    public HashMap<Double, Double> freeSlotsNeededToMoveLeft() {
        
        int extendDownwards = orientation % 180 == 0 ? 0 : 3;
        
        System.out.println(extendDownwards);
        
        return findSlotsNextToTile(tiles[0], true, extendDownwards);
        
    }

    @Override
    public HashMap<Double, Double> freeSlotsNeededToMoveRight() {
        
        int extendDownwards = orientation % 180 == 0 ? 0 : 3;
        
         return findSlotsNextToTile(tiles[3], false, extendDownwards);
    }
    
}
