import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the creation, loading and saving of data
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage class
     *
     * @param filePath the path of save file to be stored locally
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file if it does not exist in the current directory
     *
     * @throws IOException if there is an error while creating the file
     */
    public void createSaveFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            file.mkdirs();
        }
    }

    /**
     * Updates task list with data from previous saved file
     *
     * @param tasks task list to be loaded with data
     */
    public void loadSaveFile(TaskList tasks) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|", -1);
                if (line.startsWith("T")) {
                    ToDo todo = new ToDo(args[2]);
                    if (args[2].equals("X")) {
                        todo.setAsDone();
                    }
                    tasks.addTask(todo);
                } else if (line.startsWith("D")) {
                    Deadline deadline = new Deadline(args[2], args[3]);
                    if (args[2].equals("X")) {
                        deadline.setAsDone();
                    }
                    tasks.addTask(deadline);
                } else {
                    Event event = new Event(args[2], args[3], args[4]);
                    if (args[2].equals("X")) {
                        event.setAsDone();
                    }
                    tasks.addTask(event);
                }
            }
        } catch (IOException exception) {
            System.out.println("☹ OOPS!!! An error occurred");
        } catch (Exception exception) {
            System.out.println("☹ OOPS!!! An error occurred 2");
        }
    }

    /**
     * Updates the saved file with data from current task list
     *
     * @param tasks task list to be read and transferred to the save file
     * @throws IOException if there is an error during the transfer of data
     */
    public void updateSaveFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.sizeOfTaskList(); i++) {
            Task currentTask = tasks.getTask(i);
            String type;
            String description = currentTask.getDescription();
            String done = currentTask.isDone() ? "X" : " ";
            if (currentTask instanceof ToDo) {
                type = "T";
                fw.write(type + "|" + done + "|" + description + "\n");
            } else if (currentTask instanceof Deadline) {
                type = "D";
                String by = ((Deadline) currentTask).getBy();
                fw.write(type + "|" + done + "|" + description + "|" + by + "\n");
            } else {
                type = "E";
                String from = ((Event) currentTask).getFrom();
                String to = ((Event) currentTask).getTo();
                fw.write(type + "|" + done + "|" + description + "|" + from + "|" + to + "\n");
            }
        }
        fw.close();
    }
}
