package de.hft.swp1.pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
            // debbuging info
            if(!line.name.equals("player")){
                g2d.drawString("Distance from Puck: " + String.valueOf(line.ptLineDist(puck)), (int) (line.x1 + line.x2) / 2 - 80, dim.width / 2 - 80);
                g2d.drawString("Moves to me: " + line.movesToMe(), (int) (line.x1 + line.x2) / 2 - 80, dim.width / 2 - 120);
            } else{
                g2d.drawString("Distance from Puck: " + String.valueOf(line.ptLineDist(puck)), (int) line.x1 - 40, (int) line.y1 - 40);
                g2d.drawString("Moves to me: " + line.movesToMe(), (int) line.x1 - 40, (int) line.y1 - 20);
            }
            
        }
        g2d.fill(new Ellipse2D.Double(puck.x - puck.diameter/2, 
                puck.y - puck.diameter/2, puck.diameter, puck.diameter));
        // Arrow for puck
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Line2D.Double(puck.x, puck.y, (double)puck.x + puck.getUnitVector().getValue(1) * 40.0, (double)puck.y + puck.getUnitVector().getValue(2) * 40.0));
        g2d.setFont(new Font("Monospaced", 0, (int) (dim.width*0.06)));
        int margin = (int) (dim.width*0.1);
        g2d.drawString(String.valueOf(InGame.score), dim.width - margin, margin);
        g2d.dispose();
    }
}
