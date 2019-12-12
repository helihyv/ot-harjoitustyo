package fi.helihyv.tetris.ui;

import fi.helihyv.tetris.dao.HighScore;
import fi.helihyv.tetris.dao.HighScoreH2DAO;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

import fi.helihyv.tetris.gamelogic.Game;
import fi.helihyv.tetris.highscores.HighScoreService;
import fi.helihyv.tetris.gamelogic.TetrisGame;
import fi.helihyv.tetris.gamelogic.Tile;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Tetris-pelin käyttöliittymän pääluokka, jonka vastuulla on sovelluslogiikan
 * ylätason olioiden luominen ja käynnistäminen, muiden käyttöliittymän osien
 * luominen, näppäinpainalluksiin reagoiminen ja animaatioajastimen luominen ja
 * käynnistäminen.
 *
 * @author Heli Hyvättinen
 */
public class TetrisUI extends Application {

    private Game game;
    private AnimationTimer timer;
    private boolean gameRunning;
    private HighScoreService highScoreService;
    private HighScoreView highScoreView;

    /**
     * Metodi suoritetaan sovellusta käynnistettäessä. Metodi luo
     * sovelluslogiikan Game ja HighscoreService-oliot, luo muut käyttöliittymän
     * osat, luo ja käynnistää animaatioajastimen, asettaa
     * näppäimistötapahtumien käsittelijän, sekä käynnistää pelin
     */
    @Override
    public void start(Stage window) {

        game = new TetrisGame();
        GameArea gameArea = new GameArea(
                game.getGameAreaWidth(),
                game.getGameAreaHeight(),
                game.getGameOverHeight()
        );

        gameArea.drawTiles(game.getTiles());

        HBox root = new HBox();
        root.setBackground(Background.EMPTY);

        highScoreService = new HighScoreService(new HighScoreH2DAO(""), 10);
        highScoreView = new HighScoreView(highScoreService);
        SideBar sideBar = new SideBar(highScoreView);
        root.getChildren().add(sideBar.getLayout());
        root.getChildren().add(gameArea.getCanvas());

        timer = new AnimationTimer() {
            long previous = 0;

            @Override
            public void handle(long present) {

                if (present - previous < 10000000) {
                    return;
                }

                this.previous = present;

                if (!gameRunning) {
                    return;
                }

                long score = game.getScore();
                sideBar.setScoreToShow(score);
                gameArea.drawTiles(game.getTiles());

                if (game.hasGameEnded()) {
                    gameRunning = false;
                    int rank = highScoreService.getRank(score);

                    if (rank > 0) {
                        new HighScoreNameInputDialog(highScoreService, highScoreView, score, rank).show();

                    } else {
                        new GameOverDialog((score)).show();
                    }
                }
            }
        };

        Scene view = new Scene(root);
        view.addEventHandler(KeyEvent.KEY_PRESSED, createKeyPressHandler());
        window.setScene(view);
        window.setTitle("Tetris");
        window.setResizable(false);

        gameRunning = true;
        timer.start();

        window.show();
    }

    /**
     * Main-metodi ainoastaan käynnistää käyttöliittymän. Argumenttejä ei
     * huomioida.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(TetrisUI.class);
    }

    /**
     * Metodi suoritetaan sovellusta suljettaessa. Metodi lopettaa pelin, jotta
     * sovelluslogiikan ajastin ei jäisi päälle ja estäisi säiettään
     * sulkeutumasta.
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        game.stopGame();
        super.stop();
    }

    /**
     * Metodi luo ja palauttaa näppäinpainallusten tapahtumakäsittelijän
     *
     */
    private EventHandler createKeyPressHandler() {
        final EventHandler<KeyEvent> keyPressHandler
                = new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {

                KeyCode keyCode = keyEvent.getCode();

                if (keyCode.equals(KeyCode.LEFT)) {
                    game.moveBlockLeft();
                }

                if (keyCode.equals(KeyCode.RIGHT)) {
                    game.moveBlockRight();
                }

                if (keyCode.equals(KeyCode.F1)) {
                    game.stopGame();
                    gameRunning = true;
                    game.startGame();
                }

                if (keyCode.equals(KeyCode.SPACE)) {
                    game.dropBlock();
                }

                if (keyCode.equals(KeyCode.UP)) {
                    game.rotateBlock();
                }

            }
        };

        return keyPressHandler;
    }

}
