import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

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

    private static void executeDukeCommands(String line, ArrayList<Task> tasks) throws DukeException {
        if (line.equalsIgnoreCase("list")) {
            printAllTasks(tasks);
        } else if (line.toLowerCase().startsWith("mark")) {
            try {
                markTaskAsComplete(line, tasks);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ ERROR! You have no tasks to mark as complete.");
            }
        } else if (line.toLowerCase().startsWith("unmark")) {
            try {
                markTaskAsNotComplete(line, tasks);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ ERROR! You have no tasks to mark as not complete.");
            }
        } else if (line.toLowerCase().startsWith("todo")) {
            try {
                addTodo(line, tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!! todo description cannot be empty!");
            }
        } else if (line.toLowerCase().startsWith("deadline")) {
            try {
                addDeadline(line, tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ Sorry! your deadline description is invalid!");
            }
        } else if (line.toLowerCase().startsWith("event")) {
            try {
                addEvent(line, tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ Sorry! your event description is invalid!");
            }
        } else {
            throw new DukeException();
        }
    }

    private static void addTodo(String line, ArrayList<Task> tasks) {
        tasks.add(new Todo(line.substring(5)));
        int taskCount = getTaskIndex(tasks, line.substring(5));
        printTaskAddedDescription(tasks, taskCount);
    }

    private static void addEvent(String line, ArrayList<Task> tasks) {
        int fromIndex = line.indexOf("/from"); //index of event start date/time
        String event = line.substring(6, fromIndex);
        int toIndex = line.indexOf("/to"); //index of event end date/time
        String eventStart = line.substring(fromIndex + 6, toIndex);
        String eventEnd = line.substring(toIndex + 4);
        tasks.add(new Event(event, eventStart, eventEnd));
        int taskCount = getTaskIndex(tasks, event);
        printTaskAddedDescription(tasks, taskCount);
    }

    private static void addDeadline(String line, ArrayList<Task> tasks) {
        int byIndex = line.indexOf("/by"); //index of deadline due date/time
        String taskDescription = line.substring(9, byIndex);
        String deadline = line.substring(byIndex + 4);
        tasks.add(new Deadline(taskDescription, deadline));
        int taskCount = getTaskIndex(tasks, taskDescription);
        printTaskAddedDescription(tasks, taskCount);
    }

    public static int getTaskIndex(ArrayList<Task> tasks, String description) {
        int index = 0;
        for (Task myObj : tasks) {
            if(description.equalsIgnoreCase(myObj.description)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static void printAllTasks(ArrayList<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + index + "." + tasks.get(i).toString()); //print Task Description
            index++;
        }
    }

    private static void markTaskAsNotComplete(String line, ArrayList<Task> tasks) {
        int index = Integer.parseInt(line.substring(7));
        tasks.get(index-1).setTaskStatus(false);
        System.out.println(" Okay, I've marked this task as not done yet:");
        System.out.println(tasks.get(index-1).toString()); //print Task Description
    }

    private static void markTaskAsComplete(String line, ArrayList<Task> tasks) {
        int index = Integer.parseInt(line.substring(5));
        tasks.get(index-1).setTaskStatus(true);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(tasks.get(index-1).toString()); //print Task Description
    }

    private static void printTaskAddedDescription(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(" Got it. I've added this task:");
        System.out.println(tasks.get(taskIndex).toString()); //print Task Description
        System.out.println(" Now you have " + tasks.size() + " tasks in your list.");
    }

    private static void printByeMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}