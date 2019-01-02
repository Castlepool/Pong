package de.hft.swp1.pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashSet;

import javax.swing.JPanel;

/**
 * playfield
 */
public class PlayFieldPanel extends JPanel

{
    /** Attributes */

    /**
     * playfield borders
     */
    private final HashSet<PongLine> lines;

    /**
     * puck
     */
    private final Puck puck;

    /**
     * Operation PlayFieldPanel
     *
     * @param playFieldSize - size of this panel
     * @param left          -
     * @param right         -
     * @param puck          -
     * @param player        -
     * @return
     */
    public PlayFieldPanel(Dimension playFieldSize, HashSet<PongLine> lines, Puck puck)
    {
        this.lines = lines;
        this.puck = puck;
        initComponents(playFieldSize);
    }

    /**
     * Operation initComponents
     *
     * @param playFieldSize - size of this panel
     */
    private void initComponents(Dimension playFieldSize)
    {
        setPreferredSize(playFieldSize);
    }

    /**
     * Operation paintComponent
     * 
     * painting all elements placed on this panel
     *
     * @param gc - Graphics to draw on
     */
    public void paintComponent(Graphics gc)
    {
        //super.paintComponent(gc);
        Graphics2D g2d = (Graphics2D) gc.create();
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.BLACK);
        Dimension dim = getSize();
        g2d.fillRect(0, 0, dim.width, dim.height);
        g2d.setColor(Color.ORANGE);
        for(PongLine line : lines){
            g2d.draw(line);
        }
        g2d.draw(new Ellipse2D.Double(puck.x - puck.DIAMETER/2, 
                puck.y - puck.DIAMETER/2, puck.DIAMETER, puck.DIAMETER));
        g2d.drawString(String.valueOf(InGame.score), getPreferredSize().width-200, 80);
        g2d.dispose();
    }
}
