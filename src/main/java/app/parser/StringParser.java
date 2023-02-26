package app.parser;

import app.exceptions.DukeException;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

/**
 * Used to load existing tasks into a Task-list when Duke is first run.
 */
public class StringParser {
    /**
     * Method used to reconstruct a task from a given line in the text file.
     * @param line The string containing all the relevant information about a task.
     * @return A new task to be added to a task-list.
     * @throws DukeException If there was an error in processing string and returning task.
     */
    public static Task convertStringToTask(String line) throws DukeException {
        Task task;
        String delimiter = "\\|";
        String[] taskDetails = line.split(delimiter);
        String taskType = taskDetails[0].trim();
        boolean isDone = taskDetails[1].trim().equals("1");
        String taskDescription = taskDetails[2].trim();
        String startTime, endTime;

        switch (taskType) {
        case "T":
            task = new ToDo(taskDescription, isDone);
            break;
        case "D":
            endTime = taskDetails[3].trim();
            task = new Deadline(taskDescription, isDone, endTime);
            break;
        case "E":
            startTime = taskDetails[3].trim();
            endTime = taskDetails[4].trim();
            task = new Event(taskDescription, isDone, startTime, endTime);
            break;
        default:
            throw new DukeException("ONO! There was an error loading your task list.");
        }
        return task;
    }
}
