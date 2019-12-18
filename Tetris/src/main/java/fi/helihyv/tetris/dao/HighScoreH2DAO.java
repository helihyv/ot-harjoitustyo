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
            // Jos tämä epäonnistuu, myös lukeminen ja lisääminen epäonnistuvat.
            // Tieto virheestä välittyy ylöspäin funktioista list() ja create()
        }

    }

    /**
     * Lisää tietokantaan uuden tuloksen.
     *
     * @param highScore lisättävä tulos
     * @throws java.sql.SQLException
     */
    @Override
    public void create(HighScore highScore) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:" + databaseFilename, "sa", "");
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO HighScore VALUES(?,?)");

        stmt.setString(1, highScore.getName());
        stmt.setLong(2, highScore.getScore());

        stmt.executeUpdate();

    }

    /**
     * Listaa tietokantaan tallennetuista tuloksista n parasta
     *
     * @param n – kuuinka monta tulosta haetaan
     * @return n pistemäärältään suurinta tulosta listana HighScore-olioita
     * @throws java.sql.SQLException
     */
    @Override
    public List<HighScore> list(int n) throws SQLException {

        ArrayList<HighScore> highScores = new ArrayList<>();

        Connection connection = DriverManager.getConnection("jdbc:h2:" + databaseFilename, "sa", "");
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM HighScore ORDER BY score DESC LIMIT ?");
        stmt.setInt(1, n);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            HighScore highScore = new HighScore(rs.getString("name"), rs.getInt("score"));
            highScores.add(highScore);
        }

        return highScores;
    }

}
