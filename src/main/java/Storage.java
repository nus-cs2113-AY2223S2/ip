import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import duke.Task;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import static duke.TaskList.textList;

/**
 * Represents a storage object that deals with loading tasks
 * from the file and saving tasks in the file.
 */
public class Storage {
    private static String filePath = "data/duke.txt";
    private static final File file = new File(filePath);

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Saves the tasks in the task list to the file.
     * @param textList The task list that contains the tasks to be saved.
     * @throws ArrayIndexOutOfBoundsException
     */
    public static void saveFile(ArrayList<Task> textList) throws ArrayIndexOutOfBoundsException {
        try {
            Files.createDirectories(new File("data").toPath()); //create data folder if it does not exist
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            for (Task task : textList) {
                if (task instanceof Todo) {
                    fw.write("T | " + (task.isDone ? "1" : "0") + " | " + task.description + System.lineSeparator());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    fw.write("D | " + (task.isDone ? "1" : "0") + " | " + task.description + "| " + deadline.by_copy + System.lineSeparator());
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    fw.write("E | " + (task.isDone ? "1" : "0") + " | " + task.description + "| " + event.from_copy + " to " + event.to_copy + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    /**
     * Loads the tasks from the file into the task list.
     * @throws ArrayIndexOutOfBoundsException
     * @throws FileNotFoundException
     */
    public static void readFile() throws ArrayIndexOutOfBoundsException, FileNotFoundException {
        if (file.exists()) {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
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
                    String[] timeArray = lineArray[3].trim().split("to");
                    String from = timeArray[0].trim();
                    String to = timeArray[1].trim();
                    Event event = new Event(description, from, to);
                    if (isDone) {
                        event.markAsDone();
                    }
                    textList.add(event);
                }
            }
            s.close();
        }

    }

}
