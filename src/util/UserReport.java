package util;

import java.util.Arrays;

public class UserReport
{
    int[][] step1, step2, clusters;

    public UserReport(int[][] step1, int[][] step2, int[][] clusters)
    {
        this.step1 = step1;
        this.step2 = step2;
        this.clusters = clusters;
    }

    public void out()
    {
        System.out.println("step1");
        for (int[] arr : step1)
            System.out.println(Arrays.toString(arr));

        System.out.println("step2");
        for (int[] arr : step2)
            System.out.println(Arrays.toString(arr));

        System.out.println("clusters");
        for (int[] arr : clusters)
            System.out.println(Arrays.toString(arr));
    }

    public int[][] getStep1()
    {
        return step1;
    }

    public int[][] getStep2()
    {
        return step2;
    }

    public int[][] getClusters()
    {
        return clusters;
    }
}
