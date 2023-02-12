package grandduke.command;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import grandduke.exception.GrandException;
import grandduke.task.TaskList;

public abstract class Storage {
    private static final String FILE_DIRECTORY = "./store";
    private static final String FILE_NAME = "data.txt";

    public static String getFilePath() {
        return FILE_DIRECTORY + "/" + FILE_NAME;
    }

    public static String getFileDirectory() {
        return FILE_DIRECTORY;
    }

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

    public static void loadList(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        Io.printOutput(FILE_DIRECTORY + "/" + FILE_NAME + " found. Loading data...");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            String[] inputArr = line.split(" ", 2);
            String command = inputArr[0];

            String commandDetails;

            if (inputArr.length > 1) {
                commandDetails = inputArr[1].strip();
            } else {
                commandDetails = "";
            }

            try {
                switch (command) {
                    case Io.TODO_COMMAND:
                        TaskList.addTask(commandDetails, Io.TODO_COMMAND, true);
                        break;

                    case Io.DEADLINE_COMMAND:
                        TaskList.addTask(commandDetails, Io.DEADLINE_COMMAND, true);
                        break;

                    case Io.EVENT_COMMAND:
                        TaskList.addTask(commandDetails, Io.EVENT_COMMAND, true);
                        break;

                    default:
                        break;
                }
            } catch (GrandException e) {
                Io.printOutput("data.txt is corrupted. Please delete the file and restart the program.");
                System.exit(-1);
            }
        }

        Io.printOutput("Data loaded successfully.");

        sc.close();
    }
}
