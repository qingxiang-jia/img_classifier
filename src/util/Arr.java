package util;

public class Arr
{
    public static int[] concatIntArrs(int[] a, int[] b)
    {
        int[] c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static boolean contains(int t, int[] arr)
    {
        int len = arr.length;
        for (int i = 0; i < len; i++)
            if (arr[i] == t)
                return true;
        return false;
    }
}
