package duke.entity;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used to deal with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load or creates the data file into an array list of task
     *
     * @return loaded array list of task
     * @throws DukeException if data will not be saved
     */
    public ArrayList<Task> load() throws DukeException {
        String[] filePathArr = filePath.split("/");
        ArrayList<Task> tasks = new ArrayList<Task>();
        String tempPath = filePathArr[0];

        for (int i = 0; i < filePathArr.length; i++) {
            if (i != 0) {
                tempPath = tempPath + "/" + filePathArr[i];
            }
            java.nio.file.Path filePath =  getPath(tempPath);
            boolean pathExists = java.nio.file.Files.exists(filePath);
            File pathFile = new File(filePath.toString());

            try {
                if (i == (filePathArr.length - 1) && pathExists) {
                    readDukeFile(tasks, pathFile);
                } else if (!pathExists) {
                    if (i == (filePathArr.length - 1)) {
                        pathFile.createNewFile();
                    } else {
                        pathFile.mkdir();
                    }
                }
            } catch (IOException e) {
                throw new DukeException("Note: data will not be saved (Duke.txt unable to be created)");
            }
        }
        return tasks;
    }

    public void updateDukeFile(TaskList taskList) {
        writeDukeFile(taskList.getTask(0), false);
        for (int i = 1; i < taskList.taskSize(); i++) {
            writeDukeFile(taskList.getTask(i), true);
        }
    }

    public void writeDukeFile(Task task, boolean appendFile) {
        String line = (task.getIsDone() ? "1" : "0") + "|" + task.getDescription();
        if (task instanceof Todo) {
            line = "T|" + line;
        } else if (task instanceof Deadline) {
            line = "D|" + line;
        } else if (task instanceof Event) {
            line = "E|" + line;
        }
        try {
            File dukeFile = new File(getPath(this.filePath).toString());
            FileWriter fw = new FileWriter(dukeFile, appendFile);
            if (appendFile) {
                fw.write("\n" + line);
            } else {
                fw.write(line);
            }
            fw.close();
        } catch (IOException ex) {}
    }

    private void readDukeFile(ArrayList<Task> tasks, File dukeFile) throws FileNotFoundException {
        Scanner s = new Scanner(dukeFile);
        while (s.hasNextLine()) {
            String task = s.nextLine();
            if (task.length() > 4) {
                String taskType = task.substring(0, 1);
                boolean isMarked = task.substring(2, 3).equals("0") ? false : true;
                String taskDesc = task.substring(4);

                if (taskType.equals("T")) {
                    Task tempTask = new Todo(taskDesc, isMarked);
                    tasks.add(tempTask);
                } else if (taskType.equals("D")) {
                    Task tempTask = new Deadline(taskDesc, taskDesc.substring(taskDesc.lastIndexOf("/") + 1), isMarked);
                    tasks.add(tempTask);
                } else if (taskType.equals("E")) {
                    String tempInput = taskDesc.substring(taskDesc.indexOf("/") + 1);
                    String fromString = tempInput.substring(0, tempInput.indexOf("/"));
                    String toString = tempInput.substring(tempInput.lastIndexOf("/") + 1);

                    Task tempTask = new Event(taskDesc, fromString, toString, isMarked);
                    tasks.add(tempTask);
                }
            }
        }
    }

    private java.nio.file.Path getPath(String path) {
        File f = new File("");
        String home = f.getAbsolutePath();
        return java.nio.file.Paths.get(home, path);
    }
}
