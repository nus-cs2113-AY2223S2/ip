import java.util.Scanner;
// Now it is level 4

public class Duke {
    public static Task[] taskList = new Task [100]; // The size of this list is initialize to be 100
    public static int listTailIndex = 0;

    public static void list() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listTailIndex; i++) {
            System.out.print(i + 1);
            System.out.println(". " + taskList[i].showTask());
        }
        System.out.println("____________________________________________________________");
    }

    // Mark and Unmark method
    public static void markMethod(String line) {
        int taskNumber = Integer.parseInt(line.substring(5));
        taskList[taskNumber - 1].isDone = true;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList[taskNumber - 1].showTask());
        System.out.println("____________________________________________________________");
    }

    public static void unmarkMethod(String line) {
        int taskNumber = Integer.parseInt(line.substring(7));
        taskList[taskNumber - 1].isDone = false;
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList[taskNumber - 1].showTask());
        System.out.println("____________________________________________________________");
    }

    //Status related method (Totally three methods)

    public static void statusMethod (String line) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added: this task:");
        int indexOfSpace = line.indexOf(" ");
        String firstLetter = line.substring(0, indexOfSpace);

        // Information is the content without the first letter
        String information = line.substring(indexOfSpace + 1);
        information = transformString.transformString(information);

        if (firstLetter.equals("todo")) {
            Task currentTask = new Task(information, Status.T);
            taskList[listTailIndex] = currentTask;
        } else if (firstLetter.equals("deadline")) {
            Task currentTask = new Task(information, Status.D);
            taskList[listTailIndex] = currentTask;
        } else if (firstLetter.equals("event")) {
            Task currentTask = new Task(information, Status.E);
            taskList[listTailIndex] = currentTask;
        }
        System.out.println("  " + taskList[listTailIndex].showTask());
        listTailIndex++;
        System.out.println("Now you have " + Integer.toString(listTailIndex) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void initialGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void bye() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static boolean exceptionCheck(String line) {
        try {
            catchError.validateMeaningful(line.split(" ")[0]);
        } catch (DukeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("____________________________________________________________");
            return true;
        }
        try {
            catchError.validateEmpty(line);
        } catch (DukeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println("____________________________________________________________");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        // Greet the user
        initialGreeting();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            String firstWord = line.split(" ")[0];
            boolean hasError = false;
            hasError = exceptionCheck(line);

            if (!hasError) {
                if (firstWord.equals("list")) {
                    list();
                } else if (firstWord.equals("mark")) {
                    markMethod(line);

                } else if (firstWord.equals("unmark")) {
                    unmarkMethod(line);
                } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                    statusMethod(line);
                }
            }

            line = in.nextLine();
        }

        // Bye and terminate the program
        bye();
    }
}