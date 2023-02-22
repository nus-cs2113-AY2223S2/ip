package grandduke.command;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import grandduke.exception.GrandException;
import grandduke.task.TaskList;

public abstract class Storage {
    private static final String FILE_DIRECTORY = "./store";
    private static final String FILE_NAME = "data.txt";

    /**
     * get the directory of the data file
     * 
     * @return the directory of the data file
     */
    public static String getFileDirectory() {
        return FILE_DIRECTORY;
    }

    /**
     * get the file path of the data file
     * 
     * @return the file path of the data file
     */
    public static String getFilePath() {
        return getFileDirectory() + "/" + FILE_NAME;
    }

    /**
     * Ensure that the appropriate directory and file exists. If not, create them,
     * then load the data
     */
    public static void loadData() {
        try {
            // check if folder exists first
            File directory = new File(FILE_DIRECTORY);
            if (!directory.exists()) {
                Io.printOutput(FILE_DIRECTORY + " does not exist. Creating directory...");
                directory.mkdir();
            }

            // check if file exists
            File file = new File(getFilePath());
            if (!file.exists()) {
                Io.printOutput(getFilePath() + " does not exist. Creating file...");
                file.createNewFile();
            }

            loadList(file);

        } catch (FileNotFoundException e) {
            Io.printOutput("Error loading data, file not found.");
            System.exit(-1);
        } catch (Exception e) {
            Io.printOutput("data.txt is corrupted. Please delete the file and restart the program.");
            System.exit(-1);
        }
    }

    /**
     * Load the data from the data file into the task list
     * 
     * @param file the data file
     * @throws FileNotFoundException if the data file is not found
     */
    public static void loadList(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        Io.printOutput(FILE_DIRECTORY + "/" + FILE_NAME + " found. Loading data...");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            try {
                TaskList.loadTask(line);
            } catch (GrandException e) {
                Io.printOutput("data.txt is corrupted. Please delete the file and restart the program.");
                System.exit(-1);
            }
        }

        Io.printOutput("Data loaded successfully.");

        sc.close();
    }

    /**
     * Ensure that the appropriate directory and file exists. If not, create them,
     * then save the data
     */
    public static void saveData() {
        try {
            // check if folder exists first
            File directory = new File(FILE_DIRECTORY);
            if (!directory.exists()) {
                Io.printOutput(FILE_DIRECTORY + " does not exist. Creating directory...");
                directory.mkdir();
            }

            // check if file exists
            File file = new File(getFilePath());
            if (!file.exists()) {
                Io.printOutput(getFilePath() + " does not exist. Creating file...");
                file.createNewFile();
            }

            saveList(file);

        } catch (Exception e) {
            Io.printOutput("Error saving data.");
        }
    }

    /**
     * Save the data from the task list into the data file
     * 
     * @param file the data file
     * @throws IOException if there is an error writing to the data file
     */
    public static void saveList(File file) throws IOException {
        FileWriter fw = new FileWriter(file);

        for (int i = 0; i < TaskList.getTaskListSize(); i++) {
            fw.write(TaskList.getTasks().get(i).getTaskSaveString() + "\n");
        }

        fw.close();
    }
}
