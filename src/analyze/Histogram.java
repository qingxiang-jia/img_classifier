package analyze;

public class Histogram
{
    int binSize, w, h;
    int[][] laplacian;

    public Histogram(int binSize, int[][] laplacian, int w, int h)
    {
        this.binSize = binSize;
        this.laplacian = laplacian;
        this.w = w;
        this.h = h;
    }

    // returns the L1 norm of h1 and h2
    public static double l1Norm(Histogram h1, Histogram h2)
    {

    }
}
