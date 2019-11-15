
package tetris.ui;

/**
 *
 * @author Heli Hyvättinen
 */

import javafx.scene.canvas.Canvas;
import java.util.List;
import tetris.domain.Tile;

public class GameArea {
    
    private Canvas canvas;

    public GameArea(double width, double height) {
    
        this.canvas = new Canvas(width, height); 
        
    }

    public Canvas getCanvas() {
        return canvas;
    }
    
    public void drawTiles(List<Tile> tiles) {

        canvas.getGraphicsContext2D().clearRect(
                0, 
                0, 
                canvas.getWidth(), 
                canvas.getHeight()
        );
                
        for (Tile tile : tiles) {
            drawTile(tile);
        }
    }
    
    private void drawTile(Tile tile) {
    
        canvas.getGraphicsContext2D().strokeRect(
                tile.getXCoordinate(), 
                tile.getYCoordinate(), 
                tile.getWidth(), 
                tile.getWidth()
            );
   }
   
    
}


