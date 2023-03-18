package Duke.string;

import Duke.task.DukeTask;
import Duke.task.DukeToDo;

import static Duke.string.Strings.STORAGE_NEXT;

import Duke.DukeException;
import Duke.task.DukeDeadline;
import Duke.task.DukeEvent;

public class StringToTask {

    /**
     * Converts a string to a task.
     *
     * @param savedTask The string to be converted.
     * @return The task.
     * @throws DukeException If the string is not in the correct format.
     */
    public static DukeTask stringToTask(String savedTask) throws DukeException {
        String[] savedTaskDetails = savedTask.split(STORAGE_NEXT);
        DukeTask task;
        if (savedTaskDetails[0].equals("T")) {
            task = new DukeToDo(savedTaskDetails[2]);
        } else if (savedTaskDetails[0].equals("D")) {
            task = new DukeDeadline(savedTaskDetails[2], savedTaskDetails[3]);
        } else if (savedTaskDetails[0].equals("E")) {
            task = new DukeEvent(savedTaskDetails[2], savedTaskDetails[3], savedTaskDetails[4]);
        } else {
            throw new DukeException("Invalid task type found in the save file.");
        }
        if (savedTaskDetails[1].equals("true")) {
            task.mark();
        }
        return task;
    }

}
