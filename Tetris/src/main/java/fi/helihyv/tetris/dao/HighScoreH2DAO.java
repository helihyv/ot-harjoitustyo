package fi.helihyv.tetris.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;
import org.h2.message.DbException;

/**
 * Luokka huolehtii parhaiden tulosten tallentamisesta H2-tietokantaan ja niiden
 * hakemisesta sieltä
 *
 * @author Heli Hyvättinen
 */
public class HighScoreH2DAO implements HighScoreDAO {

    private String databaseFilename;

    /**
     * Luokan konstruktori luo tietokantatiedoston ja siihen taulun tuloksille,
     * ellei niitä ole jo luotu aiemmin, Saa paramerkikseen tietokantatiedoston
     * nimen polkuineen ilman mv.db -päätettä. Jos parametri on null tai tyhjä
     * merkkijono, käyetetään tietokannan oletussijaintia ./tetris
     */
    public HighScoreH2DAO(String databaseFilename) {

        if (databaseFilename == null || databaseFilename.isEmpty()) {
            this.databaseFilename = "./tetris";
        } else {
            this.databaseFilename = databaseFilename;
        }

        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:" + this.databaseFilename, "sa", "");
                PreparedStatement stmt = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS HighScore(name VARCHAR(255), score BIGINT)")) {

            stmt.execute();

        } catch (SQLException exception) {
            System.out.println("Failed to connect or initialize the database ");
            System.out.println(exception.getMessage());
        }

    }

    /**
     * Lisää tietokantaan uuden tuloksen.
     *
     * @param highScore lisättävä tulos
     * @return true jos onnistui, false jos epäonnistui
     */
    @Override
    public boolean create(HighScore highScore) {

        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:" + databaseFilename, "sa", "");
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO HighScore VALUES(?,?)");) {
            stmt.setString(1, highScore.getName());
            stmt.setLong(2, highScore.getScore());

            stmt.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Failied to add a highscore");
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Listaa tietokantaan tallennetuista tuloksista n parasta
     *
     * @param n – kuuinka monta tulosta haetaan
     * @return n pistemäärältään suurinta tulosta listana HighScore-olioita
     */
    @Override
    public List<HighScore> list(int n) {

        ArrayList<HighScore> highScores = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:" + databaseFilename, "sa", "");
                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT * FROM HighScore ORDER BY score DESC LIMIT ?");) {
            stmt.setInt(1, n);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HighScore highScore = new HighScore(rs.getString("name"), rs.getInt("score"));
                highScores.add(highScore);
            }

        } catch (SQLException exception) {
            System.out.println("Failed to read high scores");
            System.out.println(exception.getMessage());
            return null;
        }

        return highScores;
    }

}
