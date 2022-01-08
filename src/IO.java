import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

public class IO {
    public static String readTextFile(String filepath) {
        String fileContents = "";

        try {
            FileReader reader = new FileReader(filepath);

            int i;
            while ((i = reader.read()) != -1)
                fileContents += (char)i;
        }
        catch (FileNotFoundException e) {   // Error finding file
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {             // Error reading from file
            e.printStackTrace();
            return null;
        }

        return fileContents;
    }

    public static int writeTextFile(String filepath, String msg) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(filepath);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        writer.print(msg);
        writer.close();
        return 0;
    }
}
