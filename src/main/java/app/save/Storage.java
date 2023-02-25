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
public class Storage {
    private File taskFile;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner s = new Scanner(taskFile);
            ArrayList<Task> savedTasks = new ArrayList<>();

            while (s.hasNext()) {
                Task currentTask = StringParser.convertStringToTask(s.nextLine());
                if (currentTask != null) {
                    savedTasks.add(currentTask);
                }
            }
            s.close();
            return savedTasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("You do not have an existing task list.\n Initialising a new one.");
        }
    }

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
