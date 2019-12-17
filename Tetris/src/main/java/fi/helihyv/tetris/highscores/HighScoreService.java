/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.highscores;

import fi.helihyv.tetris.dao.HighScore;
import fi.helihyv.tetris.dao.HighScoreDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Parhaiden tulosten osalta sovelluslogiikasta vastaava luokka
 *
 * @author Heli Hyvättinen
 */
public class HighScoreService {

    private List<HighScore> highScores;
    private HighScoreDAO dao;
    private int n;
    private boolean failedToReadHighScoresFromDatabase;

    /**
     *
     * @param dao HighScoeDAO-olio, joka vastaa tulosten pysyväistallennuksesta
     * @param n listalla pidettävien tulosten maksimimäärä
     */
    public HighScoreService(HighScoreDAO dao, int n) {
        this.dao = dao;
        this.n = n;

        highScores = dao.list(n);

        if (highScores == null) {
            highScores = new ArrayList<>();
            failedToReadHighScoresFromDatabase = true;
        }
    }

    public List<HighScore> getHighScores() {
        return highScores;

    }

    /**
     * Metodi lisää tuloksen parhaiden tulostenlistaan
     *
     * @param highScore lisättävä tulos HighScore-oliona
     * @return onnistuiko tuloksen lisäys
     */
    public boolean addHighScore(HighScore highScore) {

        if (highScores.size() < n
                || highScore.getScore() > highScores.get(highScores.size() - 1).getScore()) {

            if (!dao.create(highScore)) {
                return false;
            }
            highScores.add(highScore);
            Collections.sort(highScores);
            if (highScores.size() > n) {
                highScores.remove(n);
            }

        }

        return true;

    }

    /**
     * Metodi palauttaa minkä sijan tuloslistalla saavuttaa annetulla
     * pistemäärällä Jos pistemäärä ei pääse listalle, palautetaan -1
     *
     * @param score pelaajan saama pistemäärä
     * @return sijoitus, tai -1 jos sijoitusta ei ole
     */
    public int getRank(long score) {

        for (int i = 0; i < highScores.size(); i++) {

            if (score > highScores.get(i).getScore()) {

                return i + 1;
            }
        }

        if (highScores.size() < n) {
            return highScores.size() + 1;
        }

        return -1;
    }

    public int getMaxNumberOfHighScores() {
        return n;
    }

    public boolean failedToReadHighScores() {
        return failedToReadHighScoresFromDatabase;
    }
}
