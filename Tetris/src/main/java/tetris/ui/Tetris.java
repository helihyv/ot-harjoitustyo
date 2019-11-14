
package tetris.ui;

/**
 *
 * @author Heli Hyvättinen
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;

import tetris.domain.Game;
import tetris.domain.TetrisGame;
import tetris.domain.Tile;

public class Tetris extends Application{
    
    @Override
    public void start(Stage window) {
       
        Game game = new TetrisGame();
        Canvas gameArea = new Canvas(game.getGameAreaWidth(),game.getGameAreaHeight());
        
        
        for (Tile tile : game.getTiles()) {
            VisualTile visualTile = new VisualTile(tile, gameArea.getGraphicsContext2D());
            visualTile.draw();
        }
        
        Group root = new Group();
        root.getChildren().add(gameArea);
        
        Scene view = new Scene(root);
        window.setScene(view);
        window.setTitle("Tetris");
        window.show();
    }
    
    public static void main(String[] args) {
        launch(Tetris.class);
    }
    
}
