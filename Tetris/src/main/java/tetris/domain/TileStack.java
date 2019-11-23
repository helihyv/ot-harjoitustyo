

package tetris.domain;

import java.util.ArrayList;
import java.util.HashMap;


public class TileStack {
    
    private ArrayList<Tile> tiles;

    public TileStack() {
    
        tiles = new ArrayList<>();
    }
    
    
    
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    
    public void addBlock(Block block) {
        for (int i = 0; i < 4; i++) {
            tiles.add(block.getTiles()[i]);
            System.out.println(block.getTiles()[i].getYCoordinate());
            System.out.println(block.getTiles()[i].getXCoordinate());
        }
    }
    
    public double topEdge(double columnLeftEdge) {
        
        double topEdge = Double.MAX_VALUE;
        
        for (Tile tile : tiles) {
            if (tile.getXCoordinate() == columnLeftEdge) {
                if (tile.getYCoordinate() < topEdge) {
                    topEdge = tile.getYCoordinate();
                }
            }
        }
        
        return topEdge;
    }
    
    public boolean areSlotsFree(HashMap<Double,Double> slots) {
        
        System.out.println("UUsi painallus");
        
        
        
        for (Tile tile : tiles) {
            
            System.out.println(tile.getYCoordinate());
            System.out.println(tile.getXCoordinate());
            
            if (slots.containsKey(tile.getYCoordinate())) {
            
             
             System.out.println(Double.compare(slots.get(tile.getYCoordinate()), tile.getXCoordinate())); 
            
            }
            if (slots.containsKey(tile.getYCoordinate())  &&
                    Double.compare(slots.get(tile.getYCoordinate()), tile.getXCoordinate()) == 0 ) {
                System.out.println(Double.compare(slots.get(tile.getYCoordinate()), tile.getXCoordinate()));   
                return false;
                
            }

        }
        
        return true;
    } 
    
}
