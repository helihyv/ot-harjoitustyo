/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.ui;

import fi.helihyv.tetris.dao.HighScore;
import fi.helihyv.tetris.highscores.HighScoreService;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Luokka huolehtii parhaiden tulosten näyttämisestä
 *
 * @author Heli Hyvättinen
 */
public class HighScoreView {

    private VBox layout;
    private ArrayList<Label> scoreLabels;
    private HighScoreService highScoreService;
    private Label errorLabel;

    public HighScoreView(HighScoreService highScoreService) {

        this.highScoreService = highScoreService;

        errorLabel = new Label();

        scoreLabels = new ArrayList<>();

        layout = new VBox();

        layout.getChildren().add(new Label("Highscores"));

        layout.getChildren().add(errorLabel);

        for (int i = 0; i < highScoreService.getMaxNumberOfHighScores(); i++) {
            Label label = new Label();
            layout.getChildren().add(label);
            scoreLabels.add(label);
        }

        updateHighScores();

        if (highScoreService.failedToReadHighScores()) {
            errorLabel.setText(
                    "Failed to read the highscores from the database.\n"
                    + "Reason:\n"
                    + highScoreService.getListErrorMessage());
        }
    }

    private void setHighScores(List<HighScore> highScores) {

        for (int i = 0; i < scoreLabels.size(); i++) {
            if (i < highScores.size()) {
                HighScore highScore = highScores.get(i);
                scoreLabels.get(i).setText((i + 1) + ". " + highScore.getName() + " "
                        + highScore.getScore());
            }

        }
    }

    /**
     * Metodi palauttaa layoutin, joka sisältää otsikon, mahdollisen
     * virheilmoituksen ja näytettävät tulokset
     *
     * @return layout
     */
    public VBox getLayout() {
        return layout;
    }

    void updateHighScores() {
        setHighScores(highScoreService.getHighScores());
    }

}
