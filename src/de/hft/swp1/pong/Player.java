package de.hft.swp1.pong;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Player extends PongLine
{
    /** Attributes */

    /**
     * speed value for the player
     */
    private int speed;

    /**
     * Timer that moves the player
     */
    private Timer mover;

    /** Associations */

    /**
     * current direction (left or right) of player
     */
    private Side direction;

    /**
     * 
     * @param startX
     * @param startY
     * @param destX
     * @param destY
     * @param name
     */
    public Player(double startX, double startY, double destX, double destY, String name)
    {
        super(startX, startY, destX, destY, name);
        speed = (int) Math.round( (x1-x2) * 0.2);
        // mover, will be running while (left/right) key is pressed
        mover = new Timer(8, (ActionEvent e) -> {
            int width = Application.ROOTFRAME.getSize().width;
            // only moves in certain range
            if(direction == Side.LEFT && x1 > (x1-x2+20) ) {
                this.setLine(x1-speed, y1, x2-speed, y2);
            }
            else if(direction == Side.RIGHT && x2 < width-(x1-x2+20) ) {
                this.setLine(x1+speed, y1, x2+speed, y2);
            }
        });
        mover.setInitialDelay(1);
    }

    /**
     * starts to move the player
     * @param direction
     */
    public void startMove(Side direction)
    {
        if(!mover.isRunning()){
            this.direction = direction;
            mover.start();
        }
    }

    /**
     * stops to move the player
     */
    public void stopMove()
    {
        if(mover.isRunning()){
            mover.stop();
        }
    }
    
    /**
     * stops to move the player
     */
    public boolean isMoving()
    {
        return mover.isRunning();
    }
    
    /**
     * set speed of player
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
}
