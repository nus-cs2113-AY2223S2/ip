package psyduck.tasklist;

import java.util.ArrayList;
import psyduck.task.Task;
import psyduck.ui.Ui;

public class TaskList {

    private static final int NOT_FOUND = -1;
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static int taskCount = 0;

    /**
     * Returns the number of tasks in the array list.
     *
     * @return the number of tasks currently in the array list.
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns the Task object at a specified position in the list.
     *
     * @param taskNum the position of the Task object in the list.
     * @return the Task object at the index specified in the list.
     */
    public static Task getTask(int taskNum) {
        return tasks.get(taskNum - 1); //array is 0-indexed, taskNum is 1-indexed
    }


    /**
     * Returns the latest Task object that was added to the list.
     *
     * @return the last Task object that was added to the list.
     */
    public static Task getNewestTask() {
        return tasks.get(taskCount - 1); //array is 0-indexed, taskNum is 1-indexed
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * Removes the Task object at a specified position in the list.
     *
     * @param taskNum the position of the Task object in the list.
     */
    public void removeTask(int taskNum) {
        tasks.remove(taskNum - 1);
        taskCount--;

    }

    /**
     * Finds and returns the list tasks that are closest to the keyword given.
     *
     * @param input the keyword used for the search.
     */
    public void findTasks(String input) {
        if (taskCount == 0) {
            System.out.println("List is empty");
            return;
        }
        ArrayList<Task> temp = new ArrayList<>();
        int tasksFound = 0;
        for (int i = 0; i < taskCount; i++) {
            String description = tasks.get(i).getDescription();
            int targetPos = description.indexOf(input);
            if (targetPos == NOT_FOUND) {
                continue;
            } else {
                temp.add(tasks.get(i));
                tasksFound++;
            }
        }
        Ui.linePrint();
        System.out.println("Here are some matching tasks: ");
        for (int i = 0; i < tasksFound; i++) {
            System.out.print(i + 1 + "."); //format is "[index]. task"
            System.out.println(temp.get(i));
        }
        Ui.linePrint();
    }

    /**
     *  List out all tasks currently in the list
     */
    public void listTasks() {
        Ui.linePrint();
        if (taskCount == 0) { //list is empty
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.print(i + 1 + "."); //format is "[index]. task"
                System.out.println(tasks.get(i));
            }
        }
        Ui.linePrint();
    }
}
