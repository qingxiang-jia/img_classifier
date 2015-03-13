package analyze;

import util.Arr;
import util.SizedSubset;

import java.util.Arrays;
import java.util.List;

/**
 * Forms a group of N by either similarity or dis-similarity.
 */
public class Group
{
    // n: size of the group
    public static int[] getGroupByL1Norm(double[][] l1Norm, int n, boolean findMax)
    {
        List<int[]> sizedSubsets = SizedSubset.getSizedSubsets(n, l1Norm.length);
        // for each subset, compute the sum of pairwise similarity of all elements in the subset
        double extrem;
        if (findMax) extrem = -Double.MAX_VALUE;
        else extrem = Double.MAX_VALUE;
        int[] extremSet = null;
        for (int[] subset : sizedSubsets)
        {
            double local = pairwiseSimilaritySum(l1Norm, subset);
            if (findMax && extrem < local)
            {
                extrem = local;
                extremSet = subset;
            }
            if (!findMax && extrem > local)
            {
                extrem = local;
                extremSet = subset;
            }
        }
        return extremSet;
    }

    private static double pairwiseSimilaritySum(double[][] l1Norm, int[] subset)
    {
        double res = 0;
        for (int i = 0; i < subset.length; i++)
            for (int j = i; j < subset.length; j++)
                res += l1Norm[subset[i]][subset[j]];
        return res;
    }
}
