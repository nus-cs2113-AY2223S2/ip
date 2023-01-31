import java.util.Scanner;
public class Duke {

    private static final String LINE = "____________________________________________________________";
    private static final String BLANK = "    ";

    public static void main(String[] args) {
        printWelcome();
        Scanner in = new Scanner(System.in);
        String inputString;
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;

        while (true) {
            inputString = in.nextLine();
            String[] command = inputString.split(" ", 2);
            //list command
            if (command[0].equals("list")) {
                doList(tasks, numberOfTasks);
            }
            //bye command
            else if (command[0].equals("bye")) {
                doExit();
                return;
            }
            //mark/unmark command
            else if (command[0].equals("mark") || command[0].equals("unmark")) {
                int indexToChange = Integer.parseInt(command[1]) - 1;
                //Check for error
                if (!(indexToChange < numberOfTasks)) {
                    System.out.println("Error");
                    continue;
                }
                if (command[0].equals("mark")) {
                    tasks[indexToChange].setDone();
                    printMarkedTask(tasks, indexToChange);
                } else {
                    tasks[indexToChange].setNotDone();
                    printUnmarkedTask(tasks, indexToChange);
                }
            }
            //add task to list
            else {
                tasks[numberOfTasks] = new Task(inputString);
                numberOfTasks += 1;
                printAddTask(tasks, numberOfTasks - 1);
            }
        }
    }

    private static void printWelcome() {
        String logo = BLANK + LINE + "\n"
                + BLANK + "Hello! I'm Duke\n"
                + BLANK + "What can I do for you?\n"
                + BLANK + LINE;
        System.out.println("\n" + logo);
    }

    private static void doList(Task[] tasks, int numberOfTasks) {
        System.out.println(BLANK + LINE);
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print(BLANK + (i + 1) + ".");
            printTask(tasks, i);
        }
        System.out.println(BLANK + LINE);
    }
    private static void doExit() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Bye. Hope to see you again soon!");
        System.out.println(BLANK + LINE);
    }
    private static void printAddTask(Task[] tasks, int indexToPrint) {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Added to list:");
        System.out.print(BLANK);
        printTask(tasks, indexToPrint);
        System.out.println(BLANK + LINE);
    }
    private static void printMarkedTask(Task[] tasks, int indexToPrint) {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Nice! I've marked this task as done:");
        System.out.print(BLANK);
        printTask(tasks, indexToPrint);
        System.out.println(BLANK + LINE);
    }
    private static void printUnmarkedTask(Task[] tasks, int indexToPrint) {
        System.out.println(BLANK + LINE);
        System.out.print(BLANK);
        System.out.println(BLANK + "OK, I've marked this task as not done yet:");
        System.out.print(BLANK);
        printTask(tasks, indexToPrint);
        System.out.println(BLANK + LINE);
    }

    private static void printTask(Task[] tasks, int indexToPrint) {
        System.out.print("[");
        if (tasks[indexToPrint].getIsDone()) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + tasks[indexToPrint].getTaskName());
    }
}
