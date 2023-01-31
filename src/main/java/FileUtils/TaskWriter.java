package FileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import Entities.*;
import Exceptions.InvalidTaskException;

public class TaskWriter {
    private static final String delimiter = " | ";

    public static void writeTasksToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        FileWriter fw;
        String taskString;

        if (file.exists()) {
            fw = new FileWriter(file, true);
        } else {
            file.createNewFile();
            fw = new FileWriter(file);
        }

        for (Task t : tasks) {
            try {
                taskString = taskToString(t);
                fw.write(taskString);
            } catch (InvalidTaskException e) {
                System.out.println("Could not convert task to string!");
                System.out.printf("Task with issue: %s\n", t.toString());
            }
        }
        fw.close();
    }

    private static String taskToString(Task t) throws InvalidTaskException {
        String taskString;
        String taskType, startDate, endDate;
        String isDone = t.isDone() ? "1" : "0";
        String taskDescription = t.getTaskDescription();

        if (t instanceof Todo) {
            taskType = TaskTypes.TODO.toString();
            taskString = String.join(delimiter, taskType, isDone, taskDescription);
        } else if (t instanceof Deadline) {
            Deadline deadline = (Deadline) t;
            taskType = TaskTypes.DEADLINE.toString();
            endDate = deadline.getEndDate();
            taskString = String.join(delimiter, taskType, isDone, taskDescription, endDate);
        } else if (t instanceof Event) {
            Event event = (Event) t;
            taskType = TaskTypes.EVENT.toString();
            startDate = event.getStartDate();
            endDate = event.getEndDate();
            taskString = String.join(delimiter, taskType, isDone, taskDescription, startDate, endDate);
        } else {
            throw new InvalidTaskException();
        }

        return taskString + "\n";
    }
}