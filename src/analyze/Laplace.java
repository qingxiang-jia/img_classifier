package analyze;

public class Laplace
{
    public static int[][] gray2Laplacian(int[][] gray)
    {
        int W = gray.length, H = gray[0].length;
        int[][] laplacian = new int[W][H];
        for (int w = 0; w < W; w++)
            for (int h = 0; h < H; h++)
            {
                int neighborSum = 0, count = 0;
                /** sum over neighbors */
                if (0 <= w-1 && 0 <= h-1 && count++ >= 0)
                    neighborSum += gray[w-1][h-1];
                if (0 <= h-1 && count++ >= 0)
                    neighborSum += gray[w][h-1];
                if (w+1 < W && 0 <= h-1 && count++ >= 0)
                    neighborSum += gray[w+1][h-1];
                if (w+1 < W && count++ >= 0)
                    neighborSum += gray[w+1][h];
                if (w+1 < W && h+1 < H && count++ >= 0)
                    neighborSum += gray[w+1][h+1];
                if (h+1 < H && count++ >= 0)
                    neighborSum += gray[w][h+1];
                if (0 <= w-1 && h+1 < H && count++ >= 0)
                    neighborSum += gray[w-1][h+1];
                if (0 <= w-1 && count++ >= 0)
                    neighborSum += gray[w-1][h];
                laplacian[w][h] = gray[w][h] * count - neighborSum;
            }
        return laplacian;
    }
}
