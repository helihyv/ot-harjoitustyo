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
               
       if (highScores.size() < n ||
            highScore.getScore() > highScores.get(highScores.size()-1).getScore()
       ) { 
        
            if (!dao.create(highScore)) {
               return false;
            }
            highScores.add(highScore);
            Collections.sort(highScores);
            if (highScores.size() > n) {
                highScores.remove(n);
            }
   
       }
       
                   
       return true;
       
    }
    
    public int getRank(long score) {
              
        for (int i = 0; i < highScores.size(); i++) {
            
            if (score > highScores.get(i).getScore()) {

                return i+1;
            }
        }
        
        if (highScores.size() < n) {
            return highScores.size() + 1;
        }
        
        return -1;
    }
    
    public int getMaxNumberOfHighScores() {
        return n;
    }
}
