package steps;

import util.UserReport;
import util.UserReportReader;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Step4
{
    public static void runProcedure(String[] filePaths)
    {
        for (int p = 0; p < filePaths.length; p++)
        {
            Path path = Paths.get(filePaths[p]);
            UserReport report = UserReportReader.genUserReport(path);

        }
    }

    // return per user score
    private static double[] eval(UserReport report)
    {
        return null;
    }

    public static void main(String[] args)
    {
//        Step4.runProcedure();
    }
}
