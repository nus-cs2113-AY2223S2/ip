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
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Write the current List of Task to filepath in the following format:
     * [task type]|[task status]|[task description]|[task time]
     *
     * @param list the current List of Task
     * @throws  DukeException If there is Input or Output Error.
     */
    public void save(List<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : list) {
                String taskInformation = t.getTask();
                String taskType = taskInformation.substring(1, 2);
                String taskStatus = (taskInformation.charAt(4) == 'V' ? "V" : "X");
                String taskDescription = taskInformation.substring(7);
                if (taskDescription.contains("(by:")) {
                    String taskTime = taskDescription.substring(taskDescription.indexOf("by: ") + 5,
                            taskDescription.indexOf(")", taskDescription.indexOf("(by: ")));
                    taskDescription = taskDescription.substring(0, taskDescription.indexOf("(by:") - 1);
                    fw.write(taskType + "|" + taskStatus + "|" + taskDescription + "|" + taskTime);
                } else if (taskDescription.contains("(at:")) {
                    String taskTime = taskDescription.substring(taskDescription.indexOf("(at: ") + 5,
                            taskDescription.indexOf(")", taskDescription.indexOf("(at: ")));
                    taskDescription = taskDescription.substring(0, taskDescription.indexOf("(at:") - 1);
                    fw.write(taskType + "|" + taskStatus + "|" + taskDescription + "|" + taskTime);
                } else {
                    fw.write(taskType + "|" + taskStatus + "|" + taskDescription);
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IO Error [" + e + "]");
        }
    }

    /**
     * Returns List of Task object read in the given file path. Load the previous inputted tasks
     *
     * @return List of Task.
     * @throws DukeException If file is not found in the file path.
     */
    public List<Task> load() throws DukeException {
        List<Task> list = new ArrayList<>();
        try {
            File data = new File(filePath);

            if (! data.exists()){
                data.getParentFile().mkdirs();
            }
            Scanner dataEntry = new Scanner(data);
            while (dataEntry.hasNext()) {
                String[] parsedData = dataEntry.nextLine().split("|",4);
                String type = parsedData[0];
                String description = parsedData[2];
                if (type.equalsIgnoreCase("T")) {
                    list.add(new Todo(description));
                } else if (type.equalsIgnoreCase("D")) {
                    String date = parsedData[3];
                    list.add(new Deadline(description, date));
                } else if (type.equalsIgnoreCase("E")) {
                    String date = parsedData[3];
                    list.add(new Event(description, date));
                } else {
                    System.out.println("Format Error!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found [" + e + "]");
        }
        return list;
    }
}