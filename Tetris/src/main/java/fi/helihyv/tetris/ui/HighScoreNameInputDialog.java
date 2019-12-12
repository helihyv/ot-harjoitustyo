/* int rank
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.ui;

import fi.helihyv.tetris.dao.HighScore;
import fi.helihyv.tetris.highscores.HighScoreService;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Heli Hyvättinen
 */
public class HighScoreNameInputDialog extends TextInputDialog {

    TextInputDialog dialog;

    public HighScoreNameInputDialog(HighScoreService highScoreService, HighScoreView highScoreView, long score, int rank) {

        super("Anonymous");
        setTitle("Tetris – Highscore");
        setHeaderText("GAME OVER \n You reached the rank " + rank + " with your score " + score);
        setGraphic(null);
        setContentText("Give your name for the highscore list:");

        //Tällä kierretään bugi, joka jättää Java 11:sta ja
        //KDE:ta käytettäessä dialogin aivan liian pieneksi
        setResizable(true);

        resultProperty().addListener((observable, oldValue, newValue) -> {
            highScoreService.addHighScore(new HighScore(newValue, score));
            highScoreView.updateHighScores();
        });

    }

}
