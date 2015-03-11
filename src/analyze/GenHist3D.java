package analyze;

// basically step 1
public class GenHist3D
{
    public static Histogram3D img2Hist3D(int[][][] img, int binSize)
    {
        int[][][] rgb = new int[256 / binSize][256 / binSize][256 / binSize];
        for (int w = 0; w < img[0].length; w++)
            for (int h = 0; h < img[0][0].length; h++) {
                rgb[img[0][w][h] / binSize][img[1][w][h] / binSize][img[2][w][h] / binSize]++;
                if (img[0][w][h] == 0 && img[1][w][h] == 0 && img[2][w][h] == 0) System.out.println("black");
            }
        return new Histogram3D(binSize, img, img[0].length, img[0][0].length);
    }

    // quick test
    public static void main(String[] args)
    {
        Histogram3D histogram = img2Hist3D(util.Img.readPPM("/Users/lee/Dropbox/VIC/assn2/images/ppm/i07.ppm"), 32);
        System.out.println(histogram.binSize);
        histogram.out();
    }
}
