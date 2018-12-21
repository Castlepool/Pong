package de.hft.swp1.pong;

import java.awt.geom.Line2D;

public class PongLine extends Line2D.Double

{
    /** Attributes */
    public final float WIDTH = 3;

    /**
     * puck distance after latest calculation
     */
    private double puckDistanceCurrent;

    /**
     * puck distance of second last calculation
     */
    private double puckDistanceBefore;

    /** Associations */

    /**
     * direction Vector of this line
     */
    private DirectionVector dVector;

    /**
     * which side of the line (according to the direction Vector) is on the inside
     * of the playfield
     */
    private Side inside;

    /**
     * Operation PongLine
     *
     * @param startX   -
     * @param startY   -
     * @param destX    -
     * @param destY    -
     * @param position - depending on the direction vector the inside
     * @return
     */
    public PongLine(double startX, double startY, double destX, double destY, Side position)
    {
        // x1, x2 etc. are fields of Line2D.Double, from which we inherit them
        x1 = startX;
        y1 = startY;
        x2 = destX;
        y2 = destY;
        DirectionVector d = new DirectionVector(new double [] {x2-x1, y2-y1});
        d.normalize();
        dVector = d;
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
