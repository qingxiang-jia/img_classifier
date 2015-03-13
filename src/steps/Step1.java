package steps;

import analyze.*;
import view.PageGen;

import java.util.Arrays;

public class Step1
{
    public static LikeUnlikes runProcedure()
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
        int maxUnlikeIndex = 0, maxLikeIndex = 0, maxUnlikeVal = 0, maxLikeVal = 0;
        Entry2D[][] l1NormTable = new Entry2D[40][40];
        for (int i = 0; i < 40; i++)
        {
            for (int j = 0; j < 40; j++)
                l1NormTable[i][j] = new Entry2D(j, l1Norm[i][j]);
            Arrays.sort(l1NormTable[i]);
            int[] unlike = new int[3], like = new int[3];
            int unlikeTotal = 0, likeTotal = 0;
            // fill unlike
            for (int u = 0; u < 3; u++)
            {
                unlike[u] = l1NormTable[i][u].getJ();
                unlikeTotal += unlike[u];
            }
            // fill like
            for (int l = l1NormTable[i].length - 1; l >= l1NormTable[i].length - 3; l--)
            {
                like[l1NormTable[i].length - 1 - l] = l1NormTable[i][l].getJ();
                likeTotal += like[l1NormTable[i].length - 1 - l];
            }
            if (maxUnlikeVal < unlikeTotal)
            {
                maxUnlikeVal = unlikeTotal;
                maxUnlikeIndex = i;
            }
            if (maxLikeVal < likeTotal)
            {
                maxLikeVal = likeTotal;
                maxLikeIndex = i;
            }
            basedOnColor[i] = new LikeUnlike(i, like, unlike); // store solution
        }
        // compute the most like/unlike in the pool of all 40 images (new approach)
        // for most like/unlike, just compute the sum of l1 norm, and find the max/min (old approach)
        LikeUnlikes likeUnlikesBasedOnColor = new LikeUnlikes(basedOnColor, maxUnlikeIndex, maxLikeIndex, Group.getGroupByL1Norm(l1Norm, 4, false), Group.getGroupByL1Norm(l1Norm, 4, true));
        return likeUnlikesBasedOnColor;
    }

    public static void main(String[] args)
    {
        LikeUnlikes step1Res = Step1.runProcedure();
//        for (LikeUnlike item : step1Res.getLikeUnlikes())
//            System.out.println(item);
        PageGen pageGen = new PageGen();
        pageGen.writePage("step1.html", "/Users/lee/Dropbox/VIC/assn2/images/jpg", step1Res);
    }
}