package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains the methods needed for the tasks stored in the ArrayList to be copied over to the text file
 * so that when the user exits the programme, the tasks are still saved.
 */
public class SaveToFile {

    /**
     * Overwrites all the tasks in the existing file specified by filePath. If there is no existing file, a new file
     * is created.
     * This is done by writing "Saved tasks: " followed by a newline.
     *
     * @param tasks    ArrayList to store Task objects
     * @param filePath Location of the text file that is used to store the tasks
     */
    public static void initialiseWritingToFile(ArrayList<Task> tasks, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("Saved tasks: " + System.lineSeparator());
            fw.close();
            FileWriter fwAppend = new FileWriter(filePath, true);
            writeTasksToFile(fwAppend, tasks);
            fwAppend.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Iterates through the ArrayList and determines the task type, status as well as the task information.
     * Passes these arguments to writeSpecificTaskToFile method.
     * taskType refers to either ToDo, Deadline or Event.
     * taskStatus refers to whether the task is completed or not.
     * isTaskDone is an integer to represent whether a task is completed or incomplete
     *
     * @param fwAppend object to allow writing to file
     * @param tasks    ArrayList to store Task objects
     * @throws IOException if file is not found
     */
    private static void writeTasksToFile(FileWriter fwAppend, ArrayList<Task> tasks) throws IOException {
        int totalNumberOfTasks = tasks.size();
        for (int index = 0; index < totalNumberOfTasks; index += 1) {
            Task currentTask = tasks.get(index);
            String taskType = currentTask.getTaskType().substring(1, 2);
            String taskStatus = currentTask.getStatus();
            int isTaskDone = taskStatus.substring(1, 2).equals("X") ? 1 : 0;
            String taskInfo = currentTask.getTaskInfo();
            writeSpecificTaskToFile(fwAppend, taskType, currentTask, isTaskDone, taskInfo);
        }
    }

    /**
     * Based on the specific task type, this function formats the task info.
     * Since todo, deadline and event task types have different parameters this function formats the task info
     * according to their respective parameters to the text file for storage.
     *
     * @param fwAppend    object to allow lines to be appended to a text file
     * @param taskType    Todo, deadline or event
     * @param currentTask Task from the ArrayList at a specified index
     * @param isTaskDone  Indicates whether the task is completed or not
     * @param taskInfo    Task description
     * @throws IOException If fwAppend.write encounters an error
     */
    private static void writeSpecificTaskToFile(FileWriter fwAppend, String taskType, Task currentTask,
                                                int isTaskDone, String taskInfo) throws IOException {
        switch (taskType) {
        case "T":
            fwAppend.write(taskType + "/" + isTaskDone + "/" + taskInfo + System.lineSeparator());
            break;
        case "D":
            Deadline currentDeadline = (Deadline) currentTask;
            String dueInfo = currentDeadline.getDueInfo();
            String deadlineDetails = taskInfo + "/" + dueInfo;
            fwAppend.write(taskType + "/" + isTaskDone + "/" + deadlineDetails + System.lineSeparator());
            break;
        case "E":
            Event currentEvent = (Event) currentTask;
            String eventStart = currentEvent.getEventStartInfo();
            String eventEnd = currentEvent.getEventEndInfo();
            String eventDetails = taskInfo + "/" + eventStart + "/" + eventEnd;
            fwAppend.write(taskType + "/" + isTaskDone + "/" + eventDetails + System.lineSeparator());
            break;
        default:
            System.out.println("Unknown task type error!");
        }
    }
}
