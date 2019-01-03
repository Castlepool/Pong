package de.hft.swp1.pong;

import static de.hft.swp1.pong.Application.ROOTFRAME;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
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
    protected static int score;

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
        // "Dispatcher" auf Deutsch: Verteiler
        kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if(refresher.isRunning()){
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
        System.out.println(width + " " + height);
        PongLine bLeft = new PongLine(0, height, width/2, 0, Side.RIGHT, "border_Left");
        PongLine bRight = new PongLine(width/2, 0, width, height, Side.RIGHT, "border_RIGHT");
        player = new Player(width/2+40, height-60, width/2-40, height-60, Side.RIGHT, "player");
        borders.add(bLeft);
        borders.add(bRight);
        borders.add(player);
        puck = new Puck(width/2, height/2, (int) (height*0.035));
        playground = new PlayFieldPanel(playFieldSize, borders, puck);
        score = 0;
    }

    /**
     * Operation to init main game components
     *
     */
    private void initComponents()
    {
        add(playground);
        refresher = new Timer(14, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update distances to puck
                borders.forEach( line -> {
                    double dist = calcDistance(line);
                    line.setNewPuckDistance(dist);
                });
                // react to collision
                checkForCollission();
                // stop if puck out of playground
                if(puck.x < 0 ||
                        puck.x > playground.getPreferredSize().width ||
                        puck.y < 0 ||
                        puck.y > playground.getPreferredSize().height){
                    System.out.println("Puck out of Playground!");
                    exitIngame();
                } else{
                    playground.repaint();
                }
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
            if(line.puckDistanceCurrent < line.WIDTH && line.movesToMe()){
                System.out.println("Moves to me: " + line.movesToMe());
                changeDirection(line);
                puck.faster();
                score++;
                return;
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
        
        double angleIncidence = Math.acos(lineVector.scalar(puckVector) / (lineVector.length() * puckVector.length()));
        //if(angle > Math.PI/2) angle = Math.PI - angle;
        //double rotateAngle = -(Math.PI + 2*angle);
        double rotationAngle = 2*angleIncidence;
        if(angleIncidence > Math.PI/2) {
            angleIncidence = Math.PI - angleIncidence;
            rotationAngle = -2*angleIncidence;
        }
        
        double xNew = puck.x * Math.cos(rotationAngle) - puck.y * Math.sin(rotationAngle);
        double yNew = puck.x * Math.sin(rotationAngle) + puck.y * Math.cos(rotationAngle);

        puck.setUnitVector(xNew, yNew);
        puck.getUnitVector().normalize();
        
        double angleReflection = Math.acos(lineVector.scalar(puckVector) / (lineVector.length() * puckVector.length()));
        if(angleReflection > Math.PI/2) {
            angleReflection = Math.PI - angleReflection;
        }
        System.out.println("-------------------------");
        System.out.println("Line: " + line.name);
        System.out.println("Time: " + Calendar.getInstance().get(Calendar.SECOND) + ":" + Calendar.getInstance().get(Calendar.MILLISECOND));
        System.out.println("Angle of incidence (Einfallswinkel): " + String.format( "%.2f", angleIncidence/Math.PI) + "\u03C0" 
            + "\nAngle of reflection (Ausfallswinkel): " + String.format( "%.2f", angleReflection/Math.PI) + "\u03C0");
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
        puck.stop();
        player.stopMove();
        refresher.stop();
        AfterGame afterGame = new AfterGame(score);
        ROOTFRAME.getContentPane().removeAll();
        //ROOTFRAME.remove(playground);
        ROOTFRAME.add(afterGame);
        ROOTFRAME.revalidate();
        ROOTFRAME.repaint();
    }
}
