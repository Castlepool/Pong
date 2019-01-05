package de.hft.swp1.pong;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Main class to run the Application.
 * @author Samuel Mergenthaler (template from Lukas Wiest)
 */
public class Application

{
    /** Attributes */
    /**
     * name of programs configuration files base folder<br />
     * So if you want to write somefiles onto the disk, please do it all in a
     * central place like this.
     */
    protected static final String BASEFOLDER = System.getProperty("user.home") + "/.swp1ws1819/group_xyz";

    /**
     * Root JFrame into which we put our JComponents we want to show.
     */
    protected static final JFrame ROOTFRAME = new JFrame();

    /**
     * Operation main
     *
     * @param args - CLI arguments
     */
    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ROOTFRAME.setTitle("Pong");
                ROOTFRAME.add(new MainMenu());
                Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int width = screenSize.width / 6;
                int height = screenSize.height / 3;
                ROOTFRAME.setSize(width, height);
                ROOTFRAME.setLocation(10,10);
                ROOTFRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                ROOTFRAME.setName("Pong");
                ROOTFRAME.setVisible(true);
            }
        });
    }
}
