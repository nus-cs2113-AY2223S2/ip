package hina.helper;

import hina.task.Deadline;
import hina.task.Event;
import hina.task.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Specifies the date-time format to be used.
     */
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

    /**
     * Class constructor with a <code>taskList</code> initialised with the <code>Task</code> elements of
     * another <code>ArrayList</code>.
     *
     * @param savedList the list from which elements are initialised.
     */
    public TaskList(ArrayList<Task> savedList) {
        taskList = savedList;
    }

    /**
     * Returns an <code>ArrayList</code> containing the <code>Task</code> elements currently stored in
     * the <code>taskList</code>.
     *
     * @return the <code>ArrayList</code> of <code>Task</code> objects.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Prints line-by-line the data of the <code>Task</code> objects stored in the <code>taskList</code>.
     */
    public static void listTasks() {
        if (taskList.size() == 0) {
            Ui.emptyListMessage();
        }
        for (Task task : taskList) {
            int i = taskList.indexOf(task);
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(task.toString());
        }
    }

    /**
     * Prints the number of elements in the <code>taskList</code>.
     */
    public static void getTaskCount() {
        System.out.printf("There are %d items on your list.\n", taskList.size());
    }

    /**
     * Adds a <code>Task</code> object to the <code>taskList</code>.
     *
     * @param description the description of the <code>Task</code>.
     */
    public static void addTask(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);
        Ui.taskAdded(newTask);
        getTaskCount();
    }

    /**
     * Adds a <code>Deadline</code> object to the <code>taskList</code>.
     *
     * @param deadline a <code>String</code> specifying the description of the <code>Deadline</code> and
     *                 the do-by date and time.
     */
    public static void addDeadline(String deadline) {
        String[] details = deadline.split("/by");
        if (details.length < 2) {
            System.out.println("Hina needs to know the deadline for this task!");
        } else {
            try {
                LocalDateTime by = LocalDateTime.parse(details[1].trim(), formatter);
                Deadline newDeadline = new Deadline(details[0], by);
                taskList.add(newDeadline);
                Ui.taskAdded(newDeadline);
                getTaskCount();

            } catch (DateTimeException exception) {
                Ui.showDateTimeError();
            }
        }
    }

    /**
     * Adds an <code>Event</code> object to the <code>taskList</code>.
     *
     * @param event a <code>String</code> specifying the description of the <code>Event</code> and
     *              the start and end time.
     */
    public static void addEvent(String event) {
        String[] details = event.split("/from");
        if (details.length < 2) {
            System.out.println("Please tell Hina when this event starts!");
        } else {
            if (details[1].split("/to").length < 2) {
                System.out.println("Please tell Hina when this event ends!");
            } else {
                try {
                    LocalDateTime from = LocalDateTime.parse(details[1].split("/to")[0].trim(), formatter);
                    LocalDateTime to = LocalDateTime.parse(details[1].split("/to")[1].trim(), formatter);
                    Event newEvent = new Event(details[0], from, to);
                    taskList.add(newEvent);
                    Ui.taskAdded(newEvent);
                    getTaskCount();

                } catch (DateTimeException exception) {
                    Ui.showDateTimeError();
                }

            }
        }
    }
    public static void deleteTask(int taskIndex) {
        try {
            Task toDelete = taskList.get(taskIndex - 1);
            System.out.println("Got it! This task will be removed:");
            System.out.println(toDelete);
            taskList.remove(taskIndex - 1);
            getTaskCount();
        } catch (IndexOutOfBoundsException e) {
            Ui.invalidNumberMessage();
        }
    }
    public static void markTask(int taskIndex) {
        try {
            taskList.get(taskIndex - 1).setDone(true);
            System.out.println("Roger that! This task is marked as done: ");
            System.out.println(taskList.get(taskIndex - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            Ui.invalidNumberMessage();
        }
    }
    public static void unmarkTask(int taskIndex) {
        try {
            taskList.get(taskIndex - 1).setDone(false);
            System.out.println("Roger that! This task is marked as not done: ");
            System.out.println(taskList.get(taskIndex - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            Ui.invalidNumberMessage();
        }
    }

    /**
     * Determines, ignoring and trailing and leading whitespaces, if any of the
     * <code>Task</code> descriptions contain the exact query substring. Prints
     * the <code>Task</code> data of those that do and prints a message if none are
     * found.
     *
     * @param line the query substring.
     */
    public static void findTask(String line) {
        String query = line.substring(4).trim();
        boolean matchFound = false;
        ArrayList<String> matchList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(query)) {
                int i = taskList.indexOf(task);
                String match = String.format("%d. %s", i + 1, task);
                matchList.add(match);
                matchFound = true;
            }
        }
        if (matchFound) {
            Ui.taskFoundMessage();
            for (String task : matchList) {
                System.out.println(task);
            }
        } else {
            Ui.taskNotFoundMessage();
        }
    }
}
