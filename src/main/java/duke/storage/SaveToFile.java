package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToFile {

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


    private static void writeTasksToFile(FileWriter fwAppend, ArrayList<Task> tasks) throws IOException {
        int totalNumberOfTasks = tasks.size();
        for (int index = 0; index < totalNumberOfTasks; index += 1) {
            Task currentTask = tasks.get(index);
            String taskType = currentTask.getTaskType().substring(1, 2);
            String taskStatus = currentTask.getStatus();
            int isTaskDone = taskStatus.substring(1, 2).equals("X") ? 1 : 0;
            String taskInfo = currentTask.getTaskInfo();
            writeSpecificTaskToFile(fwAppend, taskType, currentTask, taskStatus, isTaskDone, taskInfo);
        }
    }

    private static void writeSpecificTaskToFile(FileWriter fwAppend, String taskType, Task currentTask,
                                                String taskStatus, int isTaskDone, String taskInfo) throws IOException {
        String additionalTaskInfo = "";
        switch (taskType) {
        case "T":
            fwAppend.write(taskType + "/" + isTaskDone + "/" + taskInfo + System.lineSeparator());
            break;
        case "D":
            Deadline currentDeadline = (Deadline) currentTask;
            additionalTaskInfo = currentDeadline.getDueInfo();
            String deadlineDetails = taskInfo + "/" + additionalTaskInfo;
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
