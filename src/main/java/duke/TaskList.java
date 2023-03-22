package duke;

import duke.commands.task.Task;

/**
 * Taken from nus-cs2113-AY2223S2/personbook
 */

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTasks = new ArrayList<Task>();

    public void addTask(Task toAdd) {
        listOfTasks.add(toAdd);
    }

    public void deleteTask(int taskIndex) {
        listOfTasks.remove(taskIndex);
    }

    public Task getTask(int taskIndex) {
        return listOfTasks.get(taskIndex);
    }

    public int getSizeOfList() {
        return listOfTasks.size();
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
}
