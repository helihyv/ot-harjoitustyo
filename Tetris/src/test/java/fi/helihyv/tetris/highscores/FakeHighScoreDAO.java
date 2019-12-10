package fi.helihyv.tetris.highscores;

import fi.helihyv.tetris.dao.HighScore;
import fi.helihyv.tetris.dao.HighScoreDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Heli Hyvättinen
 */
public class FakeHighScoreDAO implements HighScoreDAO {

    ArrayList<HighScore> highScores;

    public FakeHighScoreDAO() {

        highScores = new ArrayList<>();
    }

    @Override
    public boolean create(HighScore highScore) {
        highScores.add(highScore);
        Collections.sort(highScores);
        return true;
    }

    @Override
    public List<HighScore> list(int n) {
        if (highScores.size() <= n) {
            return (List) highScores.clone();
        }

        return highScores.subList(0, n);
    }

}
