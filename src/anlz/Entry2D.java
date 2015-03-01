package anlz;

// represents an entry in a table, i is row, j is column
public class Entry2D implements Comparable<Entry2D>
{
    int i, j;
    double content;

    public Entry2D (int i, int j, double content)
    {
        this.content = content;
    }

    public int compareTo(Entry2D otherEntry) // ascending
    {
        if (this.content > otherEntry.content)
            return 1;
        else if (this.content < otherEntry.content)
            return -1;
        else
            return 0;
    }

    public int getJ()
    {
        return j;
    }
}
