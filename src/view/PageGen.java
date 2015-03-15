package view;

import analyze.LikeUnlike;
import analyze.LikeUnlikes;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PageGen
{
    TagGen tagGen;
    public void writePage(String pagename, String path, LikeUnlikes likeUnlikes)
    {
        tagGen = new TagGen();
        tagGen.setPath(path);
        StringBuilder sb = new StringBuilder();

        sb.append(tagGen.writeHeader());
        LikeUnlike[] data = likeUnlikes.getLikeUnlikes();
        // generates contents
        for (LikeUnlike likeUnlike : data)
        {
            // show subject
            sb.append(tagGen.writeDiv1("subject" + likeUnlike.getSeqNum()));
            sb.append(tagGen.writeP("pic " + likeUnlike.getSeqNum()));
            sb.append(tagGen.writeImg("i" + String.format("%02d", likeUnlike.getSeqNum()) + ".jpg"));
            sb.append(tagGen.writeDiv2());

            // show like
            sb.append(tagGen.writeDiv1("like" + likeUnlike.getSeqNum()));
            sb.append(tagGen.writeP("like"));
            for (int likeId : likeUnlike.getLike())
                sb.append(tagGen.writeImg("i" + String.format("%02d", likeId + 1) + ".jpg"));
            sb.append(tagGen.writeDiv2());

            // show unlike
            // show like
            sb.append(tagGen.writeDiv1("unlike" + likeUnlike.getSeqNum()));
            sb.append(tagGen.writeP("unlike"));
            for (int unlikeId : likeUnlike.getUnlike())
                sb.append(tagGen.writeImg("i" + String.format("%02d", unlikeId + 1) + ".jpg"));
            sb.append(tagGen.writeDiv2());
        }
        // display most like and most unlike (computed in two styles)
        // style 1 - approximation
        sb.append(tagGen.writeDiv1("most like (approximation)"));
        sb.append(tagGen.writeP("most like (approximation)"));
        sb.append(tagGen.writeP("pic " + (likeUnlikes.getMaxLikeIndex() + 1)));
        sb.append(tagGen.writeImg("i" + String.format("%02d", likeUnlikes.getMaxLikeIndex() + 1) + ".jpg"));
        for (int likeId : data[likeUnlikes.getMaxLikeIndex()].getLike())
            sb.append(tagGen.writeImg("i" + String.format("%02d", likeId + 1) + ".jpg"));
        sb.append(tagGen.writeDiv2());

        sb.append(tagGen.writeDiv1("most unlike (approximation)"));
        sb.append(tagGen.writeP("most unlike (approximation)"));
        sb.append(tagGen.writeP("pic " + (likeUnlikes.getMaxUnlikeIndex() + 1)));
        sb.append(tagGen.writeImg("i" + String.format("%02d", likeUnlikes.getMaxUnlikeIndex() + 1) + ".jpg"));
        for (int unlikeId : data[likeUnlikes.getMaxUnlikeIndex()].getUnlike())
            sb.append(tagGen.writeImg("i" + String.format("%02d", unlikeId + 1) + ".jpg"));
        sb.append(tagGen.writeDiv2());

        // style 2 - actual (max/min of sum of pairwise similarity)
        sb.append(tagGen.writeDiv1("most like"));
        sb.append(tagGen.writeP("most like"));
        for (int likeId : likeUnlikes.getLikeGroup())
            sb.append(tagGen.writeImg("i" + String.format("%02d", likeId + 1) + ".jpg"));
        sb.append(tagGen.writeDiv2());

        sb.append(tagGen.writeDiv1("most unlike"));
        sb.append(tagGen.writeP("most unlike"));
        for (int unlikeId : likeUnlikes.getUnlikeGroup())
            sb.append(tagGen.writeImg("i" + String.format("%02d", unlikeId + 1) + ".jpg"));
        sb.append(tagGen.writeDiv2());

        /** write clustering results **/
        if (likeUnlikes.getCompleteLink() != null)
        {
            sb.append(tagGen.writeDiv1("cluster - complete link"));
            sb.append(tagGen.writeP("cluster - complete link"));
            for (int i = 0; i < likeUnlikes.getCompleteLink().size(); i++) // a cluster
            {
                sb.append(tagGen.writeDiv1("cluster - complete link"+i));
                for (int j = 0; j < likeUnlikes.getCompleteLink().get(i).length; j++)
                    sb.append(tagGen.writeImg("i" + String.format("%02d", likeUnlikes.getCompleteLink().get(i)[j] + 1) + ".jpg"));
                sb.append(tagGen.writeDiv2());
            }
            sb.append(tagGen.writeDiv2());
        }

        sb.append(tagGen.writeTail());
        PrintWriter out = null;
        try {
            out = new PrintWriter(pagename);
            out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }
}
