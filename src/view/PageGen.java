package view;

import analyze.LikeUnlike;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PageGen
{
    TagGen tagGen;
    public void writePage(String pagename, String path, LikeUnlike[] data)
    {
        tagGen = new TagGen();
        tagGen.setPath(path);
        StringBuilder sb = new StringBuilder();

        sb.append(tagGen.writeHeader());
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
