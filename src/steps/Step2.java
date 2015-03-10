package steps;

import analyze.LikeUnlike;

public class Step2
{
    public static LikeUnlike[] runProcedure()
    {
        LikeUnlike[] basedOnTexture = new LikeUnlike[40];
        // read in an array of images and build histograms base on them
        int[][][][] images = new int[40][][][];
        for (int i = 0; i < 40; i++)
        {
            images[i] = util.Img.readPPM(String.format("%s%02d%s", "/Users/lee/Dropbox/VIC/assn2/images/ppm/i", i + 1, ".ppm")); // in case u need the image later
            // convert to gray scale
            int[][] gray = util.Img.RGB2Gray(images[i]);

        }
        return basedOnTexture;
    }
}
