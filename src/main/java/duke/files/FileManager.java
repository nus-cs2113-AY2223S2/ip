package duke.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.tasks.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Manages all file saving and uploading for Duke. Upon cloning,
 * user will have an empty data file. When Duke is activated, any
 * existing data will be added to the task list. When Duke is closed,
 * any tasks in the list will be saved. When Duke is restarted,
 * all tasks from data.txt will load to the list.
 */
public class FileManager {

    private static final String FILEPATH = "./data.txt";

    /**
     * Writes data to a file at a designated path location.
     * @param FILEPATH Path for file to be written on
     * @param textToAdd Text to be added to the file
     * @throws IOException Occurs when file can't be accessed
     */
    private static void writeToFile(String FILEPATH, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes data to a file at a designated path location.
     * @param FILEPATH Path for file to be added on
     * @param textToAppend Text to be added to the file
     * @throws IOException Occurs when file can't be accessed
     */
    private static void appendToFile(String FILEPATH, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Creates an ArrayList of Strings of each line in a file
     * @param FILEPATH Location of the file to be parsed
     * @return ArrayList of strings of the file contents
     * @throws FileNotFoundException if the file does not exist
     */
    private static ArrayList<String> retrieveFileContents(String FILEPATH) throws FileNotFoundException {
        ArrayList<String> taskStrings = new ArrayList<String>(); 
        File f = new File(FILEPATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            taskStrings.add(s.nextLine());
        }
        return taskStrings;
    }

    /**
     * Given a string representing a task, creates the task.
     * @param parsedTask The parsed version of the task
     */
    private static void addTaskFromFile(String parsedTask) {
        char type = parsedTask.charAt(1); // Type will be at index 1 based on how data is saved
        char status = parsedTask.charAt(4); // Status will be at index 4
        String description = parsedTask.substring(7); // Description starts at index 7
        Task temp;
        if (type == 'T') { // Adding a todo
            temp = new Todo("todo " + description);
        } else if (type == 'D') { // Adding a deadline
            String dueDate = description.substring(description.indexOf(":")+2, description.indexOf(")"));
            temp = new Deadline(description, dueDate);
        } else { // Adding an event
            String startTime = description.substring(description.indexOf("from"), description.indexOf("to"));
            String endTime = description.substring(description.indexOf("to"));
            endTime = endTime.substring(0, endTime.length()-1);
            temp = new Event(description, startTime, endTime);
        }
        if (status == 'X'){
            temp.isComplete = true;
        }
    }

    /**
     * Moves all tasks from the file to the task list using helper methods.
     * @throws Exception Occurs when a helper method hits an exception.
     */
    public void populateTaskList() throws Exception {
        try {
            File f = new File(FILEPATH);
            if (!f.exists()) {
                FileWriter fw = new FileWriter(FILEPATH);
                fw.write("");
            }
            ArrayList<String> fileContents = retrieveFileContents(FILEPATH);
            for(int i = 0; i<fileContents.size(); i++) {
                addTaskFromFile(fileContents.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error fetching file contents");
        }
    }

    /**
     * Moves all tasks from the task list to a file using helper methods
     * @throws IOException Occurs when file can't be accessed
     */
    public void populateFile() throws IOException {
        ArrayList<Task> taskList = TaskList.getTasksArray();
        if (taskList.size() == 0) {
            writeToFile(FILEPATH, "");
            return;
        }
        for(int i = 0; i<taskList.size(); i++) {
            if (i == 0) {
                writeToFile(FILEPATH, taskList.get(i).printTask()+"\n");
            } else {
                appendToFile(FILEPATH, taskList.get(i).printTask()+"\n");
            }
        }
    }
}