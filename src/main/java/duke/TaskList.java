package duke;

import duke.exception.EmptyTaskException;
import duke.exception.IllegalCommandException;
import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public static int getSize() {
        return allTasks.size();
    }

    protected static void markDone(int idx) {
        allTasks.get(idx).setDone(true);
    }

    protected static void markNotDone(int idx) {
        allTasks.get(idx).setDone(false);
    }

    protected static void deleteTask(int idx) {
        allTasks.remove(idx);
    }

    protected static void addToDo(String param) {
        allTasks.add(new ToDo(param));
    }

    protected static void addDeadline(String param, String by) {
        allTasks.add(new Deadline(param, by));
    }

    protected static void addEvent(String param, String from, String to) {
        allTasks.add(new Event(param, from, to));
    }
}
