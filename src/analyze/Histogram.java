package analyze;

public class Histogram
{
    int binSize, w, h, ignoreCount;
    int[] bin; // for both negative values (first portion) and positive values (second portion)

    public Histogram(int binSize, int[] bin, int w, int h, int ignoreCount)
    {
        this.binSize = binSize;
        this.bin = bin;
        this.w = w;
        this.h = h;
        this.ignoreCount = ignoreCount;
    }

    // returns the L1 norm of h1 and h2
    public static double l1Norm(Histogram h1, Histogram h2)
    {
        assert(h1.binSize == h2.binSize && h1.w == h2.w && h1.h == h2.h);
        int N = h1.bin.length;
        double sumDiff = 0.0;
        for (int n = 0; n < N; n++)
            sumDiff += Math.abs(h1.bin[n] - h2.bin[n]);
        double res = 1 - (sumDiff / ((h1.w * h1.h - h1.ignoreCount) + (h2.w * h2.h - h2.ignoreCount)));
//        System.out.println(res);
        return res;
    }

    public int[] getBin()
    {
        return bin;
    }
}
