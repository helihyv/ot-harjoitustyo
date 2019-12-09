/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.dao;

/**
 *
 * @author Heli Hyvättinen
 */

public class HighScore implements Comparable<HighScore> {
 
    private String name;
    private int score;

    public HighScore(String name, int score) {
        this.score = score;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(HighScore t) {
       return this.score - t.getScore();
    }
    
    
    
    
}
