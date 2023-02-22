package Duke;

import Duke.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the methods that change the tasks in the task list
 */
public class TaskList {
    public static ArrayList<Task> tasks = Duke.tasks;

    /**
     * Prints a list of tasks that have a name that includes a user-specified keyword
     *
     * @param command user input that includes keyword to search for that are in the name of currents tasks
     */

    public static void findTask(String command) {
        String keyword = command.substring(5);

        ArrayList<Task> refinedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if ((task.getTaskName()).contains(keyword)) {
                refinedTasks.add(task);
            }
        }
        int numberOfFoundTasks = refinedTasks.size();
        int i = 1;
        System.out.println("There are " + numberOfFoundTasks + " matching tasks in your list");
        for (Task foundTask : refinedTasks) {
            if (foundTask != null) {
                System.out.print(i);
                foundTask.printTask();
            }
            i++;
        }


    }


    /**
     * Creates a todo task of name determined by user input
     *
     * @param command user input that contains the name of task to create
     */
    public static void createTodo(String command) {
        command = command.substring(5);
        Task task = new Task(command);
        task.isCompleted = false;
        task.taskName = command;
        System.out.println("added:  " + task.taskName);
        tasks.add(task);
    }

    /**
     * Creates an event task of name, start time and end time determined by user-input
     *
     * @param command user input that contains name, start and end time of event task
     */
    public static void createEvent(String command) {
        String content = command.substring(5);
        String[] contents = content.split("/");
        String title = contents[0];
        String startTime = contents[1];
        String endTime = contents[2];
        Event newEvent = new Event(title);
        newEvent.setStartTime(startTime);
        newEvent.setEndTime(endTime);
        newEvent.setAsEvent();
        tasks.add(newEvent);
        newEvent.newEventResponse();
    }

    /**
     * Creates a deadline task of name and end time determined by user-input
     *
     * @param command user input that contains task name and end time
     */
    public static void createDeadline(String command) {
        String content = command.substring(8);
        String[] contents = content.split("/");
        String title = contents[0];
        String dueDate = contents[1];
        Deadline newDeadline = new Deadline(title);
        newDeadline.setEndTime(dueDate);
        newDeadline.setAsDeadline();
        tasks.add(newDeadline);
        newDeadline.newDeadlineResponse();
    }

    /**
     * Changes the completed status of task to not completed
     *
     * @param command       name of task to change completed status of
     * @param numberOfTasks number of tasks in the list
     */
    public static void numericTaskAction(String command, int numberOfTasks) {
        String[] result = command.split(" ");

        int taskNum = Integer.parseInt(result[1]);
        if (taskNum > numberOfTasks || taskNum <= 0) {
            System.out.println("Error! No such task exists!");
        } else {
            switch (result[0]) {
            case "mark":
                numericMethods.markTask(tasks, taskNum);
                break;

            case "unmark":
                numericMethods.unmarkTask(tasks, taskNum);
                break;

            case "delete":
                numericMethods.deleteTask(tasks, taskNum);
            }

        }

    }


    /**
     * Prints all the details of all tasks in the list
     */
    public static void printTaskList() {
        int i = 1;
        for (Task a : Duke.tasks) {
            if (a != null) {
                System.out.print(i);
                a.printTask();
                i++;
            }
        }
    }

    /**
     * Reads the input from user
     *
     * @return user input
     */
    public static String readTask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;

    }

    /**
     * Prompts the user for additional commands
     *
     * @return user input
     */
    public static String checkForAdditionalTask() {

        System.out.println("Do you have any other task for me?  ");
        return readTask();

    }


}
