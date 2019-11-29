/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.domain;

import java.util.ArrayList;
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
    public void updateOrientation() {
        if (orientation % 180 == 0) {
            
            tiles[0].setYCoordinate(tiles[1].getYCoordinate());
            tiles[0].setXCoordinate(tiles[1].getXCoordinate() - tileWidth);
            tiles[2].setYCoordinate(tiles[1].getYCoordinate());
            tiles[2].setXCoordinate(tiles[1].getXCoordinate() + tileWidth);
            tiles[3].setYCoordinate(tiles[1].getYCoordinate());
            tiles[3].setXCoordinate(tiles[1].getXCoordinate() + 2 * tileWidth);
            
        } else {
          
            tiles[0].setYCoordinate(tiles[1].getYCoordinate() - tileWidth);
            tiles[0].setXCoordinate(tiles[1].getXCoordinate());
            tiles[2].setYCoordinate(tiles[1].getYCoordinate() + tileWidth);
            tiles[2].setXCoordinate(tiles[1].getXCoordinate());
            tiles[3].setYCoordinate(tiles[1].getYCoordinate() + 2 * tileWidth);
            tiles[3].setXCoordinate(tiles[1].getXCoordinate());
        }
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

    @Override
    public ArrayList<Area> freeAreasNeededToRotate() {
        
        ArrayList<Area> areas = new ArrayList();
        
        if (orientation % 180 == 0) {
            
            areas.add(new Area(
                    tiles[0].getYCoordinate()-tileWidth,
                    tiles[0].getXCoordinate(),
                    tiles[0].getYCoordinate(),
                    tiles[2].getXCoordinate()
            ));
            
            areas.add(new Area(
                    tiles[1].getYCoordinate() + tileWidth,
                    tiles[1].getXCoordinate(),
                    tiles[3].getYCoordinate() + 3 * tileWidth,
                    tiles[3].getXCoordinate() + tileWidth
            ));
            
        } else {
            
            areas.add(new Area(
                    tiles[1].getYCoordinate(),
                    tiles[1].getXCoordinate() - tileWidth,
                    tiles[3].getYCoordinate() + tileWidth,
                    tiles[3].getXCoordinate() 
            ));
            
            areas.add(new Area(
                    tiles[0].getYCoordinate(),
                    tiles[0].getXCoordinate() + tileWidth,
                    tiles[2].getYCoordinate(),
                    tiles[2].getXCoordinate() + 3 * tileWidth
            ));
            
        }
        
        return areas;
    }


    
    
    
}
