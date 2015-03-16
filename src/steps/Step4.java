package steps;

import analyze.LikeUnlike;
import analyze.LikeUnlikes;
import util.Arr;
import util.IOTool;
import util.UserReport;
import util.UserReportReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Step4
{
    LikeUnlikes step1Res, step2Res;
    List<int[]> clustersCompleteLink, clustersSingleLink;

    @SuppressWarnings("unchecked")
    private void init(String[] machineReports) // MUST in this order: step1Res.ser, step2Res.ser, clusterCompleteLink.ser, clustersSingleLink.ser
    {
        step1Res = (LikeUnlikes) IOTool.toObject(machineReports[0]);
        step2Res = (LikeUnlikes) IOTool.toObject(machineReports[1]);
        clustersCompleteLink = (List<int[]>) IOTool.toObject(machineReports[2]);
        clustersSingleLink = (List<int[]>) IOTool.toObject(machineReports[3]);
    }

    public void runProcedure(String[] filePaths, String[] machineReports)
    {
        init(machineReports);
        double scores[][] = new double[filePaths.length][];
        for (int p = 0; p < filePaths.length; p++)
        {
            Path path = Paths.get(filePaths[p]);
            UserReport report = UserReportReader.genUserReport(path);
            scores[p] = eval(report);
            System.out.println(Arrays.toString(scores[p]));
        }
    }

    // return per user score
    private double[] eval(UserReport report)
    {
        double[] score = new double[4];

        /** eval step1 **/
        int step1Score = 0;
        LikeUnlike[] step1LikeUnlike = step1Res.getLikeUnlikes();
        int[][] step1User = report.getStep1();
        assert(step1LikeUnlike.length == step1User.length);

        for (int i = 0; i < step1User.length; i++)
        {
            if (Arr.contains(step1User[i][0], step1LikeUnlike[i].getLike()))
                step1Score++;
            if (Arr.contains(step1User[i][1], step1LikeUnlike[i].getUnlike()))
                step1Score++;
        }
        score[0] = step1Score;

        /** eval step2 **/
        int step2Score = 0;
        LikeUnlike[] step2LikeUnlike = step2Res.getLikeUnlikes();
        int[][] step2User = report.getStep2();
        assert(step2LikeUnlike.length == step2User.length);

        for (int i = 0; i < step2User.length; i++)
        {
            if (Arr.contains(step2User[i][0], step2LikeUnlike[i].getLike()))
                step2Score++;
            if (Arr.contains(step2User[i][1], step2LikeUnlike[i].getUnlike()))
                step2Score++;
        }
        score[1] = step2Score;

        /** eval step3 **/
        return score;
    }


    public static void main(String[] args)
    {
        Step4 step4 = new Step4();
        step4.runProcedure(new String[]{"Wen_Zhang.txt", "Yiqin_Chen.txt", "Peiqian_Li.txt"}, new String[]{"step1Res.ser", "step2Res.ser", "clusterCompleteLink.ser", "clustersSingleLink.ser"});
    }
}
