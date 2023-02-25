package app.parser;

import app.exceptions.DukeException;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

public class TaskParser {
    public static String convertTaskToString(Task task) throws DukeException {
        String outputString;
        String delimiter = " | ";
        String taskStatus = task.isDone() ? "1" : "0";
        String taskDescription = task.getTaskDescription();

        if (task instanceof ToDo) {
            String taskType = "T";
            outputString = String.join(delimiter, taskType, taskStatus, taskDescription);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String taskType = "D";
            String endTime = deadline.getDeadline();
            outputString = String.join(delimiter, taskType, taskStatus, taskDescription, endTime);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            String taskType = "E";
            String startTime = event.getStartTime();
            String endTime = event.getEndTime();
            outputString = String.join(delimiter, taskType, taskStatus, taskDescription, startTime, endTime);
        } else {
            throw new DukeException("ONO! There was an error saving your task list.");
        }
        return outputString + "\n";
    }
}
