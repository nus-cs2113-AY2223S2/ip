import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import duke.Task;

public class Save {
    private static String filePath = "data/duke.txt";

    public Save(String filePath) {
        Save.filePath = filePath;
    }

    public static void saveFile(ArrayList<Task> textList) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task task : textList) {
            if (task instanceof Todo) {
                fw.write("T | " + (task.isDone ? "1" : "0") + " | " + task.description + System.lineSeparator());
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                fw.write("D | " + (task.isDone ? "1" : "0") + " | " + task.description + "| " + deadline.by + System.lineSeparator());
            } else if (task instanceof Event) {
                Event event = (Event) task;
                fw.write("E | " + (task.isDone ? "1" : "0") + " | " + task.description + "| " + event.from + "-" + event.to + System.lineSeparator());
            }
        }
        fw.close();
    }
    public static void readFile(ArrayList<Task> textList) throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        File f = new File("data/duke.txt"); // create a File for the given file path
        Scanner s = new Scanner(new FileInputStream(("data/duke.txt"))); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineArray = line.split("\\|");
            String taskType = lineArray[0].trim(); //use trim to remove whitespaces
            boolean isDone = lineArray[1].trim().equals("1"); //check if is 1 (means [X]) or 0 (means [ ])
            String description = lineArray[2].trim();
            if (taskType.equals("T")) {
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                textList.add(todo);
            } else if (taskType.equals("D")) {
                String by = lineArray[3].trim();
                Deadline deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                textList.add(deadline);
            } else if (taskType.equals("E")) {
                String[] timeArray = lineArray[3].trim().split("-");
                String from = timeArray[0].trim();
                String to = timeArray[1].trim();
                Event event = new Event(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                textList.add(event);
            }
        }
    }
}
