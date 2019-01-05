package de.hft.swp1.pong;

import java.awt.geom.Line2D;

public class PongLine extends Line2D.Double

{
    /** Attributes */
    
    /**
     * width of borders (for collision-detection)
     */
    public final float WIDTH = 20;

    /**
     * puck distance after latest calculation
     */
    protected double puckDistanceCurrent;

    /**
     * puck distance of second last calculation
     */
    private double puckDistanceBefore;

    /** Associations */

    /**
     * direction Vector of this line
     */
    private DirectionVector dVector;

    
    
    public String name;
    /**
     * Operation PongLine
     *
     * @param startX   -
     * @param startY   -
     * @param destX    -
     * @param destY    -
     * @param name - a name for this border
     * @return
     */
    public PongLine(double startX, double startY, double destX, double destY, String name)
    {
        // x1, x2 etc. are fields of Line2D.Double, from which we inherit them
        x1 = startX;
        y1 = startY;
        x2 = destX;
        y2 = destY;
        DirectionVector d = new DirectionVector(new double [] {x2-x1, y2-y1});
        d.normalize();
        dVector = d;
        this.name = name;
    }

    /**
     * Operation setNewPuckDistance.<br />
     * 
     * set a new distance to the puck and move the last one to be the second last
     * one.
     *
     * @param distance - distance between puck and line
     */
    public void setNewPuckDistance(double distance)
    {
        puckDistanceBefore = puckDistanceCurrent;
        puckDistanceCurrent = distance;
    }

    /**
     * Operation movesToMe.<br />
     * 
     * checks with the last two stored distances, wether the puck is moving towards the line
     *
     * @return boolean
     */
    public boolean movesToMe()
    {
        return (puckDistanceCurrent < puckDistanceBefore) ? true : false;
    }

    /**
     * Operation getDirectionVector
     *
     * @return DirectionVector of this line.
     */
    public DirectionVector getDirectionVector()
    {
        return dVector;
    }
}
