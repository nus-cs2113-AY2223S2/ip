package ChatTPG;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Class to load tasks from data file and save tasks to data file.
 */
public class Storage {

    /** Specifies the directory path to be created */
    static final String DIR_PATH = "./data/";

    /** Specifies the file path to be created */
    static final String FILE_PATH = "./data/duke.txt";

    /**
     * Loads the tasks into the list of tasks by reading from the data file.
     */
    public static void loadData() {
        try {
            createDirectory();
            createFile();
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        } catch (IOException e) {
            System.out.println("ERROR: Failed to create files for storage");
        }
    }

    /**
     * Creates the directory used to store the data file.
     *
     * @throws IOException
     */
    public static void createDirectory() throws IOException {
        Path path = Paths.get(DIR_PATH);
        Files.createDirectories(path);
    }

    /**
     * Creates the file used to store the data on the tasks in the list.
     *
     * @throws IOException
     */
    public static void createFile() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    /**
     * Reads from the data file and converts the data back into the individual tasks
     * before adding the tasks to the list accordingly.
     *
     * @throws FileNotFoundException if data file does not exist.
     */
    public static void readFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.matches("^ *$")) {
                break;
            }

            boolean isDone;
            if (command.charAt(0) == 'X') {
                isDone = true;
            } else {
                isDone = false;
            }
            command = command.substring(1);
            if (command.matches("^todo.*$")) {
                ToDo todo = TaskList.createToDo(command, isDone);
                TaskList.addToList(todo);
            } else if (command.matches("^deadline.*$")) {
                try {
                    Deadline deadline = TaskList.createDeadline(command, isDone, true);
                    TaskList.addToList(deadline);
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            } else {
                try {
                    Event event = TaskList.createEvent(command, isDone, true);
                    TaskList.addToList(event);
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            }
        }
        scanner.close();
    }

    /**
     * Writes to the data file to save the current state of tasks in the list.
     *
     * @param tasks List of tasks.
     */
    public static void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String className = task.getClass().getName();
                String done;
                if (task.isDone()) {
                    done = "X";
                } else {
                    done = "N";
                }
                if (className.equals("ChatTPG.ToDo")) {
                    writer.write(done + "todo " + task.getDescription() + "\n");
                } else if (className.equals("ChatTPG.Deadline")) {
                    Deadline deadline = (Deadline) task;
                    writer.write(done + "deadline " + deadline.getDescription()
                                    + " /by  " + deadline.getBy() + "\n");
                } else {
                    Event event = (Event) task;
                    writer.write(done + "event " + event.getDescription()
                                    + " /from  " + event.getFrom()
                                    + " /to  " + event.getTo() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR: Unable to save data to file.");
        }
    }

}
