import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Task;
import duke.Todo;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    //protected Ui ui = new Ui();
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void printAddedTask(Task addedTask, int numOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + addedTask);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void printTaskList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void printMarkedTask(int taskNumber) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks.get(taskNumber));
        System.out.println("    ____________________________________________________________");
    }

    public void printUnmarkedTask(int taskNumber) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + tasks.get(taskNumber));
        System.out.println("    ____________________________________________________________");
    }

    public void printDeletedTask(Task deletedTask, int numOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + deletedTask);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void addTodoTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo newTodoTask = new Todo(description);
        tasks.add(newTodoTask);
        printAddedTask(newTodoTask, tasks.size());
    }

    public void addDeadlineTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        int firstSlashIndex = description.indexOf("/");
        int detailsEndIndex = firstSlashIndex - 1;
        String details = description.substring(0, detailsEndIndex);
        int byStartIndex = firstSlashIndex + 4;
        String by = description.substring(byStartIndex);
        Deadline newDeadlineTask = new Deadline(details, by);
        tasks.add(newDeadlineTask);
        printAddedTask(newDeadlineTask, tasks.size());
    }

    public void addEventTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException("     ☹ OOPS!!! The description of a event cannot be empty.");
        }
        int firstSlashIndex = description.indexOf("/");
        int secondSlashIndex = description.indexOf("/", firstSlashIndex + 1);
        int detailsEndIndex = firstSlashIndex - 1;
        String details = description.substring(0, detailsEndIndex);
        int fromStartIndex = firstSlashIndex + 6;
        int fromEndIndex = secondSlashIndex - 1;
        String from = description.substring(fromStartIndex, fromEndIndex);
        int toStartIndex = secondSlashIndex + 4;
        String to = description.substring(toStartIndex);
        Event newEventTask = new Event(details, from, to);
        tasks.add(newEventTask);
        printAddedTask(newEventTask, tasks.size());
    }

    public void deleteTask(int taskNumber) {
        int index = taskNumber - 1;
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        printDeletedTask(deletedTask, tasks.size());
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public int getSize() {
        return tasks.size();
    }

}
