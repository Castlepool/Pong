package de.hft.swp1.pong;

public class DirectionVector

{
    /** Attributes */
    /**
     * vector-coordinates (2D => 2 values)
     */
    private double[] values;

    /**
     * Operation DirectionVector
     *
     * @param dimensions -
     * @return
     */
    public DirectionVector(int dimensions)
    {
        values = new double [dimensions];
        for(int i = 0; i < dimensions; i++){
            values[i] = 1;
        }
    }

    /**
     * Operation DirectionVector
     *
     * @param values -
     * @return
     */
    public DirectionVector(double[] values)
    {
        this.values = values;
    }

    /**
     * Operation scalar
     *
     * @param vc -
     * @return double
     */
    public double scalar(DirectionVector vc)
    {
        double scalar = 0;
        for(int i = 0; i < values.length; i++){
            scalar += values[i] * vc.values[i];
        }
        return scalar;
    }

    /**
     * Operation length
     *
     * @return double
     */
    public double length()
    {
        double sum = 0;
        for(int i = 0; i < values.length; i++){
            sum += Math.pow(values[i], 2.0);
        }
        return Math.sqrt(sum);
    }

    /**
     * Operation getValue
     *
     * @param dimension -
     * @return double
     */
    public double getValue(int dimension)
    {
        return values[dimension-1];
    }

    /**
     * Operation setVector
     *
     * @param values    -
     * @param normalize -
     */
    public void setVector(double[] values, boolean normalize)
    {
        this.values = values;
        if(normalize) normalize();
    }

    /**
     * Operation normalize
     *
     */
    public void normalize()
    {
        double length = length();
        for(int i = 0; i < values.length; i++){
            values[i] = values[i] / length;
        }
    }
}
