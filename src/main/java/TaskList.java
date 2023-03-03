import tasktypes.Task;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> TaskList = new ArrayList<>(10);
    private static int NumTasks = 0;

    /**
     * Returns the current tasklist
     * @return TaskList currently stored in the method
     */
    public static ArrayList<Task> getList() {
        return TaskList;
    }

    /**
     * Adds a Task to the tasklist, increments the number of tasks in the list then echos the task added
     * @param newTask Task to be added to the list
     */
    public static void addItem (Task newTask) {
        TaskList.add(newTask);
        NumTasks += 1;
        System.out.println("Okay! I've added: [" + newTask.getTypeIcon() +"] " + newTask.getDescription());
    }

    /**
     * gets the number of tasks in the list
     * @return the number of tasks in the list currently
     */
    public static int getNumTasks() {
        return NumTasks;
    }

    /**
     * marks a task's status as done
     * @param index index of the task in the tasklist
     */
    public static void markDone (int index) {
        if (TaskList.get(index).getStatusIcon().equals(" ")) {
            TaskList.get(index).markDone();
        }
    }

    /**
     * marks a task's status as not done
     * @param index index of the task in the tasklist
     */
    public static void markNotDone (int index) {
        if (TaskList.get(index).getStatusIcon().equals("X")) {
            TaskList.get(index).markNotDone();
        }
    }

    /**
     * get a task item from the tasklist
     * @param index index of the task in the tasklist
     * @return returns a Task object from the tasklist according to the index
     */
    public static Task getItem (int index) {
        return TaskList.get(index);
    }

    /**
     * prints out a task in a pre-set format
     * @param index index of the task in the tasklist
     */
    public static void printItem (int index) {
        System.out.print(index+1 + ". ");
        System.out.println(TaskList.get(index).getTask());
    }

    /**
     * removes a task from the tasklist and decrements the counter for number of tasks
     * @param index index of the task in the tasklist
     */
    public static void deleteTask(int index) {
        TaskList.remove(index);
        NumTasks -= 1;
    }


}
