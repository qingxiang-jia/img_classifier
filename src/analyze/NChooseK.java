package analyze;

/**
 * Code taken from http://goo.gl/7PLp8s, written by mob (http://stackoverflow.com/users/168657/mob)
 */
public class NChooseK
{
    public static double nk(int n, int k)
    {
        if (k < 0 || k > n) return 0;
        if (k > n / 2)
        {
            // choose(n,k) == choose(n,n-k),
            // so this could save a little effort
            k = n - k;
        }

        double denominator = 1.0, numerator = 1.0;
        for (int i = 1; i <= k; i++)
        {
            denominator *= i;
            numerator *= (n + 1 - i);
        }
        return numerator / denominator;
    }
}
