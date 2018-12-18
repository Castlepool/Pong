package de.hft.swp1.pong;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * First JPanel that get's visible, asking for width and height of playfield.
 * Buttons for exiting and starting the game.
 */
public class MainMenu extends JPanel

{
    /** Attributes */

    /**
     * button to start the new game
     */
    private JButton btnStartNewGame;

    /**
     * button to exit the game
     */
    private JButton btnExit;

    /**
     * Textfield to enter the wished playfield height
     */
    private JTextField height;

    /**
     * Textfield to enter the wished playfield width
     */
    private JTextField width;

    /**
     * Operation MainMenu.<br />
     * 
     * - construct this JPanel<br />
     * - init the components you need for this (see method below)<br />
     * - remove all elements from the root frame<br />
     * - add this panel to the root frame
     *
     * @return
     */
    public MainMenu()
    {
        initComponents();
    }

    /**
     * Operation initComponents.<br />
     * 
     * initialize all fields and additionally needed thing like Actions, etc.
     *
     */
    private void initComponents()
    {
        btnStartNewGame = new JButton();
        btnExit = new JButton();
        JPanel buttonPanel = new JPanel();
        height = new JTextField();
        width = new JTextField();
        JPanel textFieldPanel = new JPanel();
        
        setLayout(new BorderLayout());
        
        btnStartNewGame.setText("Start New Game");
        buttonPanel.add(btnStartNewGame);
        
        btnExit.setText("Exit");
        btnExit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        buttonPanel.add(btnExit);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        textFieldPanel.add(height);
        
        textFieldPanel.add(width);
        
        add(textFieldPanel, BorderLayout.CENTER);
    }
}
