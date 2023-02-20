package duke.filemanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.task.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private TaskWriter taskWriter = new TaskWriter();
    private TaskLoader taskLoader = new TaskLoader();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> setTasks() throws DukeException {
        return taskLoader.setClasses(filePath);
    }

    public void writeTasks(ArrayList<Task> taskList) throws DukeException {
        taskWriter.writeToJson(taskList, filePath);
    }
}
