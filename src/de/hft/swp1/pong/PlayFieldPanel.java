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
     * background color (customisable in menu)
     */
    protected static Color backgroundColor = Color.BLACK;
    
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
    @Override
    public void paintComponent(Graphics gc)
    {
        // Graphics2D has more options
        Graphics2D g2d = (Graphics2D) gc.create();
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(backgroundColor);
        Dimension dim = getSize();
        g2d.fillRect(0, 0, dim.width, dim.height);
        g2d.setColor(Color.ORANGE);
        for(PongLine line : lines){
            g2d.draw(line);
        }
        g2d.fill(new Ellipse2D.Double(puck.x - puck.diameter/2, 
                puck.y - puck.diameter/2, puck.diameter, puck.diameter));
        g2d.setStroke(new BasicStroke(3));
        g2d.setFont(new Font("Monospaced", 0, (int) (dim.width*0.06)));
        int margin = (int) (dim.width*0.1);
        g2d.drawString(String.valueOf(InGame.score), dim.width - margin, margin);
        
        // Only if "More-Info"-Mode is enabled
        if(MainMenu.showInfo){
            // draw line that shows puck-direction
            g2d.draw(new Line2D.Double(puck.x, puck.y, 
                    (double)puck.x + puck.getUnitVector().getValue(1) * 200.0, 
                    (double)puck.y + puck.getUnitVector().getValue(2) * 200.0));
            g2d.setFont(new Font("Monospaced", 0, (int) (dim.width*0.01)));
            g2d.setColor(Color.WHITE);
            for(PongLine line : lines){
                // show info about distance an direction
                if(line.name.equals("player")){
                    g2d.drawString("Distance from Puck: " + String.format( "%.0f", line.ptLineDist(puck)), (int) line.x2, (int) line.y1 - 40);
                    g2d.drawString("Moves to me: " + line.movesToMe(), (int) line.x2, (int) line.y1 - 20);
                } else{
                    g2d.drawString("Distance from Puck: " + String.format( "%.0f", line.ptLineDist(puck)), (int) ((line.x1+line.x2)/2 - dim.width/10), (int) (line.y1+line.y2)/2);
                    g2d.drawString("Moves to me: " + line.movesToMe(), (int) ((line.x1+line.x2)/2 - dim.width/10), (int) (line.y1+line.y2)/2 + 20);
                }
            }
        }
        g2d.dispose();
    }
}
