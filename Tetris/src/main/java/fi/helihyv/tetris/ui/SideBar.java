package fi.helihyv.tetris.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Pystypalkki, joka näyttää ohjetekstin, pistetilanteen ja parhaat tulokset Saa
 * parametrikseen parhaat tulokset näyttävän HighScoreView-olion
 *
 * @author Heli Hyvättinen
 */
public class SideBar {

    private VBox layout;
    private Label scoreLabel;

    /**
     * Luo pystypalkin, joka näyttää ohjetekstin, pisteet ja parhaat tulokset
     *
     * @param highScoreView Parhaat tulokset näyttävä HighScoreView-olio
     */
    public SideBar(HighScoreView highScoreView) {

        this.layout = new VBox();
        BackgroundFill[] sideBarBackgroundfills = {new BackgroundFill(Color.CORNSILK, null, null)};
        layout.setBackground(new Background(sideBarBackgroundfills));
        Label startGameLabel = new Label("Press F1 to (re)start game.");
        scoreLabel = new Label("Score: 0");
        layout.getChildren().add(startGameLabel);
        layout.getChildren().add(scoreLabel);
        layout.getChildren().add(highScoreView.getLayout());
    }

    /**
     * Metodi palauttaa palkin VBox-layoutin, joka voidaan sijoittaa toisen
     * layoutin laoseksi
     *
     */
    public VBox getLayout() {
        return layout;
    }

    /**
     * Metodi asettaa näytettävän pistemäärän
     *
     * @param score pelaajan pisteet
     */
    public void setScoreToShow(long score) {
        scoreLabel.setText("Score: " + score);
    }

}
