package duke.storage;

import duke.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Storage {
    private static final String directoryPath = "src/main/java/duke";
    private static final String filePath = "src/main/java/duke/duke.txt";

    public static List<Task> textFileToProgram() throws FileNotFoundException {
        List<Task> listOfTasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        // Load data from file
        while (s.hasNext()) {
            String textFileLine = s.nextLine();
            String[] textFileLines = textFileLine.split(" ");
            //Categorise and filter based on taskType
            switch(textFileLines[0]){
            case "T": // identify as a to-do task
                String taskDescription = textFileLine.substring(8);
                Todo todoTask = new Todo(taskDescription, "T");
                //Determine if task is pre-marked
                if (textFileLines[2].equals("1")){
                    todoTask.markAsDone();
                }
                listOfTasks.add(todoTask);
                break;
            case "D": // identify as a deadline task
                textFileLine = textFileLine.substring(8);   // read line from task description onwards
                int separatorIdx = textFileLine.indexOf("|");
                taskDescription = textFileLine.substring(0 , separatorIdx-1);
                String by = textFileLine.substring(separatorIdx + 2);
                Deadline deadlineTask = new Deadline(taskDescription, "D" , by);
                //Determine if task is pre-marked
                if (textFileLines[2].equals("1")){
                    deadlineTask.markAsDone();
                }
                listOfTasks.add(deadlineTask);
                break;
            case "E":
                textFileLine = textFileLine.substring(8);
                separatorIdx = textFileLine.indexOf("|");
                int lastSeparatorIdx = textFileLine.lastIndexOf("|");
                taskDescription = textFileLine.substring(0, separatorIdx - 1);
                String from = textFileLine.substring(separatorIdx + 2, lastSeparatorIdx - 1);
                String to = textFileLine.substring(lastSeparatorIdx + 2);
                Event event = new Event(taskDescription, "E", from, to);
                if (textFileLines[2].equals("1")) {
                    event.markAsDone();
                }
                listOfTasks.add(event);
                break;
            default:
                System.out.println("Finished processing text file!");
            }
        }
        return listOfTasks;
    }

    public static void writeToFile(String newText) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(newText);
        fw.close();
    }
    public static void appendTextToFile(String newText) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(newText);
        fw.close();
    }

    public static void checkFileAccess() throws IOException {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
    }
}
