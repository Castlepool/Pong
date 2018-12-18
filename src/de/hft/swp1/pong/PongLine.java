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
        throw new UnsupportedOperationException("not yet implemented");
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
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Operation movesToMe.<br />
     * 
     * checks with the last to stored distances, if the puck is moving to the line
     *
     * @return boolean
     */
    public boolean movesToMe()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * Operation getDirectionVector
     *
     * @return DirectionVector of this line.
     */
    public DirectionVector getDirectionVector()
    {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
