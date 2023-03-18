package mikeyparts;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static mikeyparts.Storage.saveToFile;
import static mikeyparts.UI.addTaskMessage;
import static mikeyparts.UI.printInvalidInputMessage;
import static mikeyparts.UI.printInvalidMarkMessage;
import static mikeyparts.UI.printInvalidUnmarkMessage;
import static mikeyparts.UI.printMarkTasksMessage;
import static mikeyparts.UI.printTask;
import static mikeyparts.UI.printUnmarkTasksMessage;

public class TaskList {
    public static ArrayList<Task> tasks;

    /**
     * Returns a new Task of the "Deadline" type and adds it to the "tasks" arraylist.
     *
     * @param taskName the description of the task
     * @param dateDue the date the task is due
     * @param completion the completion status of the task
     * @return a new task of the "Deadline" type
     */
    public static Task newDeadline(String taskName, String dateDue, int completion) {
        Task newTask = null;
        newTask = new Deadline(taskName, dateDue);
        if(completion == 1) {
            newTask.isDone = true;
        } else {
            newTask.isDone = false;
        }
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Returns a new Task of the "To Do" type and adds it to the "tasks" arraylist.
     *
     *
     * @param taskName the description of the task
     * @param completion the completion status of the task
     * @return a new task of the "To Do" type
     */
    public static Task newTodo(String taskName, int completion) {
        Task newTask = null;
        newTask = new Todo(taskName);
        if(completion == 1) {
            newTask.isDone = true;
        } else {
            newTask.isDone = false;
        }
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Returns a new task of the "Event" type and adds it to the "tasks" arraylist
     *
     * @param taskName the description of the task
     * @param timeOfEvent the start and end times of the task
     * @param completion the completion status of the task
     * @return a new task of the "Event" type
     */
    public static Task newEvent(String taskName, String timeOfEvent, int completion) {
        Task newTask = null;
        newTask = new Event(taskName, timeOfEvent);
        if(completion == 1) {
            newTask.isDone = true;
        } else {
            newTask.isDone = false;
        }
        tasks.add(newTask);
        return newTask;
    }



    /**
     * Deletes a task from the "tasks" arraylist
     *
     * @param taskNumber the number of the task in the list
     */
    public static void deleteTask(int taskNumber) {
        System.out.println("I've gotcha mate, removed [" + tasks.get(taskNumber).getTaskType() + "]" + "["
                + tasks.get(taskNumber).getStatusIcon() + "]" + tasks.get(taskNumber).getName() + " "
                + tasks.get(taskNumber).getDate());
        tasks.remove(taskNumber);
        addTaskMessage(tasks);
    }

    /**
     * Method to find a task based on a keyword in a search term and prints out a list of tasks containing that keyword
     *
     * @param searchTerm the keyword to search for within the list of tasks
     */
    public static void findTasks(String searchTerm) {
        String keyword = searchTerm;
        System.out.println("Here ya go bruv, that's everythin that contains that search term:");
        for(int i = 0; i < tasks.size(); i++) {
            String temp = tasks.get(i).getName();
            int taskNumberTemp = i + 1;
            if (temp.contains(keyword)) {
                System.out.print(taskNumberTemp + ". ");
                printTask(tasks, i);
            }
        }
    }

    public static ArrayList<Task> markDone(ArrayList<Task> tasks, int i) {
        try {
            tasks.get(i).isDone = true;
            printMarkTasksMessage(tasks, i);
            saveToFile(tasks);
        } catch (Exception e) {
            printInvalidMarkMessage();
        }
        return tasks;
    }

    public static ArrayList<Task> unmarkDone (ArrayList<Task> tasks, int i) {
        try {
            tasks.get(i).isDone = false;
            printUnmarkTasksMessage(tasks, i);
            saveToFile(tasks);
        } catch (Exception e) {
            printInvalidUnmarkMessage();
        }
        return tasks;
    }
}
