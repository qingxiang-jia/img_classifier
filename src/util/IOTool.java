package util;

import java.io.*;

/**
 * Serialize the object to file.
 */
public class IOTool
{
    public static void toFile(String filename, Object obj)
    {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (oos != null) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object toObject(String path)
    {
        FileInputStream fos = null;
        ObjectInputStream oos = null;
        Object obj = null;
        try {
            fos = new FileInputStream(path);
            oos = new ObjectInputStream(fos);
            obj = oos.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (oos != null) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

}
