package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Block;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.Tile;
import java.util.ArrayList;
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

        List<Tile> tileList = (List<Tile>) tiles.clone();
        return tileList;
    }

    public void addBlock(Block block) {
        for (int i = 0; i < 4; i++) {
            tiles.add(block.getTiles()[i]);
        }
    }

    public double topEdge(double rowBottomEdge, double columnLeftEdge) {

        double topEdge = Double.MAX_VALUE;

        for (Tile tile : tiles) {
            if (tile.getXCoordinate() == columnLeftEdge
                    && tile.getYCoordinate() >= rowBottomEdge) {
                if (tile.getYCoordinate() < topEdge) {
                    topEdge = tile.getYCoordinate();
                }
            }
        }

        return topEdge;
    }

    public boolean areAreasFree(ArrayList<Area> areas) {

        for (Area area : areas) {
            for (TetrisTile tile : tiles) {
                if (area.overlaps(tile)) {
                    System.out.println("Overlap");
                    return false;
                }
            }
        }

        return true;
    }

    public int removeFullRows() {

        if (tiles.isEmpty()) {
            return 0;
        }

        int[] tilesInRows = countTilesInRows();

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

        int[] tilesInRows = new int[gameAreaHeightAsInt + 1];

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
