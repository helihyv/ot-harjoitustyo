
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
                
                if (present-previous < 10000) {
                    return;
                }
                
                gameArea.drawTiles(game.getTiles());
                
                this.previous = present;
            }
        };
        
        timer.start();
        
        Scene view = new Scene(root);
        window.setScene(view);
        window.setTitle("Tetris");
        window.show();
    }
    
    public static void main(String[] args) {
        launch(Tetris.class);
    }
    
}
