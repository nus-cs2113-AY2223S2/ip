package app.parser;

import app.exceptions.DukeException;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

public class StringParser {
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
