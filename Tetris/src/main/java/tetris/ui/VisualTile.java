
package tetris.ui;

/**
 *
 * @author Heli Hyvättinen
 */

import javafx.scene.canvas.GraphicsContext;
import tetris.domain.Tile;

public class VisualTile {
    
    private Tile tile;
    private GraphicsContext graphicsContext;

    public VisualTile(Tile tile, GraphicsContext graphicsContext) {
        this.tile = tile;
        this.graphicsContext = graphicsContext;
    }
    
    public void draw() {
        
        System.out.println(tile.getXCoordinate());
        System.out.println(tile.getYCoordinate());
        System.out.println(tile.getWidth());
    
        this.graphicsContext.strokeRect(
                tile.getXCoordinate(), 
                tile.getYCoordinate(), 
                tile.getWidth(), 
                tile.getWidth()
            );
        System.out.println("Draw tile complete");
   }
   
    
}


