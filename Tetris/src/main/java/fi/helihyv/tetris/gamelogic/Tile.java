/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.gamelogic;

/**
 * Rajapinnan toteuttava luokka kuvaa yksittäistä "tiiltä" Tetris-pelissä. Kukin
 * palikka koostuu neljästä neliömäisestä tiilestä, jotka siiŕtyvät pudottuaan
 * tiilipinoon. Käyttöliittymän riittää tietää luokasta tämä rajapinta.
 *
 * @author Heli Hyvättinen
 */
public interface Tile {

    /**
     * Metodi palauttaa tiilen vasemman yläkulman x-koordinaatin
     *
     * @return x-koordinaatti
     */
    public double getXCoordinate();

    /**
     * Metodi palauttaa tiilen vasemman yläkulman y-koordinaatin
     *
     * @return y-koordinaatti
     */
    public double getYCoordinate();

    /**
     * Metodi palauttaa tiilen leveyden, joka on samalla myös sen korkeus
     *
     * @return tiilen leveys/korkeus
     */
    public double getWidth();
}
