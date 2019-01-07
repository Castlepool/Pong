package de.hft.swp1.pong;

import static de.hft.swp1.pong.Application.ROOTFRAME;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * The complete ingame logic is handled from here.
 */
public class InGame extends JPanel

{
    /** Attributes */

    /**
     * current score during ingame
     */
    protected static int score = 0;
    
    /**
     * gameOver-flag
     */
    private boolean gameOver = false;
    
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
    
    /**
     * player (special "border", which can be moved)
     */
    private Player player;

    /**
     * panel the content is shown on
     */
    private PlayFieldPanel playground;
    
    /**
     * KeyboardFocusManager to catch left/right-key
     */
    KeyboardFocusManager kfm;
    
    /**
     * Operation InGame
     *
     * @param playFieldSize - the size we have to create our PlayFieldPanel with.
     * 
     */
    public InGame(Dimension playFieldSize)
    {
        // react to keys left/right to move player
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
                        } else if(ks.equals(KeyStroke.getAWTKeyStroke(KeyEvent.VK_BACK_SPACE, 0))){
                            exitIngame();
                            return true;
                        }
                        return true;
                    } else if(type == KeyEvent.KEY_RELEASED) {
                        player.stopMove();
                        return true;
                    } 
                }
                return false;
            }
        });
        // intialize all necessary stuff and start timers
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
    private void initPlayFieldPanel(Dimension playFieldSize)
    {
        borders = new HashSet<>();
        int width = playFieldSize.width;
        int height = playFieldSize.height;
        System.out.println("playfield-size: " + width + " " + height);
        PongLine bLeft = new PongLine(0, height, width/2, 0, "border_Left");
        PongLine bRight = new PongLine(width/2, 0, width, height, "border_RIGHT");
        player = new Player(width/2+width/26, height-60, width/2-width/26, height-60, "player");
        borders.add(bLeft);
        borders.add(bRight);
        borders.add(player);
        puck = new Puck(width/2, height/2, (int) (height*0.035));
        playground = new PlayFieldPanel(playFieldSize, borders, puck);
    }

    /**
     * Operation to init main game components
     */
    private void initComponents()
    {
        // add playground to this panel
        add(playground);
        // main-refresher for regular collision-check and repainting
        // (about 60 times a second)
        refresher = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update distances to puck
                borders.forEach( line -> {
                    double dist = line.ptLineDist(puck);
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
     * Operation checkForCollission.<br />
     * 
     * check for collissions between the puck and each border
     * change direction if necessary
     *
     */
    private void checkForCollission()
    {
        for (Iterator<PongLine> iterator = borders.iterator(); iterator.hasNext();) {
            PongLine line = iterator.next();
            if(line.puckDistanceCurrent < line.WIDTH && line.movesToMe()){
                changeDirection(line);
                puck.faster();
                if(!gameOver) score++;
                return;
            }
        }
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
        // if puck missed player => game over
        if(line.name.equals("player") && (puck.x > line.x1+20 || puck.x < line.x2-20)){
            gameOver = true;
            return;
        }
        
        // get Direction Vectors for border and puck
        DirectionVector lineVector = line.getDirectionVector();
        DirectionVector puckVector = puck.getUnitVector();
        
        // angle between line and direction-vector of puck 
        // (formular is so easy because both vectors are aleady normalized)
        double angleIncidence = Math.acos(lineVector.scalar(puckVector));
        // angle to rotate puck-direction (depends on incoming direction)
        double rotationAngle;
        if(angleIncidence > Math.PI/2) {
            angleIncidence = Math.PI - angleIncidence;
            rotationAngle = -2*angleIncidence;
        } else {
            rotationAngle = 2*angleIncidence;
        }
        
        // new unit-vector-coordinates (using a rotaion matrix, look it up in wikipedia)
        double xNew = puck.getUnitVector().getValue(1) * Math.cos(rotationAngle) - puck.getUnitVector().getValue(2) * Math.sin(rotationAngle);
        double yNew = puck.getUnitVector().getValue(1) * Math.sin(rotationAngle) + puck.getUnitVector().getValue(2) * Math.cos(rotationAngle);
        
        // set new unitvector for puck
        puck.setUnitVector(xNew, yNew);
        
        // console-output: Only if "More-Info"-Mode is enabled
        if(MainMenu.showInfo){
            double angleReflection = Math.acos(lineVector.scalar(puckVector) / (lineVector.length() * puckVector.length()));
            if(angleReflection > Math.PI/2) {
                angleReflection = Math.PI - angleReflection;
            }
            System.out.println("-------------------------");
            System.out.println("Line: " + line.name);
            System.out.println("Time: " + Calendar.getInstance().get(Calendar.SECOND) + ":" + Calendar.getInstance().get(Calendar.MILLISECOND));
            System.out.println("Angle of incidence (Einfallswinkel): " + String.format( "%.2f", angleIncidence/Math.PI) + "\u03C0" 
                + "\nAngle of reflection (Ausfallswinkel): " + String.format( "%.2f", angleReflection/Math.PI) + "\u03C0");
        }
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
        score = 0;
        ROOTFRAME.getContentPane().removeAll();
        ROOTFRAME.add(afterGame);
        ROOTFRAME.revalidate();
        ROOTFRAME.repaint();
    }
}
