package duke.storage;

import duke.Task;
import duke.UI;
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
    private static final String directoryPath = "src/main/java/duke/storage";
    private static final String filePath = "src/main/java/duke/storage/duke.txt";

    public static List<Task> textFileToArray() throws FileNotFoundException {
        List<Task> listOfTasks = new ArrayList<>();
        File file = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            String[] textLines = textLine.split(" ");
            switch (textLines[0]) {
            case "T":
                String taskDescription = textLine.substring(8);
                Todo todoTask = new Todo(taskDescription, "T");
                if (textLines[2].equals("1")) {
                    todoTask.markAsDone();
                }
                listOfTasks.add(todoTask);
                break;
            case "D":
                textLine = textLine.substring(8);
                int separatorIdx = textLine.indexOf("|");
                taskDescription = textLine.substring(0, separatorIdx - 1);
                String by = textLine.substring(separatorIdx + 2);
                Deadline deadlineTask = new Deadline(taskDescription, "D", by);
                if (textLines[2].equals("1")) {
                    deadlineTask.markAsDone();
                }
                listOfTasks.add(deadlineTask);
                break;
            case "E":
                textLine = textLine.substring(8);
                separatorIdx = textLine.indexOf("|");
                int lastSeparatorIdx = textLine.lastIndexOf("|");
                taskDescription = textLine.substring(0, separatorIdx - 1);
                String startTime = textLine.substring(separatorIdx + 2, lastSeparatorIdx - 1);
                String endTime = textLine.substring(lastSeparatorIdx + 2);
                Event eventTask = new Event(taskDescription, "E", startTime, endTime);
                if (textLines[2].equals("1")) {
                    eventTask.markAsDone();
                }
                listOfTasks.add(eventTask);
                break;
            default:
                System.out.println("File conversion is complete!");
            }
        }
        return listOfTasks;
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendTextToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void checkFile() throws IOException {
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
