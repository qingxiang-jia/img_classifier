package analyze;

import java.util.HashSet;
import java.util.Set;

/**
 * Jaccard similarity coefficient implementation, computing the similarity of clusters.
 * J(A, B) = |A ∩ B| / |A ∪ B|
 */
public class Jaccard
{
    public static double compare(int[] c1, int[] c2)
    {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < c1.length; i++)
            set1.add(c1[i]);
        for (int i = 0; i < c2.length; i++)
            set2.add(c2[i]);
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        return intersection.size() / (double) union.size();
    }
}
