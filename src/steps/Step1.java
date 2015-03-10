package steps;

import analyze.Entry2D;
import analyze.GenHist3D;
import analyze.Histogram3D;
import analyze.LikeUnlike;
import view.PageGen;

import java.util.Arrays;

public class Step1
{
    public static LikeUnlike[] runProcedure()
    {
        LikeUnlike[] basedOnColor = new LikeUnlike[40];
        // read in an array of images and build histograms base on them
        int[][][][] images = new int[40][][][];
        Histogram3D[] histograms = new Histogram3D[40];
        for (int i = 0; i < 40; i++)
        {
            images[i] = util.Img.readPPM(String.format("%s%02d%s", "/Users/lee/Dropbox/VIC/assn2/images/ppm/i", i + 1, ".ppm")); // in case u need the image later
            histograms[i] = GenHist3D.img2Hist3D(images[i], 32);
        }
        // compute L1 norm
        double[][] l1Norm = new double[40][40];
        for (int i = 0; i < 40; i++)
            for (int j = 0; j < 40; j++)
            {
                if (i == j)
                    l1Norm[i][j] = 1; // no need to compute, pic1 is the same as pic1, similarity 1
                else if (i > j)
                    l1Norm[i][j] = l1Norm[j][i]; // must have done comparison already
                else
                    l1Norm[i][j] = Histogram3D.l1Norm(histograms[i], histograms[j]);
            }
        // convert results into Entry2D and find the most like and unlike
        Entry2D[][] l1NormTable = new Entry2D[40][40];
        for (int i = 0; i < 40; i++)
        {
            for (int j = 0; j < 40; j++)
                l1NormTable[i][j] = new Entry2D(j, l1Norm[i][j]);
            Arrays.sort(l1NormTable[i]);
            int[] unlike = new int[4], like = new int[4];
            // fill unlike
            for (int u = 0; u < 4; u++)
                unlike[u] = l1NormTable[i][u].getJ();
            // fill like
            for (int l = l1NormTable[i].length - 1; l >= l1NormTable[i].length - 4; l--)
                like[l1NormTable[i].length - 1 - l] = l1NormTable[i][l].getJ();
            basedOnColor[i] = new LikeUnlike(i, like, unlike); // store solution
        }
        return basedOnColor;
    }

    public static void main(String[] args)
    {
        LikeUnlike[] step1Res = Step1.runProcedure();
        for (LikeUnlike item : step1Res)
            System.out.println(item);
        PageGen pageGen = new PageGen();
        pageGen.writePage("out.html", "/Users/lee/Dropbox/VIC/assn2/images/jpg", step1Res);
    }
}
