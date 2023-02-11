package duke.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.tasks.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/* 
    Upon cloning, user will have an empty data file duke/files/data.txt
    After first use, all tasks on the user list will save in data.txt
    When Duke is restarted, all tasks from data.txt will load to the list
*/

public class FileManager {

    private static final String filePath = "duke/files/data.txt";

    // Write to a file
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    // Append to a file
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    // Retrieve the contents of a file as an array of strings where each line is a string
    private static ArrayList<String> retrieveFileContents(String filePath) throws FileNotFoundException {
        ArrayList<String> taskStrings = new ArrayList<String>(); 
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            taskStrings.add(s.nextLine());
        }
        return taskStrings;
    }

    // Parse a string and create and add a task from it
    private static void addTaskFromFile(String parsedTask) {
        char type = parsedTask.charAt(1); // Type will be at index 1 based on how data is saved
        char status = parsedTask.charAt(4); // Status will be at index 4
        String description = parsedTask.substring(7); // Description starts at index 7
        Task temp;
        if (type == 'T') { // Adding a todo
            temp = new Todo(description);
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

    // Parse all tasks from the file to list
    public static void populateTaskList() throws FileNotFoundException {
        try {
            ArrayList<String> fileContents = retrieveFileContents(filePath);
            for(int i = 0; i<fileContents.size(); i++) {
                addTaskFromFile(fileContents.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error fetching file contents");
        }
    }

    // Parse all tasks from list to file
    public static void populateFile() throws IOException {
        ArrayList<Task> taskList = Task.getTasksArray();
        for(int i = 0; i<taskList.size(); i++) {
            if (i == 0) {
                writeToFile(filePath, taskList.get(i).printTask()+"\n");
            } else {
                appendToFile(filePath, taskList.get(i).printTask()+"\n");
            }
        }
    }
}