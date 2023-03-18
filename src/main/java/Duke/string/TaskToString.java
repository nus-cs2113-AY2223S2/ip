package Duke.string;

import Duke.task.DukeTask;
import Duke.task.DukeToDo;

import static Duke.string.Strings.LINE_SEPARATOR;
import static Duke.string.Strings.STORAGE_NEXT;

import Duke.task.DukeDeadline;
import Duke.task.DukeEvent;

public class TaskToString {
    public static String taskToString(DukeTask task) {
        if (task instanceof DukeToDo) {
            return "T" + STORAGE_NEXT + task.getIsDone() + STORAGE_NEXT + task.getName()
                    + LINE_SEPARATOR;
        } else if (task instanceof DukeDeadline) {
            return "D" + STORAGE_NEXT + task.getIsDone() + STORAGE_NEXT + task.getName()
                    + STORAGE_NEXT + ((DukeDeadline) task).getBy() + LINE_SEPARATOR;
        } else if (task instanceof DukeEvent) {
            return "E" + STORAGE_NEXT + task.getIsDone() + STORAGE_NEXT + task.getName()
                    + STORAGE_NEXT + ((DukeEvent) task).getFrom() + ((DukeEvent) task).getTo()
                    + LINE_SEPARATOR;
        } else {
            return "";
        }
    }
}
