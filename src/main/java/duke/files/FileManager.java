package duke.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.tasks.Task;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileManager {

    private static boolean isFile = false;

    public static boolean checkFile() {
        File f = new File("duke/files/data.txt");
        return (f.exists() && !f.isDirectory());
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static ArrayList<String> retrieveFileContents(String filePath) throws FileNotFoundException {
        ArrayList<String> taskStrings = new ArrayList<String>(); 
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            taskStrings.add(s.nextLine());
        }
        return taskStrings;
    }

    public static void main(String[] args) {
        System.out.println(checkFile());
        try {
            writeToFile("duke/files/data.txt", "hello");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        Task.printAllTasks();
    }

}