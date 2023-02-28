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

public class Data {

    static final String DIR_PATH = "./data/";
    static final String FILE_PATH = "./data/duke.txt";

    public static ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            createDirectory();
            createFile();
            tasks = readFile();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        } catch (IOException e) {
            System.out.println("ERROR: Failed to create files for storage");
        }
        return tasks;
    }

    public static void createDirectory() throws IOException {
        Path path = Paths.get(DIR_PATH);
        Files.createDirectories(path);
    }

    public static void createFile() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    public static ArrayList<Task> readFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<Task>();
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
                ToDo todo = TaskManager.createToDo(command, isDone);
                tasks = TaskManager.addToList(tasks, todo);
            } else if (command.matches("^deadline.*$")) {
                Deadline deadline = TaskManager.createDeadline(command, isDone);
                tasks = TaskManager.addToList(tasks, deadline);
            } else {
                Event event = TaskManager.createEvent(command, isDone);
                tasks = TaskManager.addToList(tasks, event);
            }
        }
        scanner.close();
        return tasks;
    }

    public static void saveData(ArrayList<Task> tasks) {
        try {
            System.out.println("Saving your tasks before exit...");
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String class_name = task.getClass().getName();
                String done;
                if (task.isDone()) {
                    done = "X";
                } else {
                    done = "N";
                }
                if (class_name.equals("ChatTPG.ToDo")) {
                    writer.write(done + "todo " + task.getDescription() + "\n");
                } else if (class_name.equals("ChatTPG.Deadline")) {
                    Deadline deadline = (Deadline) task;
                    writer.write(done + "deadline " + deadline.getDescription()
                                    + " /by " + deadline.getBy() + "\n");
                } else {
                    Event event = (Event) task;
                    writer.write(done + "event " + event.getDescription()
                                    + " /from " + event.getFrom()
                                    + " /to " + event.getTo() + "\n");
                }
            }
            writer.close();
            System.out.println("Save complete.");
        } catch (IOException e) {
            System.out.println("ERROR: Unable to save data to file.");
        }
    }

}
