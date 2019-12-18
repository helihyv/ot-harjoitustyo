package fi.helihyv.tetris.gamelogic;

import fi.helihyv.tetris.gamelogic.TetrisTile;

/**
 * Luokka kuvaa pelialueen nelikulmaista osa-aluetta. Käýtetään vapaiksi
 * tarvittavien alueiden välittämiseen
 *
 * @author Heli Hyvättinen
 */
class Area {

    private double topY;
    private double leftX;
    private double bottomY;
    private double rightX;

    /**
     * Konstruktorissa asetetaan alueen rajat
     *
     * @param topY – alueen yläreuna
     * @param leftX – alueen vasen reuna
     * @param bottomY – alueen alareuna
     * @param rightX – alueen oikea reuna
     */
    Area(double topY, double leftX, double bottomY, double rightX) {
        this.topY = topY;
        this.leftX = leftX;
        this.bottomY = bottomY;
        this.rightX = rightX;
    }

    /**
     * Metodi palauttaa tiedon siitä, onko parametrinä saatu tiili kokonaan tai
     * osittain alueen sisällä. Jos vain reunat kohtaavat, tiilen ei katsota
     * olevan alueen sisällä.
     *
     */
    boolean overlaps(TetrisTile tile) {

        return ((tile.getXCoordinate() + tile.getWidth() > leftX
                && tile.getXCoordinate() < rightX)
                && (tile.getYCoordinate() + tile.getWidth() > topY
                && tile.getYCoordinate() < bottomY));
    }

}
