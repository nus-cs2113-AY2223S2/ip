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

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    /**
     * Method used to load tasks from memory and add them to a Task-list, if there are any.
     * @return An ArrayList containing all the Tasks currently in memory.
     * @throws DukeException If text file containing Tasks does not exist.
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
     * Method used to write tasks from the current iteration of Duke into a text file
     * with the specified directory. Creates a folder and file if it doesn't yet exist.
     * @param tasks The Task-list containing all the tasks from the most recent iteration of Duke.
     * @throws DukeException If there was an error in writing tasks to the text file.
     */
    public void write(TaskList tasks) throws DukeException {
        File folder = new File("data");
        File file = new File("data/tasks.txt");
        FileWriter fw;
        try {
            if (!folder.exists() || !file.exists()){
                folder.mkdir();
                file.createNewFile();
            }
            fw = new FileWriter(taskFile);
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
