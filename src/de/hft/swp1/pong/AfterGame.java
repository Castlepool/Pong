package de.hft.swp1.pong;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
    private JButton next;

    /**
     * don't add the score to the list, just show it
     */
    private JButton skip;

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
        next = new JButton();
        skip = new JButton();
        JPanel buttonPanel = new JPanel();
        player = new JTextField("Name", 10);
        score = new JTextField("", 10);
        score.setEditable(false);
        JPanel textFieldPanel = new JPanel();
        
        setLayout(new BorderLayout());
        
        textFieldPanel.add(player);
        textFieldPanel.add(score);
        
        add(textFieldPanel, BorderLayout.CENTER);
        
        next.setText("Next");
        buttonPanel.add(next);
        
        skip.setText("Skip");
        buttonPanel.add(skip);
        
        add(buttonPanel, BorderLayout.PAGE_END);
        
        // Action Listeners
        skip.addActionListener( e -> {
        java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainMenu.startNewGame(Application.ROOTFRAME.getSize());
                }
            });
        });
        
        next.addActionListener( e -> {
            Highscore highscore = new Highscore(player.getText(), Integer.valueOf(score.getText()));
            
        });
    }
}
