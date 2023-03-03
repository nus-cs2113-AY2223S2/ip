package btb.tasks;

import btb.exceptions.EmptyTaskDescriptionException;
import btb.exceptions.EmptyTaskListException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public int getSize() {
        return tasks.size();
    }

    /**
     * prints number of tasks currently in the list.
     */
    public void printNumberOfTasks() {
        int numberOfTasks = tasks.size();
        System.out.println("\t Now you have " + numberOfTasks +
                " tasks in the list.");
    }

    /**
     * Add tasks to the tasks list.
     *
     * @param task the task that we want to add to the list
     */
    public void addTask(Task task, String command) throws EmptyTaskDescriptionException {
        if (task.description.equals("")) {
            throw new EmptyTaskDescriptionException();
        } else {
            tasks.add(task);
            printAcknowledgeCommand(task, command);
            printNumberOfTasks();
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Acknowledges when the commands "add", "mark", "unmark",
     * "list" are entered.
     *
     * @param task    task that has been added to the tasks list
     * @param command the command entered by user
     */
    public void printAcknowledgeCommand(Task task, String command) {
        switch (command) {
        case "todo":
        case "deadline":
        case "event":
            System.out.println("\t Got it. I've added this task:");
            break;
        case "mark":
            System.out.println("\t Great! I've marked this task as done:");
            break;
        case "unmark":
            System.out.println("\t OK, I've marked this task as not done yet:");
            break;
        case "delete":
            System.out.println("\t Noted, I've removed this task:");
            break;
        }

//        System.out.println(Constant.TAB_SPACE + Constant.TAB_SPACE + task);
        System.out.printf("\t\t%s\n", task);
    }

    /**
     * Lists the tasks in the tasks list.
     */
    public void listTasks() throws EmptyTaskListException {
        if (tasks.size() == 0) {
            throw new EmptyTaskListException();
        }

        System.out.println("\t Here are the tasks in your list:");

        int index = 1;
        for (Task task : tasks) {
            System.out.printf("\t\t%d.%s\n", index, task);
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
        tasks.set(taskNumber - 1, task);
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
        tasks.set(taskNumber - 1, task);
        printAcknowledgeCommand(task, "unmark");
    }

    /**
     * delete task from task list
     *
     * @param taskNumber the task number to be deleted.
     */
    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        printAcknowledgeCommand(task, "delete");
        printNumberOfTasks();
    }

    public void saveList(String filePath) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("Here are the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                fw.write(String.format("\t%d.%s\n", i+1, tasks.get(i)));
            }
        }
    }

    public void find(String toFind) throws EmptyTaskDescriptionException {
        if (toFind.equals("")) {
            throw new EmptyTaskDescriptionException();
        }

        int index = 1;
        System.out.println("\t Here are the matching tasks in your list:");
        boolean hasFound = false;

        for (Task task : tasks) {
            if (task.description.contains(toFind)) {
                System.out.printf("\t\t%d.%s\n", index, task);
                index++;
                hasFound = true;
            }
        }

        if (!hasFound) {
            System.out.println("\t\tOops..., no matches found");
        }
    }
}
