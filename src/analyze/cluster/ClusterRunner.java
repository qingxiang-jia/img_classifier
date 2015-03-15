package analyze.cluster;

import util.Arr;

import java.util.LinkedList;
import java.util.List;

/**
 * Given the L1 norm (combined both color and texture values) and run the cluster in either in complete link
 * mode or single link mode, after termination condition is met, return clusters.
 * A cluster is represented by an int[].
 */
public class ClusterRunner
{
    public static List<int[]> completeLink(double[][] distance, int finalSize)
    {
        assert (distance.length == distance[0].length);
        List<int[]> clusters = new LinkedList<>();
        /** populating clusters **/
        for (int i = 0; i < distance.length; i++)
        {
            int[] cluster = new int[]{i};
            clusters.add(cluster);
        }
        /** clustering clusters **/
        while (clusters.size() != finalSize)
        {
            Intermediate mergeCandidate = null;
            for (int i = 0; i < clusters.size(); i++) // each cluster compares with the rest, each saves the minimum,
                                                      // them take the global minimum out of the local minimum
                                                      // merge the corresponding two clusters
            {
                for (int j = i + 1; j < clusters.size(); j++)
                {
                    double resultIJ = compareClusterCL(distance, clusters.get(i), clusters.get(j));
                    if (mergeCandidate == null || resultIJ < mergeCandidate.val)
                        mergeCandidate = new Intermediate(clusters.get(i), clusters.get(j), resultIJ);
                }
            }
            /** merge two clusters **/
            mergeClusters(clusters, mergeCandidate);
        }
        return clusters;
    }

    private static double compareClusterCL(double[][] distance, int[] cluster1, int[] cluster2)
    {
        double maxDist = -Double.MAX_VALUE;
        for (int imgFrom1 : cluster1)
            for (int imgFrom2 : cluster2)
                if (distance[imgFrom1][imgFrom2] > maxDist)
                    maxDist = distance[imgFrom1][imgFrom2];
        return maxDist;
    }

    // merge the two clusters in candidate, and update clusters
    private static void mergeClusters(List<int[]> clusters, Intermediate candidate)
    {
        int[] cluster1 = candidate.cluster1, cluster2 = candidate.cluster2; // I know cluster1 and 2 contain mutually unique elements
        int[] mergedCluster = Arr.concatIntArrs(cluster1, cluster2);
        clusters.remove(cluster1);
        clusters.remove(cluster2);
        clusters.add(mergedCluster);
    }


}
