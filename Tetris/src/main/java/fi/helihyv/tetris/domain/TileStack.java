

package fi.helihyv.tetris.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TileStack {
    
    private ArrayList<TetrisTile> tiles;
    private double gameAreaWidth;
    private double gameAreaHeight;

    public TileStack(double gameAreaWidth, double gameAreaHeight) {
    
        tiles = new ArrayList<>();
        this.gameAreaWidth = gameAreaWidth;
        this.gameAreaHeight = gameAreaHeight;
        
    }
    
    
    
    public List<Tile> getTiles() {
       
        List<Tile> tileList =  (List<Tile>) tiles.clone();
        return tileList;
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
    
    public boolean areSlotsFree(HashMap<Double, Double> slots) {

        for (Tile tile : tiles) {
            
            if (slots.containsKey(tile.getYCoordinate())  &&
                    Double.compare(slots.get(tile.getYCoordinate()), tile.getXCoordinate()) == 0) {
   
                return false;              
            }
        }
        
        return true;
    } 
    
    public int removeFullRows() {

        if (tiles.isEmpty()) {
            return 0;
        }

        int [] tilesInRows = countTilesInRows();

        int tilesRemoved = 0;
        
        double tilesInRow = gameAreaWidth / tiles.get(0).getWidth();
        
        for (int i = 0; i < gameAreaHeight; i++) {

            if (!tiles.isEmpty() && tilesInRows[i] >= tilesInRow) {
                removeRow(i);
                tilesRemoved += tilesInRow; 
            }
        }
        
        return tilesRemoved;
    }
        
    private int[] countTilesInRows() {

        int gameAreaHeightAsInt = (int) Math.round(gameAreaHeight);

        int [] tilesInRows = new int [gameAreaHeightAsInt + 1];


        for (Tile tile : tiles) {
            int y = (int) Math.round(tile.getYCoordinate());
            tilesInRows[y]++;
        }

        return tilesInRows;
    }
        
    private void removeRow(int row) {

        for (int i = tiles.size() - 1; i >= 0; i--) {

            if (tiles.get(i).getYCoordinate() == row) {
                tiles.remove(tiles.get(i));
            } 
        }

        for (TetrisTile tile : tiles) {

            if (tile.getYCoordinate() < row) {
                tile.moveDownByTileWidth();
            }
        }
    }
    
}
