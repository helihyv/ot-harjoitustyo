
package fi.helihyv.tetris.ui;

/**
 *
 * @author Heli Hyvättinen
 */

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
import fi.helihyv.tetris.domain.TetrisGame;
import fi.helihyv.tetris.domain.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TetrisUI extends Application {

    private Game game;
    private AnimationTimer timer;
    
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
        root.getChildren().add(sideBar);
        root.getChildren().add(gameArea.getCanvas());
        
        timer = new AnimationTimer() {
            long previous = 0;
            
            @Override
            public void handle(long present) {
                
                if (present - previous < 10000000) {
                    return;
                }
                
                scoreLabel.setText("Score: " + game.getScore());
                gameArea.drawTiles(game.getTiles());
                
                this.previous = present;
            }
        };
        
        timer.start();
        
        final EventHandler<KeyEvent> keyPressHandler =
                new EventHandler<KeyEvent>()  {
            public void handle(final KeyEvent keyEvent) {
                            
                KeyCode keyCode = keyEvent.getCode();                      //  game.moveBlockLeft();
                   
                if (keyCode.equals(KeyCode.LEFT)) {
                    game.moveBlockLeft();
                }
                    
                if (keyCode.equals(KeyCode.RIGHT)) {
                    game.moveBlockRight();
                }
                
                if (keyCode.equals(KeyCode.F1)) {
                    game.stopGame();
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
