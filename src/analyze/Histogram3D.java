package analyze;

import java.util.Arrays;

public class Histogram3D
{
    int binSize, w, h;
    int[][][] rgb;

    public Histogram3D(int binSize, int[][][] rgb, int w, int h)
    {
        this.binSize = binSize;
        this.rgb = rgb;
        this.w = w;
        this.h = h;
    }

    public void out()
    {
        for (int r = 0; r < rgb.length; r++)
            for (int g = 0; g < rgb[0].length; g++)
                System.out.println(Arrays.toString(rgb[r][g]));
    }

    // returns the L1 norm of h1 and h2
    public static double l1Norm(Histogram3D h1, Histogram3D h2)
    {
        assert(h1.binSize == h2.binSize && h1.w == h2.w && h1.h == h2.h);
        double diff = 0;
        for (int r = 0; r < h1.rgb.length; r++) // for each nested iteration, raising initial value to ignore dark color
            for (int g = 0; g < h1.rgb[0].length; g++)
                for (int b = 0; b < h1.rgb[0][0].length; b++)
                    diff += Math.abs(h1.rgb[r][g][b] - h2.rgb[r][g][b]);
        double res = 1 - (diff / (2 * h1.w * h1.h)); // suggested by Prof. K
        return res;
    }
}
