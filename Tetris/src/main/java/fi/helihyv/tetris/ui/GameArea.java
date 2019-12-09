package fi.helihyv.tetris.ui;

/**
 * Luokka huolehtii its e pelin näyttämisestä Piirtää Canvas-olioon palikan ja
 * tiilipinon tiilet ja pelin päättäväää pinon korkeutta kuvaavan viivan
 *
 * @author Heli Hyvättinen
 */
import javafx.scene.canvas.Canvas;
import java.util.List;
import fi.helihyv.tetris.gamelogic.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameArea {

    private Canvas canvas;
    private double gameOverHeight;

    public GameArea(double width, double height, double gameOverHeight) {

        this.canvas = new Canvas(width, height);
        this.gameOverHeight = gameOverHeight;

    }

    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Piirtää pelin tiilet ja pelin päättymiskohtaa kuvaavan viivan Aluksi
     * puhdistetaan Canvas aiemmin piirretystä
     *
     * @param tiles
     */
    public void drawTiles(List<Tile> tiles) {

        canvas.getGraphicsContext2D().clearRect(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight()
        );

        canvas.getGraphicsContext2D().strokeLine(
                0,
                gameOverHeight,
                canvas.getWidth(),
                gameOverHeight
        );

        for (Tile tile : tiles) {
            drawTile(tile);
        }
    }

    private void drawTile(Tile tile) {

        GraphicsContext context = canvas.getGraphicsContext2D();

        context.setFill(Color.LIGHTBLUE);
        context.setStroke(Color.DARKBLUE);

        context.fillRect(
                tile.getXCoordinate(),
                tile.getYCoordinate(),
                tile.getWidth(),
                tile.getWidth()
        );

        context.strokeRect(
                tile.getXCoordinate(),
                tile.getYCoordinate(),
                tile.getWidth(),
                tile.getWidth()
        );
    }

}
