package app.parser;

import app.exceptions.DukeException;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

/**
 * Used to convert existing tasks in task-list to text for storage in text file.
 */
public class TaskParser {

    /**
     * Method used to convert a particular task into a string with the correct format.
     * @param task The Task to be represented as a string.
     * @return A string containing all the attributes of the Task.
     * @throws DukeException If error occurs when processing a Task in the Task-list.
     */
    public static String convertTaskToString(Task task) throws DukeException {
        String outputString;
        String delimiter = " | ";
        String taskStatus = (task.getStatusIcon() == "X" ? "1" : "0");
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
