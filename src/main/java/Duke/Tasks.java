package Duke;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;


public class Tasks {
    private LinkedList<Task> taskList;

    Tasks() {
        this.taskList = new LinkedList<Task>();
    }
    static Tasks loadTasks() {
        Tasks newTasks = new Tasks();
        newTasks.taskList = TaskSaver.loadTasks();
        return newTasks;
    }

    String addTask(String[] commandByWord) {
        String type = commandByWord[0];
        try {
            Task task = TaskCreator.createNewTask(commandByWord);
            taskList.add(task);
            TaskSaver.addTask(taskList.size(), task);
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

    String deleteTask(String[] commandByWord) {
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

    String mark(int index) {
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

    String unmark(int index) {
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

    String listTasks(String type) {

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



    String findTask(String[] commandByWord) {
        if (commandByWord.length > 2) return "☹ OOPS!!! Please enter a single keyword\n";
        Tasks tasksWithKeyword = new Tasks();
        for (Task task : this.taskList) {
            if (task.contains(commandByWord[1])) {
                tasksWithKeyword.addTaskClass(task);
            }
        }
        return tasksWithKeyword.listTasks("find");
    };

    String toStringList() {
        String TaskListAsString = "";
        for (Task task : taskList) {
            TaskListAsString += TaskToStringConverter.convertTaskToCommandString(task) + "\n";
        }
        return TaskListAsString;
    }
}
