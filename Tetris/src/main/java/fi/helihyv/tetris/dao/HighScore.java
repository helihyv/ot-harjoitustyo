package fi.helihyv.tetris.dao;

/**
 * Luokka kuvaa parhaiden tulosten listalle kuuluvaa tulosta Tuloksella on
 * pelaajan nimi ja pistemäärä
 *
 * @author Heli Hyvättinen
 */
public class HighScore implements Comparable<HighScore> {

    private String name;
    private long score;

    /**
     * Luo uuden tuloksen annetuilla nimellä ja pisteillä Annettu nimi
     * typistetään enintään 30 merkkiin
     *
     * @param name Pelaajan nimi
     * @param score Pisteet
     */
    public HighScore(String name, long score) {
        this.score = score;
        if (name.length() > 30) {
            name = name.substring(0, 30);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getScore() {
        return score;
    }

    /**
     * Metodi vertaa tulosta toiseen tulokseen Vertailulla tulokset järjestyvät
     * suoraan suurimmasta pienimpään Vertailussa käytetään vain pisteiden
     * määrää
     *
     * @param t verrattava toinen saman luokan olio
     * @return 0, jos verrattavilla yhtä suuret pisteet, 1, jos verrattavalla
     * oliolla suuremmat pisteet, -1, jos oliolla itsellään suuremmat pisteet
     */
    @Override
    public int compareTo(HighScore t) {
        long difference = t.getScore() - this.score;
        if (difference > 0) {
            return 1;
        }
        if (difference < 0) {
            return -1;
        }
        return 0;

    }

}
