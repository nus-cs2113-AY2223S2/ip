package app.save;

import app.exceptions.DukeException;
import app.tasks.Task;
import app.tasks.TaskList;
import app.parser.StringParser;
import app.parser.TaskParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used to deal with reading and writing of Tasks from memory.
 */
public class Storage {
    private File taskFile;

    /**
     * Constructor to initiate a new instance of Storage with the specified file path.
     * @param filePath The path address for storage to memory.
     */
    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    /**
     * Method used to load tasks from memory and add them to a Task-list.
     * Creates a new empty Task-list if there isn't an existing one.
     * @return An ArrayList containing all the Tasks currently in memory.
     * @throws DukeException If text file containing Tasks does not exist when it should.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner scanner = new Scanner(taskFile);
            ArrayList<Task> savedTasks = new ArrayList<>();

            while (scanner.hasNext()) {
                Task currentTask = StringParser.convertStringToTask(scanner.nextLine());
                if (currentTask != null) {
                    savedTasks.add(currentTask);
                }
            }
            scanner.close();
            return savedTasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("You do not have an existing task list.\n Initialising a new one.");
        }
    }

    /**
     * Method used to write Tasks from the current iteration of Duke into a text file
     * with the specified directory. Creates a folder and file if it doesn't yet exist.
     * @param tasks The Task-list containing all the Tasks from the most recent iteration of Duke.
     * @throws DukeException If there was an error in writing Tasks to the text file.
     */
    public void write(TaskList tasks) throws DukeException {
        File folder = new File("data");
        File file = new File("data/tasks.txt");

        try {
            if (!folder.exists() || !file.exists()){
                folder.mkdir();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(taskFile);
            for (Task task : tasks.getTasks()) {
                if (task == null) break;
                fw.write(TaskParser.convertTaskToString(task));
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("ONO! There was an error when saving your task list.");
        }
    }

}
