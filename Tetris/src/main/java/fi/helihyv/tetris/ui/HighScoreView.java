/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helihyv.tetris.ui;

import fi.helihyv.tetris.dao.HighScore;
import fi.helihyv.tetris.domain.HighScoreService;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Heli Hyvättinen
 */
public class HighScoreView {
    
    private VBox layout;
    private ArrayList<Label> scoreLabels;
    private HighScoreService highScoreService;
    private Label errorLabel;

    public HighScoreView(HighScoreService highScoreService ) {
        
        this.highScoreService = highScoreService;

        errorLabel = new Label();
        
        scoreLabels = new ArrayList<>();
        
        layout = new VBox();
        
        layout.getChildren().add(new Label("Highscores"));
        
        layout.getChildren().add(errorLabel);
        
        List<HighScore> highScores = loadHighScores();
        
        for (int i = 0; i < highScores.size(); i++) {
            Label label = new Label(); 
            layout.getChildren().add(label);
            scoreLabels.add(label);
        }
        
        setHighScores(highScores);
    
    }
    
    private void setHighScores(List<HighScore> highScores) {
        
        for (int i = 1; i < scoreLabels.size(); i++) {
            if (i < highScores.size()) {
                HighScore highScore = highScores.get(i);
                scoreLabels.get(i).setText(i + ". " + highScore.getName() + " " 
                        +  highScore.getScore());
            }

        }    
    }
    
    public void updateHighScores() {
            setHighScores(highScoreService.getHighScores());
    }

    public VBox getLayout() {
        return layout;
    }
    
    private List<HighScore> loadHighScores() {
        
         List<HighScore> highScores = highScoreService.getHighScores();
         
         if (highScores == null) {
             errorLabel.setText("Failed to load highscores from the database.");
             highScores = new ArrayList<>();
         }
        
         return highScores;
    }
    
    
    
  
    
    
    
}
