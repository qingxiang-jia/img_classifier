package analyze;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of Rand Index
 */
public class Rand
{
    public static double compute(int[][] userClusters, List<int[]> machineClusters)
    {
        assert (userClusters.length == machineClusters.size());

        /** convert both clusters into 1D array where each row is: img_id | set_belong_to **/
        // userClusters[cluster_id][img_id_index] = img_id is in cluster cluster_id
        int[] cUser = new int[40];
        for (int clusterId = 0; clusterId < userClusters.length; clusterId++)
            for (int imgIdIndex = 0; imgIdIndex < userClusters[clusterId].length; imgIdIndex++)
                cUser[userClusters[clusterId][imgIdIndex] - 1] = clusterId; // user: starts at 1
        // machineClusters.get(cluster_id)[img_id_index] = img_id is in cluster cluster_id
        int[] cMach = new int[40];
        for (int clusterId = 0; clusterId < machineClusters.size(); clusterId++)
            for (int imgIdIndex = 0; imgIdIndex < machineClusters.get(clusterId).length; imgIdIndex++)
                cMach[machineClusters.get(clusterId)[imgIdIndex]] = clusterId;

        /** compute Rand index **/
        double denominator = NChooseK.nk(40, 2);
        double numerator = 0.0;
        int a = 0; // the number of pairs of elements in S that are in the same set in cUser and in the same set in cMach
        int b = 0; // the number of pairs of elements in S that are in different sets in cUser and in the different sets in cMach
        for (int i = 0; i < 40; i++) // pair <i, j>
            for (int j = i + 1; j < 40; j++)
            {
                if ((cUser[i] == cUser[j]) && (cMach[i] == cMach[j]))
                    a++;
                if ((cUser[i] != cUser[j]) && (cMach[i] != cMach[j])) // <-- NOT else!
                    b++;
            }
        numerator = a + b;
        System.out.println(Arrays.toString(cUser));
        System.out.println(Arrays.toString(cMach));
        System.out.println("a: " + a + " b: " + b + " " + numerator + " " + denominator + "\n");
        return numerator / denominator;
    }
}
