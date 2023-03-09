package duke;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Driver class for loading and storing task list data
 */
public class Storage {
    File data;

    public Storage(String folderPath,String fileName) throws IOException, DukeException {

        java.nio.file.Path dataPath = java.nio.file.Paths.get(folderPath, fileName);
        boolean fileExists = java.nio.file.Files.exists(dataPath);
        data = new File(dataPath.toUri());
        data.getParentFile().mkdirs();
        if (!fileExists) {
            data.createNewFile();
        } else {
            loadTaskList();
        }
    }
    ArrayList<Task> loadTaskList() throws FileNotFoundException, DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            Task nextTask = Ui.parseTask(s.nextLine());
            taskList.add(nextTask);
        }
        return taskList;
    }
    void saveTaskList(ArrayList<Task> tasks, File data) throws IOException, DukeException {
        FileWriter fileWriter = new FileWriter(data, false);
        for (Task task : tasks) {
            if (task instanceof Todo) {
                fileWriter.write("T");
            } else if (task instanceof Deadline) {
                fileWriter.write("D");
            } else if (task instanceof Event) {
                fileWriter.write("E");
            } else {
                throw new DukeException("Unknown task type");
            }
            fileWriter.write("|");
            if (Objects.equals(task.getStatusIcon(), "[X]")) {
                fileWriter.write(Integer.toString(1));
            } else if (Objects.equals(task.getStatusIcon(), "[ ]")) {
                fileWriter.write(Integer.toString(0));
            } else {
                throw new DukeException("Unknown task isDone status");
            }
            fileWriter.write("|");
            if (task instanceof Todo) {
                fileWriter.write(task.getDescription());
            } else if (task instanceof Deadline) {
                fileWriter.write(task.getDescription());
                fileWriter.write(" /by ");
                fileWriter.write(((Deadline) task).getBy());
            } else {
                fileWriter.write(task.getDescription());
                fileWriter.write(" /from ");
                fileWriter.write(((Event) task).getStartTime());
                fileWriter.write(" /to ");
                fileWriter.write(((Event) task).getEndTime());
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}
