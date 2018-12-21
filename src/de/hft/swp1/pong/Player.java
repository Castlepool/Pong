package de.hft.swp1.pong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Player extends PongLine
{
    /** Attributes */

    /**
     * speed value for the player movage
     */
    private final int SPEED = 20;

    /**
     * Timer that moves the player
     */
    private Timer mover;

    /** Associations */

    private Side direction;

    /**
     * 
     * @param startX
     * @param startY
     * @param destX
     * @param destY
     * @param position
     */
    public Player(double startX, double startY, double destX, double destY, Side position)
    {
        super(startX, startY, destX, destY, position);
        mover = new Timer(30, (ActionEvent e) -> {
            if(direction == Side.LEFT) this.setLine(x1-SPEED, y1, x2-SPEED, y2);
            else this.setLine(x1+SPEED, y1, x2+SPEED, y2);
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
    
}
