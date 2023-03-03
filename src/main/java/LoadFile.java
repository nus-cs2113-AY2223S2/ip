import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadFile {
    private static String targetFilePath = "data";
    private static String file = "data/duke.txt";

    /**
     * Creates new file if file is not found.
     *
     * @throws IOException if there are IO errors.
     */
    public static void initFile() throws IOException {
        File directory = new File(targetFilePath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File textFile = new File(file);
        if (!textFile.exists()) {
            textFile.createNewFile();
        }
    }

    public static int loadFileContents(String filePath, ArrayList<Task> taskArrayList, int currentNumber)
            throws FileNotFoundException {
        String line;
        String type;
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            currentNumber += 1;
            line = s.nextLine();
            String[] lineComponents = line.split("/");
            type = lineComponents[0];
            switch (type) {
            case "T":
                Todo newToDo = new Todo();
                newToDo.taskLabel = "[T]";
                newToDo.isDone = lineComponents[1].equals("1");
                newToDo.description = lineComponents[2];
                taskArrayList.add(newToDo);
                break;
            case "D":
                Deadline newDeadline = new Deadline();
                newDeadline.taskLabel = "[D]";
                newDeadline.isDone = lineComponents[1].equals("1");
                newDeadline.whenDue = " (" + "by" + ": " + lineComponents[3] + ")";
                newDeadline.description = lineComponents[2] + newDeadline.whenDue;
                taskArrayList.add(newDeadline);
                break;
            case "E":
                Event newEvent = new Event();
                newEvent.taskLabel = "[E]";
                newEvent.isDone = lineComponents[1].equals("1");
                newEvent.description = lineComponents[2];
                String[] splitPeriod = lineComponents[3].split(" - ");
                newEvent.start = splitPeriod[0];
                newEvent.end = splitPeriod[1];
                newEvent.description = newEvent.description
                        + " (from: " + newEvent.start + " to: " + newEvent.end + ")";
                taskArrayList.add(newEvent);
                break;
            }
        }
        return currentNumber;
    }
}
