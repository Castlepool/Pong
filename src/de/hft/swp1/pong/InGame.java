package de.hft.swp1.pong;

import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * The complete ingame logic is handled from here.
 */
public class InGame extends JPanel

{
    /** Attributes */

    /**
     * JPanel for the ingame sidebar holding informations like the current score, if
     * the game is paused, etc.
     */
    private JPanel stats;

    /**
     * textfield showing the current score during ingame
     */
    private JTextField score;

    /**
     * timer to redraw the picture
     */
    private Timer refresher;

    /** Associations */

    /**
     * puck on the field
     */
    private Puck puck;

    /**
     * playfield borders
     */
    private HashSet<PongLine> borders;

    private Player player;

    /**
     * panel the content is shown on
     */
    private PlayFieldPanel playground;
    
    KeyboardFocusManager kfm;
    
    /**
     * Operation InGame
     *
     * @param playFieldSize - the slze we have to create our PlayFieldPanel with.
     * 
     */
    public InGame(Dimension playFieldSize)
    {
        kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                KeyStroke ks = KeyStroke.getKeyStrokeForEvent(e);
                int type = ks.getKeyEventType();
                if(type == KeyEvent.KEY_PRESSED) {
                    if(ks.equals(KeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, 0))){
                        player.startMove(Side.LEFT);
                    } else if(ks.equals(KeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, 0))){
                        player.startMove(Side.RIGHT);
                    }
                    return true;
                }
                if(type == KeyEvent.KEY_RELEASED) {
                    player.stopMove();
                    return true;
                }
                return false;
            }
        });
        
        initPlayFieldPanel(playFieldSize);
        initComponents();
        refresher.start();
        puck.start();
    }

    /**
     * Operation to init the PlayFieldPanel
     *
     * @param playFieldSize - the slze we have to create our PlayFieldPanel with.
     */
    public void initPlayFieldPanel(Dimension playFieldSize)
    {
        borders = new HashSet<>();
        int width = playFieldSize.width;
        int height = playFieldSize.height;
        PongLine bLeft = new PongLine(0, height, width/2, 0, Side.RIGHT);
        PongLine bRight = new PongLine(width/2, 0, width, height, Side.RIGHT);
        player = new Player(width/2-40, height-20, width/2+40, height-20, Side.BOTTOM);
        borders.add(bLeft);
        borders.add(bRight);
        borders.add(player);
        puck = new Puck(width/2, height/2);
        playground = new PlayFieldPanel(playFieldSize, borders, puck);
    }

    /**
     * Operation to init main game components
     *
     */
    private void initComponents()
    {
        add(playground);
        refresher = new Timer(6, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkForCollission();
                playground.repaint();
            }
        });
    }

    /**
     * Operation to init statsPanel
     *
     */
    private void initStatsPanel()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Operation to set the borders according the choosen playfieldsize
     *
     * @param playFieldSize - playfieldsize
     */
    private void setLines(Dimension playFieldSize)
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Operation to setup Puck
     *
     */
    private void setPuck()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Operation to movePlayer.<br />
     * get's called from the Timer that moves the player
     *
     * @param movement - value the puck should be moved
     */
    private void movePlayer(int movement)
    {
        //player.startMove(Side.LEFT);
    }

    /**
     * Operation checkForCollission.<br />
     * 
     * check for collissions between the puck and each border
     *
     */
    private void checkForCollission()
    {
        borders.forEach( line -> {
            double dist = calcDistance(line);
            line.setNewPuckDistance(dist);
            if(dist < line.WIDTH && line.movesToMe()){
                changeDirection(line);
                puck.faster();
            }
        });
    }

    /**
     * Operation calcDistance between a line and the puck
     *
     * @param line - border for which we want to check the puck distance
     * 
     * @return double calculated distance between puck and line
     */
    private double calcDistance(PongLine line)
    {
        return line.ptLineDist(puck);
    }

    /**
     * Operation changeDirection.<br />
     * 
     * mirrors the puck on the given line.
     *
     * @param line - border the puck should be mirrored on
     */
    private void changeDirection(PongLine line)
    {
        DirectionVector lineVector = line.getDirectionVector();
        DirectionVector puckVector = puck.getUnitVector();
        
        double angle = Math.acos(lineVector.scalar(puckVector) / (lineVector.length() * puckVector.length()));
        double rotateAngle = 2*Math.PI - 2*angle;
        
        double xNew = puck.x * Math.cos(rotateAngle) - puck.y * Math.sin(rotateAngle);
        double yNew = puck.x * Math.sin(rotateAngle) + puck.y * Math.cos(rotateAngle);

        puck.setUnitVector(xNew, yNew);
        //puck.setUnitVector(-puck.getUnitVector().getValue(1), -puck.getUnitVector().getValue(2));
    }

    /**
     * Operation exitIngame.<br />
     * 
     * - stop all timers<br />
     * - collect data like score to give to the next Panel<br />
     *
     */
    private void exitIngame()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
