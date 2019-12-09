/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Heli Hyvättinen
 */

public class HighScoreH2DAO  implements HighScoreDAO {

    @Override
    public boolean create(HighScore highScore) {
        
        try {
        
        Connection connection = DriverManager.getConnection("jdbc_h2/.tetris", "sa", "");
        
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO HighScore VALUES(?,?)");
        stmt.setString(1,highScore.getName());
        stmt.setInt(2, highScore.getScore());
        
        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
        }
        
        catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    public List<HighScore> list(int n) {
        
        ArrayList<HighScore> highScores = new ArrayList<>();
        
        try {
        
            Connection connection = DriverManager.getConnection("jdbc_h2/.tetris", "sa", "");

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM HighScore ORDER BY score DESC LIMIT ?");
            stmt.setInt(1, n);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HighScore highScore = new HighScore(rs.getString("name"), rs.getInt("score"));
                highScores.add(highScore);
            }

            stmt.close();
            connection.close();        
        }
        
        catch (Exception e ) {
            System.out.println(e.getMessage());
            return null;
        }
       
        return highScores;
    }
    
}
