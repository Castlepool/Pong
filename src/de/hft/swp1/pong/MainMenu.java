package de.hft.swp1.pong;

import static de.hft.swp1.pong.Application.ROOTFRAME;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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
        width = new JTextField("800", 10);
        height = new JTextField("600", 10);
        JPanel textFieldPanel = new JPanel();
        
        setLayout(new BorderLayout());
        
        textFieldPanel.add(width);
        textFieldPanel.add(height);
        
        add(textFieldPanel, BorderLayout.CENTER);
        
        btnStartNewGame.setText("Start New Game");
        buttonPanel.add(btnStartNewGame);
        
        btnExit.setText("Exit");
        buttonPanel.add(btnExit);
        
        add(buttonPanel, BorderLayout.PAGE_END);
        
        // Action Listeners
        btnExit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        btnStartNewGame.addActionListener((e) -> {
            if(!width.getText().matches("^[0-9]{2,4}$") || !height.getText().matches("^[0-9]{2,4}$")) {
                JOptionPane.showMessageDialog(null, "Invalid resolution!");
                return;
            }
            Dimension dim = new Dimension(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));
            startNewGame(dim);
        });
    }
    
    public static void startNewGame(Dimension dim){
        InGame inGame = new InGame(dim);
        ROOTFRAME.getContentPane().removeAll();
        ROOTFRAME.setSize(dim);
        ROOTFRAME.add(inGame);
        ROOTFRAME.revalidate();
        ROOTFRAME.repaint();
    }
}
