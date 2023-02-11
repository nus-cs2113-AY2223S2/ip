package duke.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.tasks.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileManager {

    private static final String filePath = "duke/files/data.txt";
    private static boolean isFile = false;

    public static boolean checkFile() {
        File f = new File(filePath);
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

    private static void addTaskFromFile(String parsedTask) {
        char type = parsedTask.charAt(1);
        char status = parsedTask.charAt(4);
        String description = parsedTask.substring(7);
        Task temp;
        if (type == 'T') {
            temp = new Todo(description);
        } else if (type == 'D') {
            String dueDate = description.substring(description.indexOf(":")+2, description.indexOf(")"));
            temp = new Deadline(description, dueDate);
        } else {
            String startTime = description.substring(description.indexOf("from"), description.indexOf("to"));
            String endTime = description.substring(description.indexOf("to"));
            endTime = endTime.substring(0, endTime.length()-1);
            temp = new Event(description, startTime, endTime);
        }
        if (status == 'X'){
            temp.isComplete = true;
        }
    }

    private static void populateTaskList() throws FileNotFoundException {
        try {
            ArrayList<String> fileContents = retrieveFileContents(filePath);
            for(int i = 0; i<fileContents.size(); i++) {
                addTaskFromFile(fileContents.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error fetching file contents");
        }
    }

    public static void main(String[] args) {
        try {
            populateTaskList();
            Task.printAllTasks();
        } catch (FileNotFoundException e) {
            System.out.print("Oops!");
        }
    }

}