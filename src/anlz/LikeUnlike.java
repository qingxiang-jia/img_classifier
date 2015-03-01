package anlz;

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
}
