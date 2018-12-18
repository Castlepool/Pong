package de.hft.swp1.pong;

import javax.swing.Timer;

public class Player extends PongLine
{
    /** Attributes */

    /**
     * constant speed value for the player movage
     */
    private final int SPEED = 5;

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

        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * starts to move the player
     * @param direction
     */
    public void startMove(Side direction)
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * stops to move the player
     */
    public void stopMove()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
