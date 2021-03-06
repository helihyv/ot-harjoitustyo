package fi.helihyv.tetris.gamelogic;

import java.util.ArrayList;

/**
 * Luokka kuvaa Tetris-pelin palikkaa. Abstrakti kantaluokka erimuotoisille
 * palikoille. Palikka koostuu neljästä neliömäisestä tiilestä.
 *
 * @author Heli Hyvättinen
 */
public abstract class Block {

    protected TetrisTile[] tiles;
    protected double tileWidth;
    protected int orientation;

    /**
     * Konstruktori luo tiilille taulukon ja asettaa tiilen koon ja asennon.
     * Alkuasentona aina 0 astetta.
     *
     * @param tileWidth Palikan tiilien leveys ja korkeus
     */
    public Block(double tileWidth) {
        this.tiles = new TetrisTile[4];
        this.tileWidth = tileWidth;
        this.orientation = 0;
    }

    /**
     * Metodi kääntää palikan 90 astetta myötäpäivään. Metodi asettaa palikan
     * asennon asteina ja kutsuu palikan tiilien sijainnin päivitystä.
     */
    public void rotate() {
        orientation += 90;
        if (orientation == 360) {
            orientation = 0;
        }

        updateOrientation();
    }

    /**
     * Metodi asettaa palikan tiilien sijainnin palikan asennon mukaiseksi
     */
    protected abstract void updateOrientation();

    /**
     * Metodi siirtää palikkaa yhden tiilenleveydeb verran vasemmalle
     */
    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            tiles[i].moveLeft();
        }
    }

    /**
     * Metodi siirtää palikkaa yhden tiilenleveyden verran oikealle
     */
    public void moveRight() {
        for (int i = 0; i < 4; i++) {
            tiles[i].moveRight();
        }
    }

    /**
     * Metodi siirtää palikkaa yhden loogisen pikselin verran alaspäin
     */
    public void moveDown() {
        for (int i = 0; i < 4; i++) {
            tiles[i].moveDown();
        }
    }

    /**
     * Metodi palauttaa palikan tiilet
     *
     * @return palikan tiilet taulukkona
     */
    public TetrisTile[] getTiles() {
        return tiles;
    }

    /**
     * Metodi asettaa palikan tiilien sijainnin parametriensa mukaisiksi
     *
     * @param x0 taulukon indeksissä 0 olevan tiilen x-koordinaatti
     * @param y0 taulukon indeksissä 0 olevan tiilen y-koordinaatti
     * @param x1 taulukon indeksissä 1 olevan tiilen x-koordinaatti
     * @param y1 taulukon indeksissä 1 olevan tiilen y-koordinaatti
     * @param x2 taulukon indeksissä 2 olevan tiilen x-koordinaatti
     * @param y2 taulukon indeksissä 2 olevan tiilen y-koordinaatti
     * @param x3 taulukon indeksissä 3 olevan tiilen x-koordinaatti
     * @param y3 taulukon indeksissä 3 olevan tiilen y-koordinaatti
     */
    protected void setTilePositions(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {

        tiles[0].setYCoordinate(y0);
        tiles[0].setXCoordinate(x0);
        tiles[1].setYCoordinate(y1);
        tiles[1].setXCoordinate(x1);
        tiles[2].setYCoordinate(y2);
        tiles[2].setXCoordinate(x2);
        tiles[3].setYCoordinate(y3);
        tiles[3].setXCoordinate(x3);
    }

    /**
     * Metodi palauttaa palikan vasemman reunan loogisina pikseleinä
     *
     * @return palikan vasemmanpuoleisimman tiilen vasen reuna
     */
    public abstract double leftEdge();

    /**
     * Metodi palauttaa palikan oikean reunan loogisina pikseleinä
     *
     * @return palikan oikeanpuoleisimman tiilen oikea reuna
     */
    public abstract double rightEdge();

    /**
     * Metodi palauttaa palikan alareunan loogisina pikseleinä annetussa
     * kohdassa
     *
     * @param columnLeftEdge Kun pelialue jaetaan vaakasuunnassa tiilen
     * levyisiin sarakkeisiin, tällaisen sarakkeen vasen reuna
     * @return palikan annetussa kohdassa olevista tiilistä alimman alareuna
     */
    public abstract double bottomEdge(double columnLeftEdge);

    /**
     * Metodi palauttaa alueet, joiden on oltava vapaina, jotta palikka voi
     * liikkua vasemmallae
     *
     * @return lista Area-olioita, jotka kuvaavat vapaiksi tarvittavia alueita
     */
    public abstract ArrayList<Area> freeAreasNeededToMoveLeft();

    /**
     * Metodi palauttaa alueet, joiden on oltava vapaina, jotta palikka voi
     * liikkua oikealle
     *
     * @return lista Area-olioita, jotka kuvaavat vapaiksi tarvittavia alueita
     */
    public abstract ArrayList<Area> freeAreasNeededToMoveRight();

    /**
     * Metodi palauttaa alueet, joiden on oltava vapaina, jotta palikka mahtuu
     * kääntymään
     *
     * @return lista Area-olioita, jotka kuvaavat vapaiksi tarvittavia alueita
     */
    public abstract ArrayList<Area> freeAreasNeededToRotate();

    /**
     * Metodi luo tiilenlevyisen alueen (Area-olion) ja saa parametreina alueen
     * sijainnin ja korkeuden
     *
     * @param topY alueen vasemman yläkulman y-koordinaatti
     * @param leftX alueen vasemman yläkulman x-koordinaatti
     * @param lengthInTiles alueen korkeus yksikkönä tiilenkorkeus
     * @return parametrien mukainen Area-olio
     */
    protected Area createTileWideArea(double topY, double leftX, int lengthInTiles) {

        return new Area(topY, leftX, topY + tileWidth * lengthInTiles, leftX + tileWidth);
    }

    /**
     * Metodi palauttaa X-koordinaatin, jossa palikan vasen reuna olisi
     * seuraavan käännön jälkeen
     */
    public abstract double leftEdgeAfterRotate();

    /**
     * Metodi palauttaa X-koordinaatin, jossa palikan oikea reuna olisi
     * seuraavan käännön jälkeen
     */
    public abstract double rightEdgeAfterRotate();

}
