package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class UserReportReader
{
    private static final int STEP1 = 1, STEP2 = 2, STEP3 = 3;

    public static UserReport genUserReport(Path path)
    {
        int state = 0, step1[][] = new int[40][3], step2[][] = new int[40][3], step3[][] = new int[7][];
        /** read in file **/
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lines == null)
            return null;
        /** processing lines **/
        int seq = 0;
        for (String line : lines)
        {
            if (line.equalsIgnoreCase("step1"))
            {
                state = STEP1;
                seq = 0;
            }
            else if (line.equalsIgnoreCase("step2"))
            {
                state = STEP2;
                seq = 0;
            }
            else if (line.equalsIgnoreCase("step3"))
            {
                state = STEP3;
                seq = 0;
            }
            else if (line.startsWith("@"))
                continue;
            else
            {
                String[] tokens = line.split("\\s+");
                assert(tokens.length == 3 || tokens.length == 7);
                if (state == STEP1)
                    for (int i = 0; i < 3; i++)
                        step1[seq][i] = Integer.parseInt(tokens[i]);
                else if (state == STEP2)
                    for (int i = 0; i < 3; i++)
                        step2[seq][i] = Integer.parseInt(tokens[i]);
                else
                {
                    step3[seq] = new int[tokens.length];
                    for (int i = 0; i < tokens.length; i++)
                        step3[seq][i] = step3[seq][i];
                }
                seq++;
            }
        }
        return new UserReport(step1, step2, step3);
    }
}
