package Duke;

import java.util.ArrayList;

public class DukeSortInputTask {
    /**
     * Determines which task constructor to send description of new task to
     * 
     * @param list ArrayList of tasks
     * @param taskLength Number of elements in this ArrayList of tasks
     * @param taskName description of task from user input
     * @throws DukeExceptions
     */
    public static void dukeSortInputTask(ArrayList<DukeTasks> list, Integer taskLength, String taskName) throws DukeExceptions {
        String[] taskType = taskName.split(" ", 2);
        String[] taskDescription;

        // check for task description
        if (taskType[1].isEmpty()) {
            throw new DukeExceptions("Your task does not seem to have a description");
        }
        
        if (taskType[0].equals("todo")) {
            list.add(new DukeToDos(taskType[1]));
        } else if (taskType[0].equals("deadline")) {
            if (!taskType[1].contains("/by")) {
                throw new DukeExceptions("Did you perhaps forget to set a deadline for the task?");
            }
            taskDescription = taskType[1].split("/by", 2);
            list.add(new DukeDeadlines(taskDescription[0], taskDescription[1]));
        } else if (taskType[0].equals("event")) {
            if (!taskType[1].contains("/from")) {
                throw new DukeExceptions("Did you perhaps forget to specify the duration for the event?");
            }
            taskDescription = taskType[1].split("/from", 2);
            list.add(new DukeEvents(taskDescription[0], taskDescription[1]));
        } else {
            throw new DukeExceptions("Huh? I did not quite catch that");
        }
    }
}
