package anlz;

// basically step 1
public class ColorAnalyzer
{
    public static Histogram3D analyze(int[][][] img, int binSize)
    {
        int[][][] rgb = new int[256 / binSize][256 / binSize][256 / binSize];
        for (int w = 0; w < img[0].length; w++)
            for (int h = 0; h < img[0][0].length; h++)
                rgb[img[0][w][h] / binSize][img[1][w][h] / binSize][img[2][w][h] / binSize]++;
        return new Histogram3D(binSize, rgb);
    }

    // quick test
    public static void main(String[] args)
    {
        Histogram3D histogram = analyze(util.Img.readPPM("/Users/lee/Dropbox/VIC/assn2/images/ppm/i07.ppm"), 32);
        System.out.println(histogram.binSize);
        histogram.out();
    }
}
