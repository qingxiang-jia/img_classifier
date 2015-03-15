package analyze;

import java.util.List;

public class LikeUnlikes
{
    int maxUnlikeIndex, maxLikeIndex, unlikeGroup[], likeGroup[];
    LikeUnlike[] likeUnlikes;
    List<int[]> completeLink, singleLink;

    public LikeUnlikes(LikeUnlike[] likeUnlikes, int maxUnlikeIndex, int maxLikeIndex, int[] unlikeGroup, int[] likeGroup)
    {
        this.likeUnlikes = likeUnlikes;
        this.maxUnlikeIndex = maxUnlikeIndex;
        this.maxLikeIndex = maxLikeIndex;
        this.unlikeGroup = unlikeGroup;
        this.likeGroup = likeGroup;
        this.completeLink = null;
        this.singleLink = null;
    }

    public LikeUnlikes(LikeUnlike[] likeUnlikes, int maxUnlikeIndex, int maxLikeIndex, int[] unlikeGroup, int[] likeGroup, List<int[]> completeLink, List<int[]> singleLink)
    {
        this.likeUnlikes = likeUnlikes;
        this.maxUnlikeIndex = maxUnlikeIndex;
        this.maxLikeIndex = maxLikeIndex;
        this.unlikeGroup = unlikeGroup;
        this.likeGroup = likeGroup;
        this.completeLink = null;
        this.singleLink = null;
        this.completeLink = completeLink;
        this.singleLink = singleLink;
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

    public List<int[]> getCompleteLink()
    {
        return completeLink;
    }

    public List<int[]> getSingleLink()
    {
        return singleLink;
    }
}
