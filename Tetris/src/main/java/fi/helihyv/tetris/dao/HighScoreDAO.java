/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Heli Hyvättinen
 */
public interface HighScoreDAO {
    
    public boolean create(HighScore highScore); 
    
    public List<HighScore> list(int n);
}
