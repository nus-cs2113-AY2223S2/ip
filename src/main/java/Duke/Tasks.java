package Duke;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents the list of tasks saved in Duke.
 * Keeps an LinkedList of all tasks.
 * Responsible for operations done on the tasks, such as adding, deleting, and searching for keyword.
 */

public class Tasks {
    private LinkedList<Task> taskList;

    private Tasks() {
        this.taskList = new LinkedList<Task>();
    }
    public static Tasks loadTasks() {
        Tasks newTasks = new Tasks();
        newTasks.taskList = TaskSaver.loadTasks();
        return newTasks;
    }

    /**
     * Adds new task to the list.
     *
     * @param commandByWord Contains information on task.
     * @return String command that is to be shown to the user that indicates successful insertion or any error.
     */
    public String addTask(String[] commandByWord) {
        String type = commandByWord[0];
        try {
            Task task = TaskCreator.createNewTask(commandByWord);
            taskList.add(task);
            TaskSaver.addTask(task);
            return "Got it. I've added this task:\n    " +
                    task + "\n" +
                    "  Now you have " + taskList.size() + " tasks in the list.";

        } catch (IllegalArgumentException e) {
            return "☹ OOPS!!! The description of a " + type + " cannot be empty.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "☹ OOPS!!! The date information of the " + type + " is invalid!" +
                    "\n  Use the command /by for deadlines, and /from, /to for events!" +
                    "\n  Please try again.";
        }
    }

    private void addTaskClass(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes task from the list.
     *
     * @param commandByWord Contains information on task.
     * @return String command that is to be shown to the user that indicates successful deletion or any error.
     */
    public String deleteTask(String[] commandByWord) {
        try {
            if (commandByWord.length != 2) {
                return "☹ OOPS!!! The delete command is invalid\n" +
                        "  Please use the format \"delete {task number}\"";
            }
            int index = Integer.parseInt(commandByWord[1]) - 1;
            Task removedTask = taskList.get(index);
            taskList.remove(index);
            TaskSaver.updateTask(this.toStringList());
            return "Noted. I've removed this task:\n    " +
                    removedTask.toString() + "\n  " +
                    "Now you have " + taskList.size() + " tasks in the list.";

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "☹ OOPS!!! The task number is invalid!" +
                    "\n  Please try again.";
        }
    }

    /**
     * Marks specific task in the list as complete.
     *
     * @param index index of the task in the list that is to be marked as complete.
     * @return String command that is to be shown to the user that indicates successful marking or any error.
     */
    public String mark(int index) {
        try {
            Task newTask = taskList.get(index - 1).markAsComplete();
            this.taskList.set(index - 1, newTask);
            TaskSaver.updateTask(this.toStringList());
            return "Nice! I've marked this task as done:\n  "
                    + taskList.get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "☹ OOPS!!! The task number is invalid!" +
                    "\n  Please try again.";
        }
    }
    /**
     * Marks specific task in the list as incomplete.
     *
     * @param index index of the task in the list that is to be marked as incomplete.
     * @return String command that is to be shown to the user that indicates successful unmarking or any error.
     */
    public String unmark(int index) {
        try {
            Task newTask = taskList.get(index - 1).markAsIncomplete();
            this.taskList.set(index - 1, newTask);
            TaskSaver.updateTask(this.toStringList());
            return "OK, I've marked this task as not done yet:\n  "
                    + taskList.get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "☹ OOPS!!! The task number is invalid!" +
                    "\n  Please try again.";
        }
    }

    /**
     * Lists tasks stored in the task list in string format, which is shown to the user.
     *
     * @return String of all tasks saved, tasks written line by line.
     */
    public String listTasks(String type) {
        String list = (type.equals("list")) ? "Here are the tasks in your list:\n"
                : "Here are the matching tasks in your list:\n";

        int counter = 1;
        for (Task task : taskList) {
            list += "  " + Integer.toString(counter) + ". ";
            list += task;
            list += "\n";
            ++counter;
        }
        return list;
    }

    /**
     * Returns string form of tasks that contain the keyword given by the user.
     * Iterates through all tasks within the taskList to find matching task.
     *
     * @param commandByWord String array containing command information.
     * @return String form of list of tasks that contain the keyword.
     */

    public String findTask(String[] commandByWord) {
        if (commandByWord.length > 2) return "☹ OOPS!!! Please enter a single keyword\n";
        Tasks tasksWithKeyword = new Tasks();
        for (Task task : this.taskList) {
            if (task.contains(commandByWord[1])) {
                tasksWithKeyword.addTaskClass(task);
            }
        }
        return tasksWithKeyword.listTasks("find");
    };

    /**
     * Converts the list of tasks into string format.
     *
     * @return String form of all tasks in the list.
     */
    private String toStringList() {
        String TaskListAsString = "";
        for (Task task : taskList) {
            TaskListAsString += TaskToStringConverter.convertTaskToCommandString(task) + "\n";
        }
        return TaskListAsString;
    }
}
