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

    private static final String dirPath = "data";
    private static final String filePath = "data/duke.txt";

    public static ArrayList<Task> convertToList() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            String[] textLineArray = textLine.split(" ");
            switch (textLineArray[0]) {
            case "T":
                String taskName = textLine.substring(8);
                Todo todo = new Todo(taskName, "T");
                if (textLineArray[2].equals("1")) {
                    todo.markAsDone();
                }
                taskList.add(todo);
                break;
            case "D":
                textLine = textLine.substring(8);
                int indexOfBoundary = textLine.indexOf("|");
                taskName = textLine.substring(0, indexOfBoundary - 1);
                String by = textLine.substring(indexOfBoundary + 2);
                Deadline deadline = new Deadline(taskName, "D", by);
                if (textLineArray[2].equals("1")) {
                    deadline.markAsDone();
                }
                taskList.add(deadline);
                break;
            case "E":
                textLine = textLine.substring(8);
                indexOfBoundary = textLine.indexOf("|");
                int lastIndexOfBoundary = textLine.lastIndexOf("|");
                taskName = textLine.substring(0, indexOfBoundary - 1);
                String from = textLine.substring(indexOfBoundary + 2, lastIndexOfBoundary - 1);
                String to = textLine.substring(lastIndexOfBoundary + 2);
                Event event = new Event(taskName, "E", from, to);
                if (textLineArray[2].equals("1")) {
                    event.markAsDone();
                }
                taskList.add(event);
                break;
            default:
                UI.printMessage("finish converting");
            }
        }
        return taskList;
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void checkFileAccess() throws IOException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
    }
}