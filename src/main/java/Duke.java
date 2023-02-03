import java.util.Scanner;

public class Duke {
    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    //exit loop when command received is "bye"
    public static void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    //list tasks
    public static void listTasks(){
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= Task.taskCount; i++) {
            System.out.println(i + "." + Task.tasks[i - 1]);
        }
        printLine();
    }

    //add a new task
    public static void unidentifiedCommand(){
        printLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means ☹ ");
        printLine();
    }

    //mark task as done
    public static void markTask(String[] input){
        try {
            int index = Integer.parseInt(input[1]);
            Task t = Task.tasks[index-1];
            t.markAsDone();
            printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
            printLine();
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! There's not that many tasks in here. Please try again.");
            printLine();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
        }
    }

    //mark task as not done
    public static void unmarkTask(String[] input){
        try {
            int index = Integer.parseInt(input[1]);
            Task t = Task.tasks[index-1];
            t.markNotDone();
            printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(t);
            printLine();
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! There's not that many tasks in here. Please try again.");
            printLine();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
        }
    }

    //to-do
    public static void addTodo(String[] input){
        try {
            Task t = new Todo(input[1]);
            Task.tasks[Task.taskCount] = t;
            Task.taskCount++;
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            printLine();
        }
    }

    //deadline
    public static void addDeadline(String[] input){
        try {
            String[] doBy = input[1].split("/by ", 2);
            Task t = new Deadline(doBy[0], doBy[1]);
            Task.tasks[Task.taskCount] = t;
            Task.taskCount++;
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description or due date of a deadline cannot be empty.");
            System.out.println("Please follow this format: deadline [description] /by [due date]");
            printLine();
        }
    }

    //event
    public static void addEvent(String[] input){
        try {
            String[] start = input[1].split("/from ", 2);
            String[] end = start[1].split("/to ", 2);
            Task t = new Event(start[0], end[0], end[1] );
            Task.tasks[Task.taskCount] = t;
            Task.taskCount++;
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description or duration of an event cannot be empty.");
            System.out.println("Please follow this format: event [description] /from [start] /to [end]");
            printLine();
        }
    }

    public static void handleCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!(command.equals("bye"))) {
            //list tasks
            if (command.equals("list")) {
                listTasks();
            } else {
                String[] input = command.split(" ", 2); //only check the first word
                String firstWord = input[0];
                switch (firstWord) {
                    //mark task
                    case "mark":
                        markTask(input);
                        break;
                    //un-mark task
                    case "unmark":
                        unmarkTask(input);
                        break;
                    //to-do
                    case "todo":
                        addTodo(input);
                        break;
                    //deadline
                    case "deadline":
                        addDeadline(input);
                        break;
                    //event
                    case "event":
                        addEvent(input);
                        break;
                    //unidentified action
                    default:
                        unidentifiedCommand();
                        break;
                }
            }
            command = in.nextLine(); //read next command
        }
    }

    public static void main(String[] args) {
        greetUser();
        handleCommands();
        sayBye();
    }
}

