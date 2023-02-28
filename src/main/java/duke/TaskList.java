package duke;


import java.util.ArrayList;
import duke.data.Task;


public class TaskList {
    final private static ArrayList<Task> tasks = new ArrayList<>();
    private static int tasksLength = 0;

    public static void addTask(Task newTask) {
        tasks.add(newTask);
        tasksLength++;
    }

    public static void deleteTask(int index) {
        tasks.remove(index);
        tasksLength--;
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        return tasksLength;
    }

    public static void printSize() {
        if (tasksLength == 1) {
            System.out.println("Now you have 1 task in the list.");
        }
        else if (tasksLength == 0) {
            System.out.println("Now you have 0 task in the list.");
        }
        else {
            System.out.println("Now you have "+ tasksLength +" tasks in the list.");
        }
    }
}
