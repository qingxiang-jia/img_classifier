package util;

/**
 * Convert similarity (L1 norm) to distance.
 * D = 1 - S
 */
public class S2D
{
    public static double[][] convert(double[][] similarity)
    {
        double[][] distance = new double[similarity.length][similarity[0].length];
        for (int i = 0; i < similarity.length; i++)
            for (int j = 0; j < similarity[0].length; j++)
                distance[i][j] = 1.0 - similarity[i][j];
        return distance;
    }
}
