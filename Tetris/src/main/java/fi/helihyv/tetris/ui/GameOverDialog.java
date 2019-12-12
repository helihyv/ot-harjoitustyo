package fi.helihyv.tetris.ui;

import javafx.scene.control.Alert;

/**
 *
 * @author Heli Hyvättinen
 */
public class GameOverDialog extends Alert {

    public GameOverDialog(long score) {
        super(Alert.AlertType.INFORMATION);
        setTitle("Tetris – Game Over");
        setHeaderText("GAME OVER");
        setContentText("Your score was " + score + ". No highscore this time.");
        setGraphic(null);
        setResizable(true);
    }

}
