import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        printBanner();
        Scanner in = new Scanner(System.in);
        String userInput;
        ArrayList<Task> listOfTasks = new ArrayList<>();
        boolean isRunning = true;

        while (isRunning) {
            userInput = in.nextLine();
            String[] userInputSplit = userInput.split(" ");
            isRunning = runCommand(userInput, listOfTasks, userInputSplit);
        }
    }


    /**
     * Run the command the user typed.
     *
     * @param userInput      What the user inputted into the command line.
     * @param listOfTasks    An arraylist storing the list of tasks the user created.
     * @param userInputSplit An array storing the user input but split by " ".
     * @return false only if "bye" is typed. true otherwise.
     */
    private static boolean runCommand(String userInput, ArrayList<Task> listOfTasks, String[] userInputSplit) {
        Task task;
        String userInputNoCommand = userInput.replace(userInputSplit[0], "");
        switch (userInputSplit[0]) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < listOfTasks.size(); i++) {
                task = listOfTasks.get(i);
                System.out.println(i + 1 + "." + task);
            }
            break;
        case "mark":
            int markTask = Integer.parseInt(userInputSplit[1]) - 1;
            task = listOfTasks.get(markTask);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
            break;
        case "unmark":
            int unmarkTask = Integer.parseInt(userInputSplit[1]) - 1;
            task = listOfTasks.get(unmarkTask);
            task.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
            break;
        case "todo":
            task = new Todo(userInputNoCommand);
            addAndPrintTask(listOfTasks, task);
            break;
        case "deadline":
            String[] splitDeadline = userInputNoCommand.split("/by");
            task = new Deadline(splitDeadline[0], splitDeadline[1]);
            addAndPrintTask(listOfTasks, task);
            break;
        case "event":
            String replaceSlash = userInputNoCommand.replace("/from", "--from:");
            replaceSlash = replaceSlash.replace("/to", "to:");
            String[] splitEvent = replaceSlash.split("--");
            task = new Event(splitEvent[0], splitEvent[1]);
            addAndPrintTask(listOfTasks, task);
            break;

        default:
            System.out.println("Please input a valid command.");
            break;

        }
        return true;
    }

    /**
     * Add user task to and ArrayList and print out the task added.
     *
     * @param listOfTasks An arraylist storing the list of tasks the user created.
     * @param task        Store the type of task the user ask to create.
     */
    private static void addAndPrintTask(ArrayList<Task> listOfTasks, Task task) {
        listOfTasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * Print out the logo of the cmd application.
     */
    private static void printBanner() {
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
