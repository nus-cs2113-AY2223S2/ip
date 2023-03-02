package duke;

import duke.commands.Todo;
import duke.commands.Task;
import duke.commands.Deadline;
import duke.commands.Event;

import duke.commands.Datetime;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int listSize;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
        this.listSize = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
        this.listSize = tasks.size();
    }

    public int getSize() {
        return listSize;
    }

    public String getDescription(int index) {
        return taskList.get(index).toString();
    }

    public void markThisTask(int index) {
        taskList.get(index).markAsDone();
    }

    public void unMarkThisTask(int index) {
        taskList.get(index).unmark();
    }

    public String getTask(int index) {
        return taskList.get(index).toString();
    }

    public void deleteThisTask(int index) {
        taskList.remove(index);
        listSize--;
    }

    public void addToDo(String description) {
        Todo newtodo = new Todo(description);
        taskList.add(newtodo);
        listSize++;
    }

    public void addTaskWithTime(String description, Datetime dt, String tasktype) {
        if (tasktype.equals("deadline")) {
            Deadline newddl = new Deadline(description, dt);
            taskList.add(newddl);
        } else {
            Event newevent = new Event(description, dt);
            taskList.add(newevent);
        }
        listSize++;
    }

    public String latesttask() {
        return taskList.get(listSize - 1).toString();
    }

    public ArrayList<Task> fullList() {
        return this.taskList;
    }
}
