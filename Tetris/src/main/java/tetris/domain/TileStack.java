

package tetris.domain;

import java.util.ArrayList;


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
    
}
