package fi.helihyv.tetris.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Heli Hyvättinen
 */
public class TetrisGame implements Game {

    private int gameAreaWidth;
    private int gameAreaHeight;
    private double tileWidth;
    private Block currentBlock;
    private TileStack tileStack;
    private Timer timer;
    private double loseLevel;
    private long score;
    private boolean fastFall;
    private double normalFallSpeed;
    private double fastFallSpeed;

    public TetrisGame() {
        this.gameAreaWidth = 600;
        this.gameAreaHeight = 900;
        this.tileWidth = 20;
        this.loseLevel = 200;
        normalFallSpeed = 1;
        fastFallSpeed = 5;

        startGame();
    }

    @Override
    public int getGameAreaHeight() {
        return gameAreaHeight;
    }

    @Override
    public int getGameAreaWidth() {
        return gameAreaWidth;
    }

    @Override
    public List<Tile> getTiles() {

        ArrayList<Tile> tiles = new ArrayList<>();
        Tile[] blockTiles = currentBlock.getTiles();
        for (int i = 0; i < 4; i++) {
            tiles.add(blockTiles[i]);
        }

        tiles.addAll(tileStack.getTiles());

        return tiles;
    }

    @Override
    public void startGame() {

        this.score = 0;

        tileStack = new TileStack(gameAreaWidth, gameAreaHeight);
        generateNewBlock();

        this.timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                moveBlockDown();
            }
        };
        timer.schedule(task, 10L, 10L);
    }

    @Override
    public void moveBlockLeft() {

        for (Tile tile : tileStack.getTiles()) {
            System.out.println("y: " + tile.getYCoordinate());
            System.out.println("x: " + tile.getXCoordinate());
            System.out.println("");
        }

        if (currentBlock.leftEdge() >= tileWidth
                && tileStack.areAreasFree(currentBlock.freeAreasNeededToMoveLeft())) {
            currentBlock.moveLeft();

        }
    }

    @Override
    public void moveBlockRight() {
        if (currentBlock.rightEdge() <= gameAreaWidth - tileWidth
                && tileStack.areAreasFree(currentBlock.freeAreasNeededToMoveRight())) {
            currentBlock.moveRight();
        }
    }

    public void moveBlockDown() {

        double fallSpeed = fastFall ? fastFallSpeed : normalFallSpeed;

        for (int i = 0; i < fallSpeed; i++) {

            moveBlockDownByOne();
        }
    }

    private void moveBlockDownByOne() {

        currentBlock.moveDown();
        for (double i = currentBlock.leftEdge(); i <= currentBlock.rightEdge(); i += tileWidth) {
            double blockBottom = currentBlock.bottomEdge(i);
            if (Double.compare(blockBottom, gameAreaHeight) >= 0
                    || Double.compare(blockBottom, tileStack.topEdge(blockBottom, i)) >= 0) {
                tileStack.addBlock(currentBlock);

                int tilesRemoved = tileStack.removeFullRows();

                score += tilesRemoved * 100;
                if (isGameOver()) {
                    stopGame();
                } else {
                    generateNewBlock();
                }
            }

        }

    }

    private void generateNewBlock() {
        fastFall = false;

        Random r = new Random();

        int blockType = r.nextInt(7) + 1;

        double center = gameAreaWidth / 2;

        switch (blockType) {

            case 1:
                this.currentBlock = new SquareBlock(center - tileWidth, tileWidth);
                break;

            case 2:
                this.currentBlock = new IBlock(center - tileWidth * 2, tileWidth);
                break;

            case 3:
                this.currentBlock = new LBlock(center - tileWidth, tileWidth);
                break;

            case 4:
                this.currentBlock = new MirrorLBlock(center - tileWidth, tileWidth);
                break;

            case 5:
                this.currentBlock = new SBlock(center - 2 * tileWidth, tileWidth);

            case 6:
                this.currentBlock = new MirrorSBlock(center - 2 * tileWidth, tileWidth);

            case 7:
                this.currentBlock = new TBlock(center - 2 * tileWidth, tileWidth);
        }
    }

    @Override
    public void stopGame() {
        timer.cancel();

    }

    public boolean isGameOver() {

        for (int i = 0; i < gameAreaWidth; i += tileWidth) {

            if (tileStack.topEdge(0, i) <= loseLevel) {
                return true;
            }
        }

        return false;
    }

    @Override
    public double getGameOverHeight() {
        return loseLevel;
    }

    @Override
    public long getScore() {
        return score;
    }

    @Override
    public void dropBlock() {
        fastFall = true;
    }

    @Override
    public void rotateBlock() {
        if (tileStack.areAreasFree(currentBlock.freeAreasNeededToRotate())) {
            currentBlock.rotate();
        }
    }

}
