package tasks;

import constants.Constant;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * prints number of tasks currently in the list.
     */
    public void printNumberOfTasks() {
        int numberOfTasks = tasks.size();
        System.out.println(Constant.TAB_SPACE + "Now you have " + numberOfTasks +
                " tasks in the list.");
    }

    /**
     * Add tasks to the tasks list.
     *
     * @param task the task that we want to add to the list
     */
    public void addTask (Task task, String command) {
        tasks.add(task);
        printAcknowledgeCommand(task, command);
        printNumberOfTasks();
    }

    /**
     * Acknowledges when the commands "add", "mark", "unmark",
     * "list" are entered.
     *
     * @param task task that has been added to the tasks list
     * @param command the command entered by user
     */
    public void printAcknowledgeCommand(Task task, String command) {
        switch (command) {
        case "todo":
        case "deadline":
        case "event":
            System.out.println(Constant.TAB_SPACE +
                    "Got it. I've added this task:");
            break;
        case "mark":
            System.out.println(Constant.TAB_SPACE +
                    "Great! I've marked this task as done:");
            break;
        case "unmark":
            System.out.println(Constant.TAB_SPACE +
                    "OK, I've marked this task as not done yet:");
            break;
        }

        System.out.println(Constant.TAB_SPACE + Constant.TAB_SPACE + task);
    }

    /**
     * Lists the tasks in the tasks list.
     */
    public void listTasks () {
        System.out.println(Constant.TAB_SPACE +
                "Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.printf(Constant.TAB_SPACE + Constant.TAB_SPACE +
                    "%d.%s\n", index, task);
            index++;
        }
    }

    /**
     * marks the task indicated by the
     * taskNumber as done.
     *
     * @param taskNumber the task number that we want to mark as completed
     */
    public void markTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.setDone(true);
        printAcknowledgeCommand(task, "mark");
    }

    /**
     * marks the task indicate by
     * task as not done.
     *
     * @param taskNumber the task number that we want to mark as incomplete again.
     */
    public void unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.setDone(false);
        printAcknowledgeCommand(task, "unmark");
    }
}
