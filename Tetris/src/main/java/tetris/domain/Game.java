/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

/**
 *
 * @author hzkoskin
 */

import java.util.List;

public interface Game {
    
    public void startGame();
    
    public int getGameAreaWidth();
    
    public int getGameAreaHeight();
    
    public List<TetrisTile> getTiles();
    
}
