package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.ArrayList;


public class ReadFromFile {

    // Copying text file contents over to tasks list
    public static void copyToArrayList(String line, ArrayList<Task> tasks, String filePath) {
        switch (line.substring(0, 1)) {
        case "T":
            copyTodoToArrayList("T", line.substring(2), tasks);
            break;
        case "D":
            copyDeadlineToArrayList("D", line.substring(2), tasks);
            break;
        case "E":
            copyEventToArrayList("E", line.substring(2), tasks);
            break;
        case "S":
            // Saved data text on the first line (initialisation step)
            break;
        default:
            System.out.println("Unknown task type detected...");
            System.out.println("Skipping task...");
        }
    }

    //Does creation of ToDo and copying to tasks
    private static void copyTodoToArrayList(String taskType, String taskInfo, ArrayList<Task> tasks) {
        // Parse line to split task status and task info
        String[] messageComponents = taskInfo.split("/", 2);
        // Create new todo with task info
        ToDo newToDo = new ToDo(messageComponents[1]);
        // Store todo in list of tasks
        tasks.add(newToDo);
        int currentTaskIndex = tasks.size() - 1;
        // Mark task as done if task status was stored as 1
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.markAsDone();
        }
    }

    private static void copyDeadlineToArrayList(String taskType, String taskInfo, ArrayList<Task> tasks) {
        String[] messageComponents = taskInfo.split("/", 3);
        Deadline newDeadline = new Deadline(messageComponents[1], messageComponents[2]);
        tasks.add(newDeadline);
        int currentTaskIndex = tasks.size() - 1;
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.markAsDone();
        }
    }

    private static void copyEventToArrayList(String taskType, String taskInfo, ArrayList<Task> tasks) {
        String[] messageComponents = taskInfo.split("/", 4);
        Event newEvent = new Event(messageComponents[1], messageComponents[2], messageComponents[3]);
        tasks.add(newEvent);
        int currentTaskIndex = tasks.size() - 1;
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.markAsDone();
        }
    }
}
