package Duke;

import java.util.LinkedList;


public class Tasks {
    private final LinkedList<Task> taskList;

    Tasks() {
        this.taskList = TaskSaver.loadTasks();
    }

    String addTask(String[] commandByWord) {
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

    String deleteTask(String[] commandByWord) {
        try {
            if (commandByWord.length != 2) {
                return "☹ OOPS!!! The delete command is invalid\n" +
                        "  Please use the format \"delete {task number}\"";
            } else {
                int index = Integer.parseInt(commandByWord[1]) - 1;
                Task removedTask = taskList.get(index);
                taskList.remove(index);
                TaskSaver.deleteTask(removedTask);
                return "Noted. I've removed this task:\n    " +
                        removedTask.toString() + "\n  " +
                        "Now you have " + taskList.size() + " tasks in the list.";
            }

        } catch(IndexOutOfBoundsException | NumberFormatException e) {
            return "☹ OOPS!!! The task number is invalid!" +
                    "\n  Please try again.";
        }
    }

    String mark(int index) {
        taskList.get(index - 1).markAsComplete();
        return "Nice! I've marked this task as done:\n  " + taskList.get(index - 1).toString();
    }

    String unmark(int index) {
        taskList.get(index - 1).markAsIncomplete();
        return "OK, I've marked this task as not done yet:\n  " + taskList.get(index - 1).toString();
    }

    String listTasks() {

        String list = "Here are the tasks in your list:\n";
        int counter = 1;
        for (Task task : taskList) {
            list += "  " + Integer.toString(counter) + ". ";
            list += task;
            list += "\n";
            ++counter;
        }
        return list;
    }
}
