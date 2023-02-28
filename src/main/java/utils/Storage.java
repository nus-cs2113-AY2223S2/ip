package utils;

import exceptions.FileLineParseException;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileLineParseException, FileNotFoundException {
        DukeFileReader dukeFileReader = new DukeFileReader(filePath);
        ArrayList<Task> tasks = dukeFileReader.readFileToTaskList();

        return tasks;
    }

    public void addNewObjectToFile(Task newObject, Ui ui) {
        DukeFileWriter dukeFileWriter = new DukeFileWriter(filePath);
        try {
            dukeFileWriter.addNewObjectToFile(newObject);
        } catch (IOException e) {
            ui.showIOException();
        }
    }

    public void rewrite(TaskList tasks, Ui ui){
        DukeFileWriter dukeFileWriter = new DukeFileWriter(filePath);
        try {
            dukeFileWriter.rewriteAllToFile(tasks);
        } catch (IOException e) {
            ui.showIOException();
        }
    }
}
