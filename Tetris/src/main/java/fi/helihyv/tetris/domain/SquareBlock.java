 
package fi.helihyv.tetris.domain;

import java.util.HashMap;

/**
 *
 * @author Heli Hyvättinen
 */
public class SquareBlock extends Block {

    public SquareBlock(double xCoordinate, double tileWidth) {
        super(tileWidth);
        tiles[0] = new TetrisTile(xCoordinate, 0, tileWidth);
        tiles[1] = new TetrisTile(xCoordinate, tileWidth, tileWidth);
        tiles[2] = new TetrisTile(xCoordinate + tileWidth, 0 , tileWidth);
        tiles[3] = new TetrisTile(xCoordinate + tileWidth, tileWidth, tileWidth);
    } 
    
    @Override
    public void rotate() {
        
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
        if (columnLeftEdge == tiles[0].getXCoordinate() || 
                columnLeftEdge == tiles[2].getXCoordinate()) {

            return tiles[3].getYCoordinate() + tileWidth;
        } else {
            return  0;
        }
    }

    @Override
    public HashMap<Double, Double> freeSlotsNeededToMoveLeft() {
        
        HashMap<Double,Double> coordinates = new HashMap<>();
        
        double freeSlotXCoordinate = tiles[0].getXCoordinate() - tileWidth;
        double topEdge = tiles[0].getYCoordinate();
                      
        return freeSlotsNeededToMove(topEdge, freeSlotXCoordinate);
    }

    @Override
    public HashMap<Double, Double> freeSlotsNeededToMoveRight() {
          HashMap<Double,Double> coordinates = new HashMap<>();
        
        double freeSlotXCoordinate = tiles[2].getXCoordinate() +  tileWidth;
        double topEdge = tiles[2].getYCoordinate();
        
        return freeSlotsNeededToMove(topEdge, freeSlotXCoordinate);
               
    }    
    
   private HashMap<Double, Double> freeSlotsNeededToMove(double bloxkTopEdge, double freeSlotXCoordinate) {
        
        HashMap<Double,Double> coordinates = new HashMap<>();
        
        System.out.println("Calculating needed free slots");
        
        System.out.println(bloxkTopEdge);
        System.out.println(bloxkTopEdge % tileWidth);
        System.out.println(bloxkTopEdge - bloxkTopEdge % tileWidth);
        System.out.println(bloxkTopEdge - bloxkTopEdge % tileWidth +  tileWidth);
        System.out.println(bloxkTopEdge - bloxkTopEdge % tileWidth + 2 * tileWidth);                      
        coordinates.put(bloxkTopEdge - bloxkTopEdge % tileWidth , freeSlotXCoordinate);
        coordinates.put(bloxkTopEdge - bloxkTopEdge % tileWidth +  tileWidth, freeSlotXCoordinate);
        
        if (bloxkTopEdge % tileWidth != 0) {
            
            coordinates.put(bloxkTopEdge - bloxkTopEdge % tileWidth + 2 * tileWidth, freeSlotXCoordinate);
            
        }
        
        return coordinates;
    }   
}
