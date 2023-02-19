package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidIndexException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


import java.util.ArrayList;


public class TaskList implements java.io.Serializable {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public  int getNumTasks() {
        return tasks.size();
    }

    public  Task getLatestTask() {
        return tasks.get(tasks.size() - 1);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }


    public void addToDo(String taskName) {
        tasks.add(new Todo(taskName));
    }

    public void addDeadline(String taskDetails) throws InvalidCommandException {
        int byIndex = taskDetails.indexOf(" /by ");
        if (byIndex == -1) {
            throw new InvalidCommandException();
        }
        String taskName = taskDetails.substring(0, byIndex);
        String dueDate = taskDetails.substring(byIndex + 5); // rest of string after " /by "
        tasks.add(new Deadline(taskName, dueDate));
    }

    public void addEvent(String taskDetails) throws InvalidCommandException {
        int fromIndex = taskDetails.indexOf(" /from ");
        int toIndex = taskDetails.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new InvalidCommandException();
        }
        String taskName = taskDetails.substring(0, fromIndex);
        String startTime = taskDetails.substring(fromIndex + 7, toIndex);
        String endTime = taskDetails.substring(toIndex + 5);
        tasks.add(new Event(taskName, startTime, endTime));
    }

    public Task deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        return deletedTask;
    }


}
