/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.gamelogic;

import java.util.List;

/**
 * Rajapinnan toteuttava luokka muodostaa tetris-pelin logiikan rungon
 * Käyttöliittymän riittää tietää luokasta tämä rajapinta
 *
 * @author Heli Hyvättinen
 */
public interface Game {

    /**
     * Metodi aloittaa pelin
     */
    public void startGame();

    /**
     * Metodi lopettaa pelin. Tätä metodia on kutsuttava ennen ohjelmasta
     * poistumista, jotta ajastin tulee asianmukaisesti sammutetuksi. Muuten
     * ajastimen säie jää toimimaan muun ohjelman sulkeutuessa.
     */
    public void stopGame();

    /**
     * Metodi palauttaa tiedon, onko peli päättynyt
     *
     * @return true: peli on päättynyt, false: peli on yhä käynnissä
     */
    public boolean hasGameEnded();

    /**
     * Metodi palauttaa pelialueen leveyden
     *
     * @return pelialueen leveys
     */
    public int getGameAreaWidth();

    /**
     * Metodi palauttaa pelialueen korkeuden
     *
     * @return pelialueen korkeus
     */
    public int getGameAreaHeight();

    /**
     * Metodi palauttaa pelissä olevat tiilet Ensimmäiset neljä tiiltä ovat
     * palikan tiilet, loput palikkapinon tiiliä
     *
     * @return Lista pelin tiilistä
     */
    public List<Tile> getTiles();

    /**
     * Metodi siirtää palikkaa yhden tiilenleveyden verran vasemmalle, jos se
     * mahtuu liikkumaan
     */
    public void moveBlockLeft();

    /**
     * Metodi siirtää palikkaa yhden tiilenleveyden verran oikealle, jos se
     * mahtuu liikkumaan
     */
    public void moveBlockRight();

    /**
     * Metodi palauttaa korkeuden, jolle palikkapinon pitää ulottua, jotta peli
     * päättyy
     *
     * @return palikkapinon korkeus, jolla peli päättyy
     */
    public double getGameOverHeight();

    /**
     * Metodi palauttaa pelaajan tämänhetkiset pisteet
     *
     * @return pelaajan pisteet
     */
    public long getScore();

    /**
     * Metodi pudottaa palikan nopeammin Putousnopeus viisinkertaistuu
     */
    public void dropBlock();

    /**
     * Metodi kääntää palikkaa 90 astetta myötäpäivään
     */
    public void rotateBlock();

}
