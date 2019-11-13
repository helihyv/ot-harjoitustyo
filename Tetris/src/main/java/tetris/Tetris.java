
package tetris;

/**
 *
 * @author Heli Hyvättinen
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;

public class Tetris extends Application{
    
    @Override
    public void start(Stage window) {
       
        Canvas gameArea = new Canvas(600,2000);
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
