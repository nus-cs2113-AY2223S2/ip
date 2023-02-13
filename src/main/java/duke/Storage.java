package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Storage {

    public static List<Task> convertToArray(String filePath) throws FileNotFoundException{
        List<Task> taskList = new ArrayList<>();

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            String[] textLineArray = textLine.split(" ");
            switch (textLineArray[0]){
            case "T":
                String taskName = textLine.substring(7);
                Todo t = new Todo(taskName, "T");
                if (textLineArray[2].equals("1")){
                    t.markAsDone();
                }
                taskList.add(t);
                break;
            case "D":
                textLine = textLine.substring(7);
                int indexOfBoundary = textLine.indexOf("|");
                taskName = textLine.substring(0,indexOfBoundary);
                String by = textLine.substring(indexOfBoundary + 1);
                Deadline d = new Deadline(taskName, "D", by);
                if (textLineArray[2].equals("1")){
                    d.markAsDone();
                }
                taskList.add(d);
                break;
            case "E":
                textLine = textLine.substring(7);
                indexOfBoundary = textLine.indexOf("|");
                int lastIndexOfBoundary = textLine.lastIndexOf("|");
                taskName = textLine.substring(0,indexOfBoundary);
                String from = textLine.substring(indexOfBoundary + 1, lastIndexOfBoundary);
                String to = textLine.substring(lastIndexOfBoundary + 1);
                Event e = new Event(taskName, "E", from , to);
                if (textLineArray[2].equals("1")){
                    e.markAsDone();
                }
                taskList.add(e);
                break;
            default:
                UI.printMessage("finish converting");
            }
        }
        return taskList;
    }