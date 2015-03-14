package analyze;

import util.Arr;

public class GenHist
{
    // generate a 1D histogram by first converting gray-scale image to nonNegBin
    public static Histogram img2Hist(int[][] gray, int binSize) // binSize: 2^x
    {
        int[][] laplacian = Laplace.gray2Laplacian(gray);
        // generate histogram, [-255*8, 255*8]
        int[] negBin = new int[256*8 / binSize], nonNegBin = new int[256*8 / binSize];
        int W = gray.length, H = gray[0].length, ignoreCount = 0;
        for (int w = 0; w < W; w++)
            for (int h = 0; h < H; h++)
            {
                if (Math.abs(laplacian[w][h]) < 5)
                {
                    ignoreCount++;
                    continue;
                }
                if (laplacian[w][h] < 0)
                    negBin[-(laplacian[w][h] / binSize)]++; // index is positive
                else
                    nonNegBin[laplacian[w][h] / binSize]++;
            }
        return new Histogram(binSize, Arr.concatIntArrs(negBin, nonNegBin), W, H, ignoreCount);
    }
}
