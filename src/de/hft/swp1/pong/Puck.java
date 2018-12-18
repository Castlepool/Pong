package de.hft.swp1.pong;

import java.awt.geom.Point2D;

import javax.swing.Timer;

public class Puck extends Point2D.Double

{
    /** Attributes */

    /**
     * diameter the puck optically has
     */
    public final int DIAMETER = 10;

    /**
     * timer that moves the puck in it's vectors direction
     */
    private Timer mover;

    /** Associations */

    /**
     * pucks direction
     */
    private DirectionVector unitVector;

    /**
     * Operation Puck
     *
     * @return
     */
    public Puck()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Operation setUnitVector
     * 
     * set new vector for puck
     *
     * @param x -
     * @param y -
     */
    public void setUnitVector(double x, double y)
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Operation getUnitVector
     *
     * @return DirectionVector pucks direction
     */
    public DirectionVector getUnitVector()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * starts the timer that moves the puck
     */
    public void start()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * stops the timer that moves the puck
     */
    public void stop()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * highers the timeout of the mover Timer
     */
    public void slower()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * lowers the timout of the mover Timer
     */
    public void faster()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Operation move.<br />
     * 
     * move puck once in his direction
     *
     */
    public void move()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
