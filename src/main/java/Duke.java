import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        printWelcome();

        String input;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        boolean isRunning = true;

        while (isRunning) {
            input = in.nextLine();
            String[] command = input.split(" ", 2);

            switch(command[0]) {
            case "bye":
                printBye();
                isRunning = false;
                break;
            case "list":
                printAllTasks(tasks);
                break;
            case "mark":
                try {
                    markTaskDone(tasks, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please indicate the task number to be marked.");
                }
                break;
            case "delete":
                try {
                    deleteTask(tasks, command[1]);

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please indicate the task number to be deleted.");
                }
                break;
            case "unmark":
                try {
                    markTaskNotDone(tasks, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please indicate the task number to be unmarked.");
                }
                break;
            case "todo":
                try {
                    addTodo(tasks, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    //throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                try {
                    addDeadline(tasks, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    //throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "event":
                try {
                    addEvent(tasks, command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                }
                break;
            default:
                System.out.println("I don't know what that means :(");

            }
        }
    }
    public static void markTaskDone(ArrayList<Task> tasks, String command) /*throws DukeException*/ {

        try {
            int taskNumber = Integer.parseInt(command);
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            printMarkDone();
            printTask(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The given task number does not exist. ):");
            //throw in here
        } catch (NumberFormatException e) {
            System.out.println("The task index must be numeric.");
        } catch (DukeException e) {
            System.out.println("The task is already marked as done.");
        }

    }

    public static void markTaskNotDone(ArrayList<Task> tasks, String command)  {

        try {
            int taskNumber = Integer.parseInt(command);
            Task task = tasks.get(taskNumber - 1);
            task.unmarkAsDone();

            printMarkNotDone();
            printTask(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The given task number does not exist. ):");
            //throw in here
        } catch (NumberFormatException e) {
            System.out.println("The task index must be numeric.");
        } catch (DukeException e) {
            System.out.println("The task is already marked as not done.");
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, String command) {
        try {
            int taskNumber = Integer.parseInt(command);
            Task task = tasks.get(taskNumber - 1);
            printDeleteTask();
            printTask(task);
            tasks.remove(taskNumber - 1);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("The given task number does not exist. ):");
        } catch (NumberFormatException e) {
            System.out.println("The task index must be numeric.");
        }


    }
    public static void addTodo(ArrayList<Task> tasks, String description) {
        printAddTask();

        Task task = new Todo(description);
        tasks.add(task);

        printTask(task);
        printNoOfTasks(tasks.size());
    }

    public static void addDeadline(ArrayList<Task> tasks, String command) /*throws DukeException*/ {

        if (command.contains("/by")) {
            String[] components = command.split(" /by");

            try {
                Task task = new Deadline(components[0], components[1]);
                tasks.add(task);
                printAddTask();
                printTask(task);
                printNoOfTasks(tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please specify a time for the deadline.");
            }

        } else {
            System.out.println("Invalid format. Remember to use '/by' to indicate the time.");
            //throw new DukeException();
        }
    }

    public static void addEvent(ArrayList<Task> tasks, String command) {

        if (command.matches("(.*)" + "/from" + "(.*)" + "/to" + "(.*)")) {

            try {
                String[] components = command.split(" /from | /to "); //split string using "/from" and "/to"
                Task task = new Event(components[0], components[1], components[2]);
                tasks.add(task);

                printAddTask();
                printTask(task);
                printNoOfTasks(tasks.size());
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please specify both the starting and ending time of the event");
            }
        } else {
            System.out.println("Incorrect format. Specify events in the format 'event A /from B to /C'");
        }


    }
    public static void printAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
            return;
        }

        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(count + "." + task);
            count += 1;
        }
    }
    public static void printTask(Task task) {
        System.out.println(task);
    }
    public static void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }
    public static void printMarkDone() {
        System.out.println("Nice! I've marked this task as done:");
    }
    public static void printMarkNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public static void printDeleteTask() {
        System.out.println("Noted. I've removed this task:");
    }
    public static void printNoOfTasks(int size) {
        System.out.println("Now you have " + size + " tasks in the list");
    }
    public static void printInvalidMessage() {
        System.out.println("I don't know what that means :-(");
    }
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
