package FileUtils;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import Entities.Deadline;
import Entities.Event;
import Entities.Task;
import Entities.TaskList;
import Entities.Todo;
import EntityUtils.DateParser;
import Exceptions.DukeException;


public class Storage {
    private static final String readDelimiter = " \\| ";
    private static final String writeDelimiter = " | ";
    private File taskFile;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner s = new Scanner(taskFile);
            Task currentTask;
            ArrayList<Task> savedTasks = new ArrayList<Task>();

            while (s.hasNext()) {
                currentTask = parseLine(s.nextLine());
                if (currentTask != null) {
                    savedTasks.add(currentTask);
                }
            }
            s.close();

            return savedTasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File does not exist!");
        }
    }

    public void write(TaskList tasks) throws DukeException {
        FileWriter fw;
        String taskString;

        try {
            if (!taskFile.getParentFile().exists()) {
                taskFile.getParentFile().mkdirs();
                taskFile.createNewFile();
            }
            fw = new FileWriter(taskFile);
    
            for (Task t : tasks.getTasks()) {
                taskString = stringfyTask(t);
                fw.write(taskString);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IO Exception occurred when writing to file");
        }
    }

    private static Task parseLine(String line) throws DukeException {
        String[] taskInformation = line.split(readDelimiter);
        Task task = null;
        String taskType = taskInformation[0];
        boolean isDone = taskInformation[1].equals("1") ? true : false;
        String taskDescription = taskInformation[2];
        LocalDateTime startDate, endDate;

        switch (taskType) {
            case "T":
                task = new Todo(taskDescription, isDone);
                break;
            case "D":
                endDate = DateParser.stringToDate(taskInformation[3]);
                task = new Deadline(taskDescription, isDone, endDate);
                break;
            case "E":
                startDate = DateParser.stringToDate(taskInformation[3]);
                endDate = DateParser.stringToDate(taskInformation[4]);
                task = new Event(taskDescription, isDone, startDate, endDate);
                break;
            default:
                throw new DukeException("Error parsing file!");
        }

        return task;
    }

    private static String stringfyTask(Task t) throws DukeException {
        String taskString;
        String taskType, startDate, endDate;
        String isDone = t.isDone() ? "1" : "0";
        String taskDescription = t.getTaskDescription();

        if (t instanceof Todo) {
            taskType = TaskTypes.TODO.toString();
            taskString = String.join(writeDelimiter, taskType, isDone, taskDescription);
        } else if (t instanceof Deadline) {
            Deadline deadline = (Deadline) t;
            taskType = TaskTypes.DEADLINE.toString();
            endDate = deadline.getEndDate();
            taskString = String.join(writeDelimiter, taskType, isDone, taskDescription, endDate);
        } else if (t instanceof Event) {
            Event event = (Event) t;
            taskType = TaskTypes.EVENT.toString();
            startDate = event.getStartDate();
            endDate = event.getEndDate();
            taskString = String.join(writeDelimiter, taskType, isDone, taskDescription, startDate, endDate);
        } else {
            throw new DukeException("Line is corrupted!");
        }

        return taskString + "\n";
    }
}
