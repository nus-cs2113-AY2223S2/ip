package duke;

import duke.exceptions.FormatException;
import duke.exceptions.NoDescriptionException;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The storage class deals with things related to data storage,
 * including writing to file and write the date from file to the list.
 */
public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "data.txt";
    private static Command command = new Command();

    /**
     * Initialize and print the task list with the previous data stored in the txt file.
     * If the file doesn't exist, it will try to create a new file for future storage.
     *
     * @param tasks The list for storing tasks.
     * @param filepath The filepath to open the path, or create a new file.
     */
    public void initializeStorage(ArrayList<Task> tasks, String filepath) {
        try {
            printFileContents(tasks, filepath);
        } catch (FileNotFoundException e) {
            Ui.showInitErrorMessage();
            try {
                new File(filepath).createNewFile();
            } catch (IOException ioe) {
                throw new RuntimeException("Creation of file failed.", ioe);
            }
        }
    }

    /**
     * Write in data into the txt file to store changes.
     *
     * @param filePath The filepath to open the file and store date.
     * @param tasks The list containing the user's tasks.
     * @throws IOException If error occurred when writing to file, the exception will be thrown.
     */
    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < tasks.size(); i += 1) {
            outputWriter.write(tasks.get(i).toStorage() + System.lineSeparator());
        }
        outputWriter.flush();
        outputWriter.close();
    }

    /**
     * Try to store changes in the file, it will call the writeToFile function for implementation.
     *
     * @param filepath The filepath to open the file and store date.
     * @param tasks The list containing the user's tasks.
     */
    public void storeChanges(String filepath, ArrayList<Task> tasks) {
        try {
            writeToFile(filepath, tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    /**
     * Print the contents in the file line by line, and add it into the task list.
     *
     * @param tasks The list containing the user's tasks
     * @param filePath The filepath to open the file and store date.
     * @throws FileNotFoundException If the file is not found by using filepath, the exception will be thrown.
     */
    private static void printFileContents(ArrayList<Task> tasks, String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        System.out.println("These are the task from your file: ");
        while (s.hasNext()) {
            String task = s.nextLine();
            System.out.println(task);
            String type = task.substring(1,2);
            final int descriptionId = task.lastIndexOf("]");
            String taskDescription = task.substring(descriptionId+1).trim();
            final int doneId = task.indexOf("[X]");
            switch(type) {
            case "T":
                try {
                    command.addTodo(tasks, taskDescription);
                    if (doneId != -1) {
                        tasks.get(tasks.size()-1).markAsDone();
                    }
                } catch (NoDescriptionException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    Ui.printLine();
                }
                break;
            case "E":
                try {
                    command.addEvent(tasks, taskDescription);
                    if (doneId != -1) {
                        tasks.get(tasks.size()-1).markAsDone();
                    }
                } catch (NoDescriptionException | FormatException | ParseException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    Ui.printLine();
                }
                break;
            case "D":
                try {
                    command.addDeadline(tasks, taskDescription);
                    if (doneId != -1) {
                        tasks.get(tasks.size()-1).markAsDone();
                    }
                } catch (NoDescriptionException | FormatException | ParseException e) {
                    System.out.println("WOOFS!!! Something went wrong");
                    Ui.printLine();
                }
                break;
            }
        }
        Ui.printLine();
    }

}
