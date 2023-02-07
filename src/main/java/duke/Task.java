package duke;

import java.util.ArrayList;

public class Task {
    protected String details;
    protected boolean isDone;

    public Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    public String getDetails() {
        return this.details;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.details;
    }

    public static void printAddedTask(Task addedTask, int numOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + addedTask);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printMarkedTask(int taskNumber, ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + taskList.get(taskNumber));
        System.out.println("    ____________________________________________________________");
    }

    public static void printUnmarkedTask(int taskNumber, ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + taskList.get(taskNumber));
        System.out.println("    ____________________________________________________________");
    }

    public static void addTodoTask(String description, ArrayList<Task> taskList) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo newTodoTask = new Todo(description);
        taskList.add(newTodoTask);
        Task.printAddedTask(newTodoTask, taskList.size());
    }

    public static void addDeadlineTask(String description, ArrayList<Task> taskList) throws DukeException {
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
        taskList.add(newDeadlineTask);
        Task.printAddedTask(newDeadlineTask, taskList.size());
    }

    public static void addEventTask(String description, ArrayList<Task> taskList) throws DukeException {
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
        taskList.add(newEventTask);
        Task.printAddedTask(newEventTask, taskList.size());
    }
}
