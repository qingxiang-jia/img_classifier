package analyze;

public class Histogram
{
    int binSize, w, h;
    int[][][] rgb;

    public Histogram(int binSize, int[][][] rgb, int w, int h)
    {
        this.binSize = binSize;
        this.rgb = rgb;
        this.w = w;
        this.h = h;
    }

    // returns the L1 norm of h1 and h2
    public static double l1Norm(Histogram h1, Histogram h2)
    {
    }
}
