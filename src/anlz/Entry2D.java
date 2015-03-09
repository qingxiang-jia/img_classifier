package anlz;

// represents an entry in a table, i is row, j is column
public class Entry2D implements Comparable<Entry2D>
{
    int j; // i is the original position (column) of this entry, which is also the pic number
    double content;

    public Entry2D (int j, double content)
    {
        this.content = content;
        this.j = j;
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

    public String toString()
    {
        return "" + content;
    }

    public int getJ()
    {
        return j;
    }
}
