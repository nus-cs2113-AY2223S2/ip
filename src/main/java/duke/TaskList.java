package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> get() {
        return this.tasks;
    }

    public static void addEventTask(String[] informationNeededForPerformingUserRequest) {
        tasks.add(new Event(informationNeededForPerformingUserRequest[1], informationNeededForPerformingUserRequest[2], informationNeededForPerformingUserRequest[3]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "event", tasks.size());
    }

    // i put to this correct my Storage class addition of tasks (general task addition)
    public static void addTask(Task task) {
        tasks.add(task);
    }

    // customized task addition after parsing the input
    public static void addDeadlineTask(String[] informationNeededForPerformingUserRequest) {
        tasks.add(new Deadline(informationNeededForPerformingUserRequest[1], informationNeededForPerformingUserRequest[2]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "deadline", tasks.size());
    }

    public static void addToDoTask(String[] informationNeededForPerformingUserRequest) {
        tasks.add(new ToDo(informationNeededForPerformingUserRequest[1]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "todo", tasks.size());
    }

    public static void deleteTask(String[] informationNeededForPerformingUserRequest) {
        int indexToRemove = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        Ui.printNotification(tasks.get(indexToRemove), "delete", tasks.size() - 1);
        tasks.remove(indexToRemove);
    }

    public static void unmarkTask(String[] informationNeededForPerformingUserRequest) {
        int indexToBeUnmarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        tasks.get(indexToBeUnmarked).setDone(false);
        Ui.printNotification(tasks.get(indexToBeUnmarked), "unmark", tasks.size());
    }

    public static void markTask(String[] informationNeededForPerformingUserRequest) {
        int indexToBeMarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        tasks.get(indexToBeMarked).setDone(true);
        Ui.printNotification(tasks.get(indexToBeMarked), "mark", tasks.size());
    }

    public static void findTask(String[] informationNeededForPerformingUserRequest) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(informationNeededForPerformingUserRequest[1])) {
                tasksWithKeyword.add(task);
            }
        }
        if (tasksWithKeyword.isEmpty()) {
            System.out.println("There were no matching tasks in your list."); // add to UI later?
            return;
        }
        // if there are matches, we list the tasks
        Ui.listTasks(tasksWithKeyword, "find");
    }
}