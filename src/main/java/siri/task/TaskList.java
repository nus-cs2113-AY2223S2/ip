package siri.task;
import java.io.IOException;
import java.util.ArrayList;

import static siri.Duke.indexOfTask;

/**
 * Represents the list of task.
 */
public class TaskList {
    protected static ArrayList<Task> taskList;

    /**
     * Create a new empty task list.
     */
    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    /**
     * Create a new task list with the loaded data (loaded task list) from storage file.
     * @param oldTaskList
     */
    public TaskList(ArrayList<Task> oldTaskList){
        this.taskList = new ArrayList<>(oldTaskList);
    }

    /**
     * @return task list.
     */
    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    /**
     * Print the task list task by task.
     */
    public void printTaskList() {
        System.out.println("Below is your task list");
        for (int i = 0; i < indexOfTask; ++i) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    /**
     * Add different types of task to the task list and storage file in different format respectively.
     *
     * @param taskCommand represents the task type (Todo, Deadline, Event).
     * @param taskD represents the task description of each task.
     */
    public void addTask(String taskCommand, String taskD) {
        switch (taskCommand) {
        case "todo":
            taskList.add(new ToDo(taskD.trim()));
            break;
        case "deadline":
            String[] deadlineTaskD = taskD.split("/by ", 2);
            taskList.add(new Deadline(deadlineTaskD[0].trim(), deadlineTaskD[1].trim()));
            break;
        case "event":
            String[] eventName = taskD.split("/from ", 2);
            String[] eventDate = eventName[1].split("/to ", 2);
            taskList.add(new Event(eventName[0].trim(), eventDate[0].trim(), eventDate[1].trim()));
            break;
        }
    }

    /**
     * Print the task that has just been added.
     */
    public void printNewTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(indexOfTask).toString());
        System.out.println("Now you have " + (indexOfTask + 1) + " tasks in the list.");
    }

    /**
     * Delete the task according to the task number input by user, decreasing the size of the task list.
     *
     * @param taskNumber represents the task of that index that the user wants to remove.
     */
    public void deleteTask(int taskNumber) {
        printDeletedTask(taskNumber);
        taskList.remove(taskNumber - 1);
        indexOfTask--;
        //IndexOutOfBoundsException
    }

    /**
     * Print the task that has just been deleted according to the task number.
     *
     * @param taskNumber represents the task of that index that has been deleted.
     */
    public void printDeletedTask(int taskNumber) {
        String deleteString = taskList.get(taskNumber - 1).toString();
        System.out.println("Noted! I've deleted this task:");
        System.out.println("  " + deleteString);
        System.out.println("Now you have " + (indexOfTask - 1) + " tasks in the list.");
    }

    /**
     * Mark and Unmark the task that is indicated by the task number (index of the task).
     *
     * @param marker represents the task command (mark or unmark).
     * @param taskNumber represents the task number to be marked / unmarked.
     */
    public void markTask(String marker, int taskNumber) {
        if (marker.equals("mark")) {
            taskList.get(taskNumber - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("  " + taskList.get(taskNumber - 1).toString());
        } else {
            taskList.get(taskNumber - 1).setDone(false);
            System.out.println("Ok! I've marked this task as not done yet: ");
            System.out.println("  " + taskList.get(taskNumber - 1).toString());
        }

        //Null Pointer Exception (taskNumber > indexOfTask)
    }

    /**
     * Find the task according to the keyword provided by user,
     * and print the list of matching tasks.
     *
     * @param keyword user input
     */
    public void findTask(String keyword){
        boolean is_empty = false;
        System.out.println("Here are the matching tasks in your list: \n");
        for (int i = 0; i < indexOfTask; ++i) {
            if (taskList.get(i).getDescription().contains(keyword)){
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
                is_empty = true;
            }
        }
        if(is_empty == false){
            System.out.println("There is no matching tasks in your list :(");
        }
    }
}