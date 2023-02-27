package duke;

import task.Task;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    public static final File FILE_PATH = new File("./data/duke.txt");
    public static final File FILE_DIR = new File("./data");

    public static void openFile() {
        File file = new File(String.valueOf(FILE_PATH));
        readFile(file);
    }

    public static void readFile(File file) {
        try {
            java.io.FileReader fr = new java.io.FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null) {
                TaskList.initialiseTaskList(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("You have no pre-existing tasks :)");
        }  catch (IOException e) {
            System.out.println("Error reading file :(");
        }
    }

    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            PrintWriter pw = new PrintWriter(FILE_PATH);
            writeTaskToFile(pw, tasks);
        } catch (FileNotFoundException e) {
            if (!FILE_DIR.isDirectory()) {
                System.out.println("Data directory not found. Creating new data directory");
                Path path = Paths.get(String.valueOf(FILE_DIR));
                try {
                    Files.createDirectories(path);
                } catch (IOException f) {
                    System.out.println("Failed to create directory");
                }
            }
            if (!FILE_PATH.isFile()) {
                System.out.println("File not found. Creating new text file");
                Path fileLoc = Paths.get(String.valueOf(FILE_PATH));
                try {
                    Files.createFile(fileLoc);
                } catch (IOException f) {
                    System.out.println("Failed to create file");
                }
            }
        }
    }

    public static void writeTaskToFile(PrintWriter pw, ArrayList<Task> tasks) {
        for (Task t : tasks) {
            pw.println(t.printToFile());
        }
        pw.close();
    }
}
