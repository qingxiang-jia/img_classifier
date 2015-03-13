package analyze;

public class LikeUnlikes
{
    int maxUnlikeIndex, maxLikeIndex, unlikeGroup[], likeGroup[];
    LikeUnlike[] likeUnlikes;

    public LikeUnlikes(LikeUnlike[] likeUnlikes, int maxUnlikeIndex, int maxLikeIndex, int[] unlikeGroup, int[] likeGroup)
    {
        this.likeUnlikes = likeUnlikes;
        this.maxUnlikeIndex = maxUnlikeIndex;
        this.maxLikeIndex = maxLikeIndex;
        this.unlikeGroup = unlikeGroup;
        this.likeGroup = likeGroup;
    }

    public int getMaxUnlikeIndex()
    {
        return maxUnlikeIndex;
    }

    public int getMaxLikeIndex()
    {
        return maxLikeIndex;
    }

    public LikeUnlike[] getLikeUnlikes()
    {
        return likeUnlikes;
    }

    public int[] getUnlikeGroup()
    {
        return unlikeGroup;
    }

    public int[] getLikeGroup()
    {
        return likeGroup;
    }
}
