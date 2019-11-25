/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.domain;

/**
 *
 * @author Heli Hyvättinen
 */

import java.util.List;

public interface Game {
    
    public void startGame();
    
    public void stopGame();
    
    public int getGameAreaWidth();
    
    public int getGameAreaHeight();
    
    public List<Tile> getTiles();
    
    public void moveBlockLeft();
    
    public void moveBlockRight();
    
}
