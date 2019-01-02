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
    public int diameter = 20;

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
    public Puck(int width, int height, int diameter)
    {
        x = width;
        y = height;
        this.diameter = diameter;
        //unitVector = new DirectionVector(new double [] {-Math.random(),-Math.random()});
        unitVector = new DirectionVector(new double [] {-1.0,-0.55});
        mover = new Timer(30, new ActionListener() {
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
        if(mover.getDelay() < 60){
            mover.setDelay(mover.getDelay()+1);
        }
    }

    /**
     * lowers the timout of the mover Timer
     */
    public void faster()
    {
        if(mover.getDelay() > 8){
            mover.setDelay(mover.getDelay()-1);
        }
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
        x = x + unitVector.getValue(1)*10;
        y = y + unitVector.getValue(2)*10;
    }
}
