package analyze;

import java.util.ArrayList;
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
        List<int[]> clusters = new ArrayList<>();
        /** populating clusters **/
        for (int i = 0; i < distance.length; i++)
        {
            int[] cluster = new int[]{i};
            clusters.add(cluster);
        }
        /** clustering clusters **/

        return null;
    }


}
