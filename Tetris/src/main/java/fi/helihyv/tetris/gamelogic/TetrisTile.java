package fi.helihyv.tetris.gamelogic;

/**
 * Luokka kuvaa Tetris-pelin yksittäistä tiiltä. Kukin palikka koostuu neljästä
 * neliömäisestä tiilestä, jotka siiŕtyvät pudottuaan tiilipinoon. Pelilogiikan
 * luokkien, jotka liikuttavat tiiliä suoraan, on tunnettava tämä luokka, sillä
 * käyttöliittymää vasten tarkoitettu rajapinta Tile ei sisällä palikkaa
 * liikuttavia metodeja.
 *
 * @author Heli Hyvättinen
 */
public class TetrisTile implements Tile {

    private double x;
    private double y;

    private double width;

    /**
     * Luokan konstruktori luo uuden tiilen ja saa paramtereikseen tiilen
     * sijainnin ja koon
     *
     * @param x tiilen vasemman yläkulma x-koordinaatti
     * @param y tiilen vasemman yläkulman y-koordinaatti
     * @param width tiilen leveys/korkeus
     */
    public TetrisTile(double x, double y, double width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    /**
     * Metodi siirtää tiilen yhden tiilenleveyden vasemmalle
     */
    public void moveLeft() {
        this.x -= this.width;
    }

    /**
     * Metodi siirtää tiilen yhden tiilenleveyden verran oikealle
     */
    public void moveRight() {
        this.x += this.width;
    }

    /**
     * Metodi siirtää tiilen yhden loogisen pikselin alaspäin
     */
    public void moveDown() {
        this.y += 1;
    }

    @Override
    public double getXCoordinate() {
        return x;
    }

    @Override
    public double getYCoordinate() {
        return y;
    }

    public void setYCoordinate(double y) {
        this.y = y;
    }

    public void setXCoordinate(double x) {
        this.x = x;
    }

    @Override
    public double getWidth() {
        return width;

    }

    /**
     * Metodi siirtää tiiltä yhden tiilenkorkeuden verran alaspäin
     */
    public void moveDownByTileWidth() {
        this.y += width;
    }

}
