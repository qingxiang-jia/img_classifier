package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This code is modified from KarmaAndCoding's algorithm: http://goo.gl/L9YY7m
 * Generates subsets of size n from set of size N.
 */
public class SizedSubset
{
    public static List<int[]> getSizedSubsets(int n, int N)
    {
        int[] A = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = i;

        int len = A.length;
        long result = 0;

        StringBuffer sb = new StringBuffer();
        int diff = len - n;

        for (int i = 1; i <= diff; i++)
            sb.append('0');
        for (int i = 1; i <= n; i++)
            sb.append('1');

        String binStr = sb.toString();
        String finalBinStr = sb.reverse().toString();
        long last = Long.parseLong(finalBinStr, 2);
        List<int[]> sizedSubsets = new ArrayList<>();
        loadSubSet(A, binStr, sizedSubsets);
        result = Long.parseLong(binStr, 2);
        while (result < last)
        {
            binStr = findNext(result, len);
            loadSubSet(A, binStr, sizedSubsets);
            result = Long.parseLong(binStr, 2);
        }
        return sizedSubsets;
    }

    public static void loadSubSet(int[] A, String binaryString, List<int[]> sizedSubsets)
    {
        int len = binaryString.length(), k = 0;
        int[] subset = new int[4];
        for (int i = 0; i < len; i++)
            if (binaryString.charAt(i) == '1')
                subset[k++] = A[i];
        sizedSubsets.add(subset);
    }

    //finds the next smallest number with same number of k bits set.
    public static String findNext(long n, int k)
    {
        StringBuffer sbuff = new StringBuffer();

        long l = Long.toBinaryString(n).length();
        if (l < k)
            for (int i = 1; i <= (k - l); i++)
                sbuff.append('0');
        char[] ch = (sbuff.toString() + Long.toBinaryString(n)).toCharArray();
        int len = ch.length;
        boolean switched = false;
        int index = 0;
        for (int i = len - 1; i >= 0; i--)
            if (ch[i] == '1')
            {
                for (int j = i - 1; j >= 0; j--)
                    if (ch[j] == '0')
                    {
                        ch[j] = '1';
                        ch[j + 1] = '0';
                        index = j + 1;
                        //System.out.println("J:" + j + " J+1:" + (j+1));
                        switched = true;
                        break;
                    }
                if (switched)
                    break;
            }
        int i = index + 1;
        int j = len - 1;

        while (i < j)
        {
            if (ch[i] == '1' && ch[j] == '0')
            {
                ch[i] = '0';
                ch[j] = '1';
                ++i;
                --j;
            }
            ++i;
        }
        StringBuffer sb = new StringBuffer();
        for (i = 0; i < len; i++)
            sb.append(ch[i]);

        return sb.toString();
    }

    // quick test
    public static void main(String[] args)
    {
        List<int[]> sizedSubsets = SizedSubset.getSizedSubsets(4, 40);
        for (int[] subset : sizedSubsets)
            System.out.println(Arrays.toString(subset));
    }

}
