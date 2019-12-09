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
 * Luokka huoilehtii parhaiden tulosten tallentamisesta H2-tietokantaan ja
 * niiden hakemisesta sieltä Tietokanta sijaitsee tiedostossa ./tetris.mv.db
 * Lisäksi luodaan tiedosto ./tetris.trace.db
 *
 * @author Heli Hyvättinen
 */
public class HighScoreH2DAO implements HighScoreDAO {

    /**
     * Luokan konstruktori luo tietokantatiedoston ja siihen taulun tuloksille,
     * ellei niitä ole jo luotu aiemmin
     */
    public HighScoreH2DAO() {

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:./tetris", "sa", "");

            PreparedStatement stmt = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS HighScore(name VARCHAR(255), score BIGINT)");

            stmt.execute();
            stmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public boolean create(HighScore highScore) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:./tetris", "sa", "");

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO HighScore VALUES(?,?)");
            stmt.setString(1, highScore.getName());
            stmt.setLong(2, highScore.getScore());

            stmt.executeUpdate();
            stmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<HighScore> list(int n) {

        ArrayList<HighScore> highScores = new ArrayList<>();

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:./tetris", "sa", "");

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return highScores;
    }

}
