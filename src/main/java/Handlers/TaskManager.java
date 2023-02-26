package Handlers;

import Tasks.Task;
import java.util.ArrayList;

public class TaskManager {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the taskList Array
     * 
     * @param t the task to be added
     */
    public static void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes a task from the taskList Array
     * 
     * @param index
     */
    public static void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:\n" + taskList.get(index - 1).describeTask() + "\n");
        taskList.remove(index - 1);
    }

    public static int getTaskCount() {
        return taskList.size();
    }

    /**
     * Prints out all tasks in the taskList Array for the user to read.
     */
    public static void listTask() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks in your list yet!");
            return;
        }

        int existingTaskCount = 1;
        for (Task item : taskList) {
            System.out.println(existingTaskCount + ". " + item.describeTask());
            existingTaskCount++;
        }

        System.out.println("\nYou have " + getTaskCount() + " tasks in the list.\n");
    }

    /**
     * Takes in the user input index, converts to the index of the task in the taskList Array and
     * marks it as done.
     * 
     * @param index the index of the task in the taskList Array
     */
    public static void markTask(int index) {
        if (index > 0 && index <= getTaskCount()) {
            ArrayList<Task> list = getTaskList();
            Task task = list.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + task.describeTask() + "\n");
        }
    }

    /**
     * Takes in the user input index, converts to the index of the task in the taskList Array and
     * marks it as not done.
     * 
     * @param index the index of the task in the taskList Array
     */
    public static void unmarkTask(int index) {
        if (index > 0 && index <= getTaskCount()) {
            ArrayList<Task> list = getTaskList();
            Task task = list.get(index - 1);
            task.unmarkAsDone();
            System.out.println("OK, Ive marked this task as not done yet:\n" + task.describeTask() + "\n");
        }
    }

    
    /** 
     * Prints out all tasks that contain the given description.
     * 
     * @param description the description to be searched for
     */
    public static void findTask(String description) {
        ArrayList<Task> list = getTaskList();
        int existingTaskCount = 1;
        for (Task item : list) {
            if (item.describeTask().contains(description)) {
                System.out.println(existingTaskCount + ". " + item.describeTask());
                existingTaskCount++;
            }
        }
    }

    
    /**
     * Reads a single task from the file and adds it to the task list 
     * and input the relevant details depending on the task type.
     * 
     * @param line the line of text from the file
     */
    public static void readTaskFromFile(String line) {
        String[] taskDetails = line.split("\\|");
        String taskType = taskDetails[0].trim();
        String taskStatus = taskDetails[1].trim();
        String taskDescription = taskDetails[2].trim();

        switch (taskType) {
        case "T":
            Task todo = new Tasks.Todo(taskDescription);
            if (taskStatus.equals("1")) {
                todo.markAsDone();
            }
            addTask(todo);
            break;
        case "D":
            String taskDeadline = taskDetails[3].trim();
            Task deadline = new Tasks.Deadline(taskDescription, taskDeadline);
            if (taskStatus.equals("1")) {
                deadline.markAsDone();
            }
            addTask(deadline);
            break;
        case "E":
            String[] taskDate = taskDetails[3].split("-");
            String taskDateFrom = taskDate[0].trim();
            String taskDateTo = taskDate[1].trim();
            Task event = new Tasks.Event(taskDescription, taskDateFrom, taskDateTo);
            if (taskStatus.equals("1")) {
                event.markAsDone();
            }
            addTask(event);
            break;
        default:
            System.out.println("Error reading file");
            break;
        }
    }
}
