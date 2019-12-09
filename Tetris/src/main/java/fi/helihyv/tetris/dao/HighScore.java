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
    private long score;

    public HighScore(String name, long score) {
        this.score = score;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getScore() {
        return score;
    }

    @Override
    public int compareTo(HighScore t) {
       long difference = t.getScore() - this.score;
       if (difference > 0) {
           return 1;
       }
       if (difference < 0) {
           return -1;
       }
       return 0;
    
    
    
    
}
