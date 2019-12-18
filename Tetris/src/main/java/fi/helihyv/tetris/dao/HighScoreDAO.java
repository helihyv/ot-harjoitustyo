package fi.helihyv.tetris.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Rajapinnan toteuttava luokka huolehtii parhaiden tulosten tallentamisesta ja
 * hakemisesta
 *
 * @author Heli Hyvättinen
 */
public interface HighScoreDAO {

    /**
     * Metodi lisää tietokantaan uuden tuloksen
     *
     * @param highScore tallennettava HighScore-olio
     * @return true, jos tallennus onnistui, false, jos epäonnistui
     */
    public boolean create(HighScore highScore);

    /**
     * Metodi listaa tuloksia tietokannasta Tulokset listataan aina suurimmasta
     * pienimpään ja niitä haetaan annettu määrä
     *
     * @param n haettavien tulosten määrä
     * @return haetut tulokset listana HighScore-olioita
     */
    public List<HighScore> list(int n);
}
