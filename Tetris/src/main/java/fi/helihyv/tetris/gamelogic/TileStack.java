package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.Block;
import fi.helihyv.tetris.gamelogic.TetrisTile;
import fi.helihyv.tetris.gamelogic.Tile;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka kuvaa Tetris-pelin tiilipinoa, johon palikat pudottuaan päätyvät.
 *
 * @author Heli Hyvättinen
 */
public class TileStack {

    private ArrayList<TetrisTile> tiles;
    private double gameAreaWidth;
    private double gameAreaHeight;

    /**
     * Luokan konstruktori luo uuden tiilipinon ja saa parametreikseen
     * pelialueen koon
     *
     * @param gameAreaWidth pelialueen leveys loogisina pikseleinä
     * @param gameAreaHeight pelialueen korkeus loogisina pikseleinä
     */
    public TileStack(double gameAreaWidth, double gameAreaHeight) {

        tiles = new ArrayList<>();
        this.gameAreaWidth = gameAreaWidth;
        this.gameAreaHeight = gameAreaHeight;

    }

    /**
     * Metodi palauttaa tiilipinoon kuuluvat tiilet
     *
     * @return tiilipinon tiilet listana Tile-olioita
     */
    public List<Tile> getTiles() {

        List<Tile> tileList = (List<Tile>) tiles.clone();
        return tileList;
    }

    /**
     * Metodi lisää annetun palikan tiilet tiilipinoon
     *
     * @param block palikka, jonka tiilet lisätään tiilipinoon
     */
    public void addBlock(Block block) {
        for (int i = 0; i < 4; i++) {
            tiles.add(block.getTiles()[i]);
        }
    }

    /**
     * Metodi palauttaa tiilipinon ylimmän kohdan y-koordinaation annetussa
     * vaakasuuntaisessa kohdassa, annetun rajakorkeuden alapuolella
     *
     * @param rowBottomEdge y-koordinaatti, jonka alapuoliset osat tiilipinoa
     * huomioidaan
     * @param columnLeftEdge Kun pelialue jaetaan paliknalevyisiin sarakkeisiin,
     * tällaisen sarakkeen vasen reuna loogisina pikseleinä
     * @return tiilipinon korkeimman kohdan y-koordinaatti annetussa kohdassa
     * Jos kohdassa ei ole tiiliä palautetaan pelialueen alareunan
     * y-koordinaatti
     */
    public double topEdge(double rowBottomEdge, double columnLeftEdge) {

        double topEdge = gameAreaHeight;

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

    /**
     * Metodi palauttaa tiedon, ovatko kaikki parametrissa annetut alueet
     * vapaita Jos alue menee osittainkaan päällekkäin jonkin tiilipinon tiilen
     * kanssa, se ei ole vapaa.
     *
     * @param areas Lista Area-olioita, jotka kuvaavat alueita, joiden vapaana
     * oleminen tarkistetaan
     * @return true: kaikki tutkittavat alueet olivat kokonaan vapaita, false:
     * ainakin jokin alueista ei ollut kokonaan vapaa
     */
    public boolean areAreasFree(ArrayList<Area> areas) {

        for (Area area : areas) {
            for (TetrisTile tile : tiles) {
                if (area.overlaps(tile)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Metodi poistaa täyteen tulleet rivit ja siirtää niiden yläpouolella
     * olleet rivit alaspäin
     *
     * @return poistettujen tiilien määrä
     */
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
