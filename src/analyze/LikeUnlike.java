package analyze;

import java.util.Arrays;

public class LikeUnlike
{
    int id; // could be picture sequence number
    int[] like;
    int[] unlike;

    public LikeUnlike(int id, int[] like, int[] unlike)
    {
        this.id = id;
        this.like = like;
        this.unlike = unlike;
    }

    public String toString()
    {
        return "id=" + id + " " + Arrays.toString(like) + " <-like unlike-> " + Arrays.toString(unlike);
    }

    public int getId()
    {
        return id;
    }

    public int getSeqNum() // the same as the sequence number in the pic filename
    {
        return id + 1;
    }

    public int[] getLike()
    {
        return like;
    }

    public int[] getUnlike()
    {
        return unlike;
    }

}
