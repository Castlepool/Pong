package de.hft.swp1.pong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public Puck(int width, int height)
    {
        x = width;
        y = height;
        unitVector = new DirectionVector(new double [] {0.5,-1.0});
        mover = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        mover.setInitialDelay(500);
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
        unitVector.setVector(new double [] {x,y}, true);
    }

    /**
     * Operation getUnitVector
     *
     * @return DirectionVector pucks direction
     */
    public DirectionVector getUnitVector()
    {
        return unitVector;
    }

    /**
     * starts the timer that moves the puck
     */
    public void start()
    {
        mover.start();
    }

    /**
     * stops the timer that moves the puck
     */
    public void stop()
    {
        mover.stop();
    }

    /**
     * highers the timeout of the mover Timer
     */
    public void slower()
    {
        mover.stop();
        mover.setDelay(mover.getDelay()+1);
        mover.start();
    }

    /**
     * lowers the timout of the mover Timer
     */
    public void faster()
    {
        mover.stop();
        mover.setDelay(mover.getDelay()-1);
        mover.start();
    }

    /**
     * Operation move.<br />
     * 
     * move puck once in his direction
     *
     */
    public void move()
    {
        // move in direction of unitvector (update points location)
        x = x + unitVector.getValue(1);
        y = y + unitVector.getValue(2);
    }
}
