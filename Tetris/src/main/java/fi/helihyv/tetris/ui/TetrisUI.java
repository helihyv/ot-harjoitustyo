
package fi.helihyv.tetris.ui;

/**
 *
 * @author Heli Hyvättinen
 */

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

import fi.helihyv.tetris.domain.Game;
import fi.helihyv.tetris.domain.HighScoreService;
import fi.helihyv.tetris.domain.TetrisGame;
import fi.helihyv.tetris.domain.Tile;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TetrisUI extends Application {

    private Game game;
    private AnimationTimer timer;
    private boolean gameRunning;
    private HighScoreService highScoreService;
    private HighScoreView highScoreView;
    
    @Override
    public void start(Stage window) {
       
        game = new TetrisGame();
        GameArea gameArea = new GameArea(
                game.getGameAreaWidth() ,
                game.getGameAreaHeight() ,
                game.getGameOverHeight()
            );

        gameArea.drawTiles(game.getTiles());
        
        HBox root = new HBox();
        root.setBackground(Background.EMPTY);
        VBox sideBar = new VBox();
        BackgroundFill[] sideBarBackgroundfills = {new BackgroundFill(Color.CORNSILK, null, null) };
        sideBar.setBackground(new Background(sideBarBackgroundfills));
        Label startGameLabel = new Label("Press F1 to (re)start game.");
        Label scoreLabel = new Label("Score: 0");
        sideBar.getChildren().add(startGameLabel);
        sideBar.getChildren().add(scoreLabel);
        highScoreService = new HighScoreService(new HighScoreH2DAO(), 10);
        highScoreView = new HighScoreView(highScoreService);
        sideBar.getChildren().add(highScoreView.getLayout());
        root.getChildren().add(sideBar);
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
                scoreLabel.setText("Score: " + score);
                gameArea.drawTiles(game.getTiles());
                
                if (game.hasGameEnded()) {
                    gameRunning = false;
                    int rank = highScoreService.getRank(score);
                
                    if ( rank > 0) {
                        TextInputDialog  dialog = new TextInputDialog("Name here please");
                        dialog.setTitle("GAME OVER");
                        dialog.setHeaderText("You reached the rank " + rank);
                        dialog.setGraphic(null);
                        dialog.setContentText("Give your name for the highscore list:");
                        dialog.resultProperty().addListener(
                                (observable, oldValue, newValue) -> {
                                        highScoreService.addHighScore(new HighScore(newValue, score));
                                        highScoreView.updateHighScores();
                                }
                        );
                        dialog.show();

                    } else {
   //                    Alert alert = new Alert(AlertType.INFORMATION);
   //                     alert.show();
                        System.out.println("No highscore this time");
                    }
                    
                    
                } 
                

            }
        };
        
        gameRunning = true;
        timer.start();
        
        final EventHandler<KeyEvent> keyPressHandler =
                new EventHandler<KeyEvent>()  {
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
        
        Scene view = new Scene(root);
        view.addEventHandler(KeyEvent.KEY_PRESSED, keyPressHandler);
        window.setScene(view);
        window.setTitle("Tetris");
        window.show();
    }
    
    public static void main(String[] args) {
        launch(TetrisUI.class);
    }

    @Override
    public void stop() throws Exception {
        game.stopGame();
        super.stop();
    }
    
    
    
}
