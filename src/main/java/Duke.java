import java.util.Scanner;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Duke {
    public static void main(String[] args) {

        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String line = in.nextLine();
            System.out.println("____________________________________________________________");
            if ("bye".equalsIgnoreCase(line)) {
                printByeMessage();
                break;
            }
            try {
                executeDukeCommands(line, tasks);
            } catch (DukeException e) {
                System.out.println("☹ ERROR! Sorry, this is not a recognized Duke command!");
            }
            System.out.println("____________________________________________________________");
        }
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke, your personal task manager.");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Executes duke commands: list, mark, unmark, todo, deadline, and event with exception handling.
     *
     * @param command The user inputted command.
     * @param tasks   An ArrayList holding the task objects.
     * @throws DukeException if input is not any of the supported duke commands.
     */

    private static void executeDukeCommands(String command, ArrayList<Task> tasks) throws DukeException {
        if (command.equalsIgnoreCase("list")) {
            printAllTasks(tasks);
        } else if (command.toLowerCase().startsWith("mark")) {
            try {
                markTaskAsComplete(command, tasks);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ ERROR! this task to mark is not recognized.");
            }
        } else if (command.toLowerCase().startsWith("unmark")) {
            try {
                markTaskAsNotComplete(command, tasks);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ ERROR! this task to unmark is not recognized.");
            }
        } else if (command.toLowerCase().startsWith("todo")) {
            try {
                addTodo(command, tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!! todo description cannot be empty!");
            }
        } else if (command.toLowerCase().startsWith("deadline")) {
            try {
                addDeadline(command, tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ Sorry! your deadline description is invalid!");
            }
        } else if (command.toLowerCase().startsWith("event")) {
            try {
                addEvent(command, tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ Sorry! your event description is invalid!");
            }
        } else if (command.toLowerCase().startsWith("delete")) {
            try {
                deleteTask(tasks, command);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ ERROR! this task to delete is not recognized.");
            }
        } else {
            throw new DukeException();
        }
    }

    /**
     * Adds a new todo to the ArrayList of task objects.
     *
     * @param line  The todo description.
     * @param tasks An ArrayList holding the task objects.
     */

    private static void addTodo(String line, ArrayList<Task> tasks) {
        tasks.add(new Todo(line.substring(5)));
        int taskCount = getTaskIndex(tasks, line.substring(5));
        printTaskAddedDescription(tasks, taskCount);
    }

    /**
     * Adds a new event to the ArrayList of task objects.
     *
     * @param line  The event description.
     * @param tasks An ArrayList holding the task objects.
     */

    private static void addEvent(String line, ArrayList<Task> tasks) {
        //index of event start date/time
        int fromIndex = line.indexOf("/from");
        String event = line.substring(6, fromIndex);
        //index of event end date/time
        int toIndex = line.indexOf("/to");
        String eventStart = line.substring(fromIndex + 6, toIndex);
        String eventEnd = line.substring(toIndex + 4);
        tasks.add(new Event(event, eventStart, eventEnd));
        int taskCount = getTaskIndex(tasks, event);
        printTaskAddedDescription(tasks, taskCount);
    }

    /**
     * Adds a new deadline to the ArrayList of task objects.
     *
     * @param line  The deadline description.
     * @param tasks An ArrayList holding the task objects.
     */

    private static void addDeadline(String line, ArrayList<Task> tasks) {
        //index of deadline due date/time
        int byIndex = line.indexOf("/by");
        String taskDescription = line.substring(9, byIndex);
        String deadline = line.substring(byIndex + 4);
        tasks.add(new Deadline(taskDescription, deadline));
        int taskCount = getTaskIndex(tasks, taskDescription);
        printTaskAddedDescription(tasks, taskCount);
    }

    /**
     * Deletes the task from the ArrayList of task objects
     *
     * @param tasks An ArrayList holding the task objects.
     * @param task  The command to delete the task
     */
    private static void deleteTask(ArrayList<Task> tasks, String task) {
        int index = Integer.parseInt(task.substring(7));
        String description = tasks.get(index - 1).toString();
        printTaskDeletedDescription(tasks, description);
        tasks.remove(index - 1);
    }

    /**
     * Returns the index of a particular task from the ArrayList.
     *
     * @param tasks       An ArrayList holding the task objects.
     * @param description The description of the task.
     * @return index of task.
     */

    public static int getTaskIndex(ArrayList<Task> tasks, String description) {
        int index = 0;
        for (Task myObj : tasks) {
            if (description.equalsIgnoreCase(myObj.getDescription())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Prints all tasks in the ArrayList.
     *
     * @param tasks An ArrayList holding the task objects.
     */

    private static void printAllTasks(ArrayList<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");
        int index = 1;
        for (Task userTask : tasks) {
            //print Task Description
            System.out.println("  " + index + "." + userTask.toString());
            index++;
        }
    }

    /**
     * Marks the task as not complete.
     *
     * @param line  The description of the task.
     * @param tasks An ArrayList holding the task objects.
     */

    private static void markTaskAsNotComplete(String line, ArrayList<Task> tasks) {
        int index = Integer.parseInt(line.substring(7));
        tasks.get(index - 1).setTaskStatus(false);
        System.out.println(" Okay, I've marked this task as not done yet:");
        //print Task Description
        System.out.println(tasks.get(index - 1).toString());
    }

    /**
     * Marks the task as complete.
     *
     * @param line  The description of the task.
     * @param tasks An ArrayList holding the task objects.
     */

    private static void markTaskAsComplete(String line, ArrayList<Task> tasks) {
        int index = Integer.parseInt(line.substring(5));
        tasks.get(index - 1).setTaskStatus(true);
        System.out.println(" Nice! I've marked this task as done:");
        //print Task Description
        System.out.println(tasks.get(index - 1).toString());
    }

    /**
     * Prints the description of the added task.
     *
     * @param tasks     An ArrayList holding the task objects.
     * @param taskIndex The index of the task.
     */
    private static void printTaskAddedDescription(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(" Got it. I've added this task:");
        //print Task Description
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in your list.");
    }

    /**
     * Prints the description of the added task.
     *
     * @param tasks       An ArrayList holding the task objects.
     * @param description The description of the task.
     */
    private static void printTaskDeletedDescription(ArrayList<Task> tasks, String description) {
        System.out.println(" Noted. I've deleted this task:");
        //print Task Description
        System.out.println(description);
        int items = tasks.size() - 1;
        System.out.println(" Now you have " + items + " tasks in your list.");
    }

    private static void printByeMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}