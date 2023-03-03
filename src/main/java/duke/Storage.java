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

/**
 * This class deals with the opening, reading and writing of the saveFile.
 */
public class Storage {

    public static final File FILE_PATH = new File("./data/duke.txt");
    public static final File FILE_DIR = new File("./data");

    /**
     * This method opens file based on path. Then it will cll the readFile method to initialise the task list.
     *
     * @param taskList The list where tasks are stored. We will be initialising it later.
     */
    public void openFile(TaskList taskList) {
        File file = new File(String.valueOf(FILE_PATH));
        readFile(file, taskList);
    }

    private void readFile(File file, TaskList taskList) {
        try {
            java.io.FileReader fr = new java.io.FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null) {
                taskList.initialiseTaskList(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("You have no pre-existing tasks :)");
        }  catch (IOException e) {
            System.out.println("Error reading file :(");
        }
    }

    /**
     * This method does the checking of file path and directory and rectify any issues encountered before calling
     * the writeTaskToFile method to input data into the save file.
     *
     * @param taskList The list of task to transfer the data from.
     */
    public void writeToFile(TaskList taskList) {
        try {
            PrintWriter pw = new PrintWriter(FILE_PATH);
            writeTaskToFile(pw, taskList);
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

    private void writeTaskToFile(PrintWriter pw, TaskList taskList) {
        for (Task t : taskList.tasks) {
            pw.println(t.printToFile());
        }
        pw.close();
    }
}
