
package tetris.ui;

/**
 *
 * @author Heli Hyvättinen
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

import tetris.domain.Game;
import tetris.domain.TetrisGame;
import tetris.domain.Tile;

public class Tetris extends Application{

    private Game game;
    private AnimationTimer timer;
    
    @Override
    public void start(Stage window) {
       
        game = new TetrisGame();
        GameArea gameArea = new GameArea(game.getGameAreaWidth(),game.getGameAreaHeight());
        
        gameArea.drawTiles(game.getTiles());
        
        Group root = new Group();
        root.getChildren().add(gameArea.getCanvas());
        
        timer = new AnimationTimer() {
            long previous = 0;
            
            @Override
            public void handle(long present) {
                
                if (present-previous < 10000000) {
                    return;
                }
                
                gameArea.drawTiles(game.getTiles());
                
                this.previous = present;
            }
        };
        
        timer.start();
        
        final EventHandler<KeyEvent> keyPressHandler =
                new EventHandler<KeyEvent>()  {
                    public void handle (final KeyEvent keyEvent ) {
                            
                        KeyCode keyCode = keyEvent.getCode();                      //  game.moveBlockLeft();
                   
                    if (keyCode.equals(KeyCode.LEFT)) {
                        game.moveBlockLeft();
                     }
                    
                    if (keyCode.equals(KeyCode.RIGHT)) {
                        game.moveBlockRight();
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
        launch(Tetris.class);
    }

    @Override
    public void stop() throws Exception {
        game.stopGame();
        super.stop();
    }
    
    
    
}
