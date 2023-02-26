package storage;

import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import exception.IncompleteInputException;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Represents a Storage class that is used to save and load ArrayList<Task> to and from the
 * ./data/duke.txt file.
 */
public class TaskStorage {
    private final String filePath;

    public TaskStorage(String filePath)  {
        this.filePath = filePath;
    }

    /**
     * Save the given ArrayList<Task> into lines of String in the ./data/duke.txt file
     * If the directory and file does not exist, it will create the data/duke.txt in the current working directory
     * This method is called by {@link command.SaveCommand#execute}
     *
     * @param tasks The entire ArrayList<Task> to be saved into the ./data/duke.txt file
     * @throws java.io.IOException If an I/O error occurred either in the creation or writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws java.io.IOException {
        File dir = new File("data");
        File f = new File("data/duke.txt");
        if (!dir.exists()) {
            System.out.println("Directory does not exist, creating a directory called data...");
            boolean success = dir.mkdir();
            if (success) {
                System.out.println("Directory data has been successfully created");
            }
        }
        if (!f.exists()) {
            System.out.println("File does not exist, creating a new file ./data/duke.txt");
           boolean success = f.createNewFile();
           if (success) {
               System.out.println("File duke.txt has been successfully created");
           }
        }

        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task t: tasks) {
            fw.write(t.toSaveString() + System.lineSeparator());
        }

        fw.close();
    }

    /**
     * Loads the content from the ./data/duke.txt file and process into ArrayList<Task> if it exists
     * @return ArrayList<Task> containing the task in the .txt file
     * @throws IncompleteInputException If the data stored in the .txt file has incomplete information
     * @throws DateTimeParseException If the Time specified for {@link Deadline} and {@link Event} does not follow the YYYY-MM-DD HH:mm format
     */
    public ArrayList<Task> loadTasks() throws IncompleteInputException, DateTimeParseException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File("data/duke.txt");
        if (f.exists()) {
            try {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String inputStr = s.nextLine();
                    Task taskToAdd = Parser.processSavedInput(inputStr);
                    tasks.add(taskToAdd);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Exception : Failed to read existing file " + e);
            }
        }
        return tasks;
    }
}
