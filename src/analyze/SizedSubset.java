package analyze;

import java.util.*;

import com.google.common.collect.Sets;

/**
 * Generates subsets of size n from set of size N.
 */
public class SizedSubset
{
    public static List<int[]> getSizedSubsets(int n, int N)
    {
        Set<Integer> imgSet = new HashSet<>();
        for (int i = 0; i < N; i++) // populate set
            imgSet.add(i);
        Set<Set<Integer>> pwrSet = Sets.powerSet(imgSet);
        List<int[]> sizedSet = new ArrayList<>();
        for (Set<Integer> subset : pwrSet)
        {
            if (subset.size() == n)
            {
                int[] subsetArr = new int[n];
                int i = 0;
                for (Integer element : subset)
                    subsetArr[i++] = element;
                sizedSet.add(subsetArr);
            }
        }
        return sizedSet;
    }

    // quick test
    public static void main(String[] args)
    {
        List<int[]> res = SizedSubset.getSizedSubsets(3, 5);
        for (int[] arr : res)
            System.out.println(Arrays.toString(arr));
    }


}
