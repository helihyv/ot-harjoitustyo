package fi.helihyv.tetris.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heli Hyvättinen
 */
public class HighScoreH2DAOUnitTest {

    private HighScoreH2DAO dao;

    @Test
    public void highScoreH2DAOListReturnsEmptyListIfNoHighScoresAdded() {
        dao = new HighScoreH2DAO("mem:testdb1;DB_CLOSE_DELAY=-1");
        assertTrue(dao.list(10).isEmpty());
    }

    @Test
    public void highScoreH2DAOListReturnsCorrectNumberOfHighScores() {
        dao = new HighScoreH2DAO("mem:testdb2;DB_CLOSE_DELAY=-1");
        dao.create(new HighScore("Otso Kontio", 1000));
        dao.create(new HighScore("Meri Kotka", 2000));

        assertEquals(2, dao.list(10).size());
    }

    @Test
    public void highScoreH2DAOListReturnsHighScoresUnChanged() {
        dao = new HighScoreH2DAO("mem:testdb3;DB_CLOSE_DELAY=-1");
        dao.create(new HighScore("Otso Kontio", 1000));

        assertEquals("Otso Kontio", dao.list(10).get(0).getName());
        assertEquals(1000, dao.list(10).get(0).getScore());
    }

    @Test
    public void highScoreH2DAOListReturnsHighestHighScoresInCorrectOrder() {
        dao = new HighScoreH2DAO("mem:testdb4;DB_CLOSE_DELAY=-1");
        dao.create(new HighScore("Otso Kontio", 1000));
        dao.create(new HighScore("Meri Kotka", 2000));
        dao.create(new HighScore("Alli Albatrossi", 3000));

        List<HighScore> highScores = dao.list(2);

        assertEquals(2, highScores.size());
        assertEquals("Alli Albatrossi", highScores.get(0).getName());
        assertEquals("Meri Kotka", highScores.get(1).getName());
    }

    @Test
    public void highScoreH2DAOPersistsHighScoresToDatabase() {
        dao = new HighScoreH2DAO("mem:testdb5;DB_CLOSE_DELAY=-1");
        dao.create(new HighScore("Otso Kontio", 1000));

        dao = new HighScoreH2DAO("mem:testdb5;DB_CLOSE_DELAY=-1");

        assertEquals("Otso Kontio", dao.list(10).get(0).getName());

    }

}
