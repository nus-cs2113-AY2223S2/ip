package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * This class contains the methods responsible for reading from and saving information about the tasks.
 * Each element in the ArrayList will be saved as a line in a txt file.
 * Examples of lines in the txt file are as follows:
 * T|1|read book
 * D|0|return book|June 6th
 * E|0|project meeting|Aug 6th 2pm|4pm
 **/
public class FileOperations {
    /** Relative filepath to tasks.txt */
    private static final String FILE_PATH = "./tasks.txt";

    /** ArrayList of tasks */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Loads the information required for the ArrayList from the tasks.txt file.
     *
     * @return An ArrayList of tasks.
     * @throws IOException If the tasks.txt file does not exist.
     */
    public static ArrayList<Task> loadArrayListFromFile() {

        try {

            File inputFile = new File(FILE_PATH);
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String entry;

            System.out.println("Save file found.\nLoading save file...");

            while ((entry = br.readLine()) != null) {
                tasks.add(extractTaskFromString(entry));
            }


        } catch (IOException exception) {
            System.out.println("Save file does not exist.\nCreating new save file...");
        }

        return tasks;

    }

    /**
     * Saves the information from the ArrayList to the file
     *
     * @param tasks An ArrayList of tasks.
     * @throws IOException If the tasks.txt file cannot be written to.
     */
    public static void saveArrayListToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH, false);
        fileWriter.write(formatArrayList(tasks));
        fileWriter.close();
    }

    private static String formatArrayList(ArrayList<Task> tasks) {

        StringBuilder output = new StringBuilder();

        for (Task t : tasks) {
            if (t instanceof Todo) {
                output.append(t.getTaskType() + "|" + (t.isDone() ? 1 : 0) + "|" + t.getDescription()
                        + System.lineSeparator());
            } else if (t instanceof Deadline) {
                output.append(t.getTaskType() + "|" + (t.isDone() ? 1 : 0) + "|" + t.getDescription() + "|"
                        + ((Deadline) t).getBy() + System.lineSeparator());
            } else {
                output.append(t.getTaskType() + "|" + (t.isDone() ? 1 : 0) + "|" + t.getDescription() + "|"
                        + ((Event) t).getFrom() + "|" + ((Event) t).getTo() + System.lineSeparator());
            }
        }
        return output.toString();
    }

    private static Task extractTaskFromString(String entry) {

        Task task;
        String[] stringArrayOfEntry = entry.split("\\|");

        // Check type of Task and create new Task based on the type
        if (entry.startsWith("T")) {
            task = new Todo(stringArrayOfEntry[2]);
        } else if (entry.startsWith("D")) {
            task = new Deadline(stringArrayOfEntry[2], stringArrayOfEntry[3]);
        } else {
            task = new Event(stringArrayOfEntry[2], stringArrayOfEntry[3], stringArrayOfEntry[4]);
        }

        // Set done status of the Task
        task.setDone(stringArrayOfEntry[1].equals("1"));

        return task;

    }
}
