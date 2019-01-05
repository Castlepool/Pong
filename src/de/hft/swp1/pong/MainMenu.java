package de.hft.swp1.pong;

import static de.hft.swp1.pong.Application.ROOTFRAME;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

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
     * Dropdown of color-schemes to choose from
     */
    private JComboBox dropdownColors;
    
    /**
     * Checkbox to show additional information in game
     */
    private JCheckBox checkBoxShowInfo;
    
    /**
     * flag that sets "showInfo"-mode
     */
    protected static boolean showInfo = false;
    
    /**
     * Operation MainMenu
     */
    public MainMenu()
    {
        initComponents();
    }

    /**
     * Operation initComponents
     */
    private void initComponents()
    {
        // initialize components
        btnStartNewGame = new JButton("Start New Game");
        btnExit = new JButton("Exit");
        JPanel buttonPanel = new JPanel();
        width = new JTextField("1200", 10);
        height = new JTextField("800", 10);
        dropdownColors = new JComboBox(new String [] {"Black", "Grey"});
        checkBoxShowInfo = new JCheckBox("show additional ingame-info", false);
        JPanel optionsPanel = new JPanel();
        
        setLayout(new BorderLayout());
        
        // set up menu-options
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        optionsPanel.add(new JLabel("Resolution:"));
        JPanel helperPanel1 = new JPanel();
        helperPanel1.add(new JLabel("Width: "));
        helperPanel1.add(width);
        optionsPanel.add(helperPanel1);
        JPanel helperPanel2 = new JPanel();
        helperPanel2.add(new JLabel("Height: "));
        helperPanel2.add(height);
        optionsPanel.add(helperPanel2);
        optionsPanel.add(new JLabel("Background-Color:"));
        JPanel helperPanel3 = new JPanel();
        helperPanel3.add(dropdownColors);
        optionsPanel.add(helperPanel3);
        optionsPanel.add(checkBoxShowInfo);
        add(optionsPanel, BorderLayout.CENTER);
        
        // set up buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        buttonPanel.add(btnStartNewGame);
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
            switch(dropdownColors.getSelectedItem().toString()){
                case "Grey":
                    PlayFieldPanel.backgroundColor = Color.DARK_GRAY;
            }
            showInfo = checkBoxShowInfo.isSelected();
            startNewGame(dim);
        });
    }
    
    /**
     * Operation startNewGame
     */
    public static void startNewGame(Dimension dim){
        InGame inGame = new InGame(dim);
        ROOTFRAME.getContentPane().removeAll();
        ROOTFRAME.setSize(dim);
        ROOTFRAME.add(inGame);
        ROOTFRAME.revalidate();
        ROOTFRAME.repaint();
    }
}
