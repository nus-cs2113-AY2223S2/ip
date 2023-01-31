package FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Entities.*;
import Exceptions.InvalidTaskException;

public class TaskReader {
    private static final String delimiter = " \\| ";

    public static ArrayList<Task> readAndReturnTasks(String filePath) {
        ArrayList<Task> savedTasks = new ArrayList<Task>();
        Task currentTask;

        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                currentTask = lineToTask(s.nextLine());
                if (currentTask != null) {
                    savedTasks.add(currentTask);
                }
            }
            s.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist! Returning empty task list");
        }
        return savedTasks;
    }

    private static Task lineToTask(String line) {
        String[] taskInformation = line.split(delimiter);
        Task task = null;
        String taskType = taskInformation[0];
        boolean isDone = taskInformation[1].equals("1") ? true : false;
        String taskDescription = taskInformation[2];
        String startDate, endDate;

        try {
            switch (taskType) {
                case "T":
                    task = new Todo(taskDescription, isDone);
                    break;
                case "D":
                    endDate = taskInformation[3];
                    task = new Deadline(taskDescription, isDone, endDate);
                    break;
                case "E":
                    startDate = taskInformation[3];
                    endDate = taskInformation[4];
                    task = new Event(taskDescription, isDone, startDate, endDate);
                    break;
                default:
                    throw new InvalidTaskException();
            }
        } catch (InvalidTaskException e) {
            System.out.printf("%s is not a valid task! Database may be corrupted!\n", line);
        }

        return task;
    }
}
