package fi.helihyv.tetris.highscores;

import fi.helihyv.tetris.dao.HighScore;
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
public class HighScoreServiceTest {

    HighScoreService highScoreService;

    @Before
    public void setUp() {

        highScoreService = new HighScoreService(new FakeHighScoreDAO(), 10);

    }

    @Test
    public void highScoreServiceGetRankReturns1WhenThereAreNoHighScores() {

        assertEquals(1, highScoreService.getRank(1000));
    }

    @Test
    public void highScoreServiceGetRankReturns1WhenThereAreNoHigherHighScores() {

        highScoreService.addHighScore(new HighScore("Otso Kontio", 100));
        assertEquals(1, highScoreService.getRank(1000));
    }

    @Test
    public void highScoreServiceGetRankReturns2IfThereIsOneHigherHighScore() {
        highScoreService.addHighScore(new HighScore("Otso Kontio", 1000));

        assertEquals(2, highScoreService.getRank(100));
    }

    @Test
    public void highScoreServiceGetRankReturnsMinusOneIfScoreDoesNotQualifyAsHighScore() {
        for (int i = 0; i < 12; i++) {
            highScoreService.addHighScore(new HighScore("Otso Kontio", 3000 + i));
        }

        assertEquals(-1, highScoreService.getRank(2999));

    }

    @Test
    public void highScoreServiceGetHighScoresReturnsCorrectNumberOfHighScores() {
        highScoreService.addHighScore(new HighScore("Otso Kontio", 5000));
        highScoreService.addHighScore(new HighScore("Otso Kontio", 2000));

        assertEquals(2, highScoreService.getHighScores().size());

    }

    @Test
    public void highScoreServiceGetHighScoresDoesNotReturnMoreThanMaxNumberOfHighScores() {

        for (int i = 0; i < 12; i++) {
            highScoreService.addHighScore(new HighScore("Otso Kontio", 3000 + i));
        }

        assertEquals(10, highScoreService.getHighScores().size());
    }

    @Test
    public void highScoreServiceGetHighScoresReturnsHighScoresInCorrectOrder() {

        highScoreService.addHighScore(new HighScore("Otso Kontio", 2000));
        highScoreService.addHighScore(new HighScore("Meri Kotka", 10000));
        highScoreService.addHighScore(new HighScore("Alli Albatrosi", 3000));

        assertEquals("Meri Kotka", highScoreService.getHighScores().get(0).getName());
    }

    @Test
    public void highScoreServiceGetMaxNumberOfHighScoresReturnsCorrectNumber() {
        assertEquals(10, highScoreService.getMaxNumberOfHighScores());
    }

}
