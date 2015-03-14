package steps;

import analyze.*;
import view.PageGen;

import java.util.Arrays;

/**
 * Sort of a combined version of Step1 and 2. The only difference is that the new "L1 Norm" will depend on
 * both versions of L1 norms.
 */
public class Step3
{
    public static LikeUnlikes runProcedure(double r)
    {
        assert(0 <= r && r <= 1);
        LikeUnlike[] basedOnColorTexture = new LikeUnlike[40];

        // read in an array of images and build histograms base on them
        int[][][][] images = new int[40][][][];
        Histogram3D[] histograms3D = new Histogram3D[40];
        Histogram[] histograms2D = new Histogram[40];
        for (int i = 0; i < 40; i++)
        {
            images[i] = util.Img.readPPM(String.format("%s%02d%s", "/Users/lee/Dropbox/VIC/assn2/images/ppm/i", i + 1, ".ppm")); // in case u need the image later
            // step1 business
            histograms3D[i] = GenHist3D.img2Hist3D(images[i], 32);
            // step2 business
            int[][] gray = util.Img.RGB2Gray(images[i]);
            histograms2D[i] = GenHist.img2Hist(gray, 32);
        }

        /** compute L1 norm based on color **/
        double[][] l1NormColor = new double[40][40];
        for (int i = 0; i < 40; i++)
            for (int j = 0; j < 40; j++)
            {
                if (i == j)
                    l1NormColor[i][j] = 1; // no need to compute, pic1 is the same as pic1, similarity 1
                else if (i > j)
                    l1NormColor[i][j] = l1NormColor[j][i]; // must have done comparison already
                else
                    l1NormColor[i][j] = Histogram3D.l1Norm(histograms3D[i], histograms3D[j]);
            }

        /** compute L1 norm based on texture **/
        double[][] l1NormTexture = new double[40][40];
        for (int i = 0; i < 40; i++)
            for (int j = 0; j < 40; j++)
            {
                if (i == j)
                    l1NormTexture[i][j] = 1; // no need to compute, pic1 is the same as pic1, similarity 1
                else if (i > j)
                    l1NormTexture[i][j] = l1NormTexture[j][i]; // must have done comparison already
                else
                    l1NormTexture[i][j] = Histogram.l1Norm(histograms2D[i], histograms2D[j]);
            }

        /** combine both norms linearly **/
        double[][] l1Norm = new double[40][40];
        for (int i = 0; i < 40; i++)
            for (int j = 0; j < 40; j++)
                l1Norm[i][j] = r * l1NormColor[i][j] + (1.0 - r) * l1NormTexture[i][j];

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
            basedOnColorTexture[i] = new LikeUnlike(i, like, unlike); // store solution
        }
        // compute the most like/unlike in the pool of all 40 images (new approach)
        // for most like/unlike, just compute the sum of l1 norm, and find the max/min (old approach)
        LikeUnlikes likeUnlikesColorTexture = new LikeUnlikes(basedOnColorTexture, maxUnlikeIndex, maxLikeIndex, Group.getGroupByL1Norm(l1NormColor, 4, false), Group.getGroupByL1Norm(l1NormColor, 4, true));
        return likeUnlikesColorTexture;
    }

    public static void main(String[] args)
    {
        for (int i = 1; i < 10; i++)
        {
            LikeUnlikes step3Res = Step3.runProcedure(0.1*i);
            PageGen pageGen = new PageGen();
            pageGen.writePage("step3" + "r_0." + i + ".html", "/Users/lee/Dropbox/VIC/assn2/images/jpg", step3Res);
        }
    }
}
