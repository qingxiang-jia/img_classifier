package analyze;

public class LikeUnlikes
{
    int maxUnlikeIndex, maxLikeIndex;
    LikeUnlike[] likeUnlikes;

    public LikeUnlikes(LikeUnlike[] likeUnlikes, int maxUnlikeIndex, int maxLikeIndex)
    {

        this.likeUnlikes = likeUnlikes;
        this.maxUnlikeIndex = maxUnlikeIndex;
        this.maxLikeIndex = maxLikeIndex;
    }

    public int getMaxUnlikeIndex()
    {
        return maxUnlikeIndex;
    }

    public void setMaxUnlikeIndex(int maxUnlikeIndex)
    {
        this.maxUnlikeIndex = maxUnlikeIndex;
    }

    public int getMaxLikeIndex()
    {
        return maxLikeIndex;
    }

    public void setMaxLikeIndex(int maxLikeIndex)
    {
        this.maxLikeIndex = maxLikeIndex;
    }

    public LikeUnlike[] getLikeUnlikes()
    {
        return likeUnlikes;
    }

    public void setLikeUnlikes(LikeUnlike[] likeUnlikes)
    {
        this.likeUnlikes = likeUnlikes;
    }
}
