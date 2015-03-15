package analyze.cluster;

/**
 * Contains intermediate result during clustering.
 */
public class Intermediate
{
    int[] cluster1, cluster2;
    double val;
    public Intermediate(int[] cluster1, int[] cluster2, double val)
    {
        this.cluster1 = cluster1;
        this.cluster2 = cluster2;
        this.val = val;
    }
}
