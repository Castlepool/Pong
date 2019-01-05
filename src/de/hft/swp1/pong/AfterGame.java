package de.hft.swp1.pong;

import static de.hft.swp1.pong.Application.ROOTFRAME;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * collects player data if score should be listed on the highscores list
 */
public class AfterGame extends JPanel
{
    /** Attributes */

    /**
     * for inputing players name
     */
    private JTextField player;

    /**
     * showing the reached score
     */
    private JTextField score;

    /**
     * adding the score with the entered Player name to the list and show it
     */
    private JButton saveScore;

    /**
     * don't add the score to the list, just show it
     */
    private JButton tryAgain;
    
    /**
     * table of highscores of this session
     */
    private static Highscores highscoresScrollPane;
    
    /**
     * Operation AfterGame
     *
     * @param score - reached score during game
     * @return
     */
    public AfterGame(int score)
    {
        initComponents();
        this.score.setText(String.valueOf(score));
    }

    /**
     * Operation initComponents
     *
     */
    private void initComponents()
    {
        // init stuff
        saveScore = new JButton("Save Score");
        tryAgain = new JButton("Try Again");
        JPanel buttonPanel = new JPanel();
        player = new JTextField("Your Name", 10);
        score = new JTextField("", 10);
        score.setEditable(false);
        JButton returnToMenu = new JButton("Return to Main Menu");
        JPanel textFieldPanel = new JPanel();
        
        setLayout(new BorderLayout());
        
        // set up text fields
        textFieldPanel.add(new JLabel("Your Name: "));
        textFieldPanel.add(player);
        textFieldPanel.add(new JLabel("Your Score: "));
        textFieldPanel.add(score);
        add(textFieldPanel, BorderLayout.PAGE_START);
        
        // set up buttons
        buttonPanel.add(saveScore);
        buttonPanel.add(tryAgain);
        buttonPanel.add(returnToMenu);
        add(buttonPanel, BorderLayout.PAGE_END);
        
        // show highscore-Table, create new one if none exists
        if(highscoresScrollPane == null){
            highscoresScrollPane = new Highscores();
        }
        add(highscoresScrollPane, BorderLayout.CENTER);
        
        // Action Listeners
        tryAgain.addActionListener( e -> {
            MainMenu.startNewGame(Application.ROOTFRAME.getSize());
        });
        
        saveScore.addActionListener( e -> {
            Highscore highscore = new Highscore(player.getText(), Integer.valueOf(score.getText()));
            highscoresScrollPane.addHighscore(highscore);
            updateTable();
        });
        
        returnToMenu.addActionListener( e -> {
            ROOTFRAME.setVisible(false);
            ROOTFRAME.getContentPane().removeAll();
            Application.main(null);
        });
    }
    
    private void updateTable(){
        add(highscoresScrollPane, BorderLayout.CENTER);
        saveScore.setVisible(false);
        ROOTFRAME.revalidate();
        ROOTFRAME.repaint();
    }
}
