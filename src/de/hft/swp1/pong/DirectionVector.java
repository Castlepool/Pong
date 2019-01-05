package de.hft.swp1.pong;

public class DirectionVector

{
    /** Attributes */
    /**
     * 
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
        this.values = new double [values.length];
        for(int i = 0; i < values.length; i++){
            this.values[i] = values[i];
        }
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
    
    /**
     * Operation getNormal
     *
     */
    public static DirectionVector getNormal(DirectionVector d)
    {
        double slopePrevious = d.getValue(2)/d.getValue(1);
        double slopeNew = -1/(slopePrevious);
        DirectionVector normal = new DirectionVector(new double [] {1,slopeNew});
        return normal;
    }
}
