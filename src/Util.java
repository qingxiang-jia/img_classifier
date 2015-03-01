import java.io.*;
import java.util.StringTokenizer;

public class Util
{
    // This method is modified from the code written by Paul Rosin, Matt Gee, and Graham Daniell @ http://goo.gl/RYgo3G
    public static int[][][] readPPM(String filename)
    {
        String line;
        StringTokenizer st;
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader( new BufferedInputStream( new FileInputStream(filename))));
            DataInputStream in2 = new DataInputStream( new BufferedInputStream( new FileInputStream(filename)));
            // read PPM image header
            // skip comments
            line = in.readLine();
            in2.skip((line + "\n").getBytes().length);
            do
            {
                line = in.readLine();
                in2.skip((line + "\n").getBytes().length);
            } while (line.charAt(0) == '#');

            // the current line has dimensions
            st = new StringTokenizer(line);
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            // next line has pixel depth
            line = in.readLine();
            in2.skip((line + "\n").getBytes().length);
            st = new StringTokenizer(line);
            int depth = Integer.parseInt(st.nextToken());

            // create a 3D array to hold the picture 3xWxH
            int[][][] pixels = new int[3][width][height];

            // read pixels now
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    for (int i = 0; i < 3; i++)
                        pixels[i][x][y] = in2.readUnsignedByte();
            in.close();
            in2.close();
            return pixels;
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Error: image in " + filename + " too big");
        } catch (FileNotFoundException e)
        {
            System.out.println("Error: file " + filename + " not found");
        } catch (IOException e)
        {
            System.out.println("Error: end of stream encountered when reading " + filename);
        } return null;
    }

    // quick test
    public static void main(String[] args)
    {
        int[][][] image = Util.readPPM("/Users/lee/Dropbox/VIC/assn2/images/ppm/i01.ppm");
        System.out.printf("%d %d %d \n", image.length, image[0].length, image[0][0].length);
    }
}
