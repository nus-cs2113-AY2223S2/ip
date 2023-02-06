import java.util.Scanner;


public class Duke {
    public static int currentIndex = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tasksSize = 100;
        Task[] tasks = new Task[tasksSize];


        printHelloStatement();
        while (true) {
            String input;
            input = in.nextLine();
            if (input.equals("bye")) {
                printByeStatement();
                break;
            } else if (input.equals("list")) {
                printAllTasks(tasks);
            } else if (input.startsWith("mark")) {
                String[] temp = input.split(" ", 2);
                int taskIndex = Integer.parseInt(temp[1]);
                Task curTask = tasks[taskIndex - 1];
                curTask.markAsDone();
                printTaskStatusStatement(curTask,"mark");
            } else if (input.startsWith("unmark")) {
                String[] temp = input.split(" ", 2);
                int taskIndex = Integer.parseInt(temp[1]);
                Task curTask = tasks[taskIndex - 1];
                curTask.unmarkAsDone();
                printTaskStatusStatement(curTask,"unmark");
            } else if (input.startsWith("todo")) {
                try {
                    String[] temp = input.split("todo "); //separates todo description
                    String description = temp[1];
                    ToDo todo = new ToDo(currentIndex + 1, description);
                    tasks[currentIndex] = todo;
                    currentIndex++;
                    printTaskAddedStatement(currentIndex, todo);
                } catch (IndexOutOfBoundsException exception) {
                    printDottedLine();
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty");
                    printDottedLine();
                }
            } else if (input.startsWith("deadline")) {
                try {
                    String[] temp = input.split("deadline | /by "); //separates deadline description and time
                    String description = temp[1];
                    String by = temp[2];
                    Deadline deadline = new Deadline(currentIndex + 1, description, by);
                    tasks[currentIndex] = deadline;
                    printTaskAddedStatement(currentIndex, deadline);
                } catch (IndexOutOfBoundsException exception) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty");
                }
            } else if (input.startsWith("event")) {
                try {
                    String[] temp = input.split(("event | /from | /to ")); //separates event description and times
                    String description = temp[1];
                    String from = temp[2];
                    String to = temp[3];
                    Event event = new Event(currentIndex + 1, description, from, to);
                    tasks[currentIndex] = event;
                    currentIndex++;
                    printTaskAddedStatement(currentIndex, event);
                } catch (IndexOutOfBoundsException exception) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty");
                }
            } else {
                printUnknownCommandError();
            }
        }
    }

    private static void printDottedLine() {
        System.out.println("____________________________________________________________");
    }
    private static void printAllTasks(Task[] tasks) {
        printDottedLine();
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            if (task != null) {
                System.out.println(task);
            }
        }
        printDottedLine();
    }

    private static void printHelloStatement() {
        printDottedLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printDottedLine();
    }

    private static void printByeStatement() {
        printDottedLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printDottedLine();
    }

    private static void printTaskStatusStatement(Task curTask, String status) {
        printDottedLine();
        if (status.equals("mark")){
            System.out.println("Nice! I've marked this task as done:");
        }
        else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(curTask);
        printDottedLine();
    }

    private static void printTaskAddedStatement(int currentIndex, Task task) {
        printDottedLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (currentIndex == 1) {
            System.out.println("Now you have " + currentIndex + " task in the list.");
        }
        else {
            System.out.println("Now you have " + currentIndex + " tasks in the list.");
        }
        printDottedLine();
    }

    private static void printUnknownCommandError() {
        printDottedLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDottedLine();
    }
}

