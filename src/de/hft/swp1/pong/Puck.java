package de.hft.swp1.pong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.Timer;

public class Puck extends Point2D.Double

{
    /** Attributes */

    /**
     * diameter (Durchmesser) the puck optically has
     */
    public int diameter = 18;

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
     * pucks speed
     */
    private int speed = 2;
    
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
        // random start-direction (upwards)
        unitVector = new DirectionVector(new double [] {-Math.random(),-Math.random()});
        unitVector.normalize();
        // mover constantly moving puck around
        mover = new Timer(8, new ActionListener() {
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
     * increase speed of puck
     */
    public void faster()
    {
        if(speed < 9){
            speed += 1;
        }
    }

    /**
     * Operation move.<br />
     * 
     * move puck once in his direction, at current speed (update points location)
     *
     */
    public void move()
    {
        x = x + unitVector.getValue(1)*speed;
        y = y + unitVector.getValue(2)*speed;
    }
}
