/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.domain;

import fi.helihyv.tetris.dao.HighScore;
import fi.helihyv.tetris.dao.HighScoreDAO;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Heli Hyvättinen
 */
public class HighScoreService {
    
    List<HighScore> highScores;
    HighScoreDAO dao;
    int n;


    public HighScoreService(HighScoreDAO dao, int n) {
        this.dao = dao;
        this.n = n;
        highScores = dao.list(n);
    }
        
    public List<HighScore> getHighScores() {
        return highScores;
        
    }
    
    public boolean addHighScore(HighScore highScore) {
        
       if (highScore.getScore() > highScores.get(n-1).getScore()) { 
        
            if (!dao.create(highScore)) {
               return false;
            }
            highScores.add(highScore);
            Collections.sort(highScores);
            highScores.remove(n-1);

   
       }
       
                   
       return true;
       
    }
    
    public int getRank(int score) {
       
        for (int i = 0; i < n; i++) {
            
            if (score > highScores.get(i).getScore()) {
                return i;
            }
        }
        
        return -1;
    }
}
