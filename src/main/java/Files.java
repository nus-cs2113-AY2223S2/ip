import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Files {
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void writeToFile(String filePath, ArrayList<Task> a) throws IOException {
        FileWriter reset = new FileWriter(filePath);
        FileWriter fw = new FileWriter(filePath, true);
        reset.write("");
        int i = 0;
        for (Task x : a){
            i++;
            fw.write(i + ". " + x.toString() + '\n');
        }
        fw.close();
    }
}
