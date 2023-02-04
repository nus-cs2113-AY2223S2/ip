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
            try {
                isRunning = runCommand(userInput, listOfTasks, userInputSplit);
            } catch (TodoCommandException e) {
                System.out.println("OOPS!!! The description of todo cannot be empty");
                System.out.println("Usage: todo <description>");
            } catch (MarkUnmarkCommandException e) {
                System.out.println("OOPS!!! please give me a valid number from the list.");
                System.out.println("Usage: mark/unmark <task number>");
            } catch (DeadlineCommandException e) {
                System.out.println("OOPS!!! please give me a deadline.");
                System.out.println("Usage: deadline /by <specify by when>");
            } catch (EventCommandException e) {
                System.out.println("OOPS!!! please give me a event.");
                System.out.println("Usage: event /from <specify from when> /to <specify to when>");
            }

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
    private static boolean runCommand(String userInput, ArrayList<Task> listOfTasks, String[] userInputSplit)
            throws TodoCommandException, MarkUnmarkCommandException, DeadlineCommandException, EventCommandException {
        Task task;
        String userInputNoCommand = userInput.replace(userInputSplit[0], "");
        switch (userInputSplit[0]) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        case "list":
            if (listOfTasks.isEmpty()) {
                System.out.println("Wow! you currently have no task!");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listOfTasks.size(); i++) {
                    task = listOfTasks.get(i);
                    System.out.println(i + 1 + "." + task);
                }
            }
            break;
        case "mark":
            markUnmarkTask(listOfTasks, userInputSplit, "mark");
            break;
        case "unmark":
            markUnmarkTask(listOfTasks, userInputSplit, "unmark");
            break;
        case "todo":
            task = new Todo(userInputNoCommand);
            if (userInputNoCommand.equals("")) {
                throw new TodoCommandException();
            }
            addAndPrintTask(listOfTasks, task);
            break;
        case "deadline":
            String[] splitDeadline = userInputNoCommand.split("/by");
            if (userInputNoCommand.equals("") || splitDeadline.length != 2) {
                throw new DeadlineCommandException();
            }
            task = new Deadline(splitDeadline[0], splitDeadline[1]);
            addAndPrintTask(listOfTasks, task);
            break;
        case "event":
            String replaceSlash = userInputNoCommand.replace("/from", "--from:");
            replaceSlash = replaceSlash.replace("/to", "to:");
            String[] splitEvent = replaceSlash.split("--");
            if (userInputNoCommand.equals("") || splitEvent.length != 2) {
                throw new EventCommandException();
            }
            task = new Event(splitEvent[0], splitEvent[1]);
            addAndPrintTask(listOfTasks, task);
            break;

        default:
            System.out.println("OOPS!! I'm sorry, but I don't know what that means. Please give me a valid command.");
            System.out.println("List of valid commands:");
            System.out.println("Usage: todo <description>");
            System.out.println("Usage: mark/unmark <task number>");
            System.out.println("Usage: deadline /by <specify by when>");
            System.out.println("Usage: event /from <specify from when> /to <specify to when>");
            break;

        }
        return true;
    }

    /**
     * @param listOfTasks    An arraylist storing the list of tasks the user created.
     * @param userInputSplit An array storing the user input but split by " ".
     * @param markOrUnmark   A String variable telling the method whether the task is to be marked or unmarked.
     * @throws MarkUnmarkCommandException     if the user gave an invalid task number not in the list, this exception will be thrown.
     * @throws ArrayIndexOutOfBoundsException if the user gave an empty number, this exception will be thrown.
     * @throws NumberFormatException          if the user gave something that is not an integer, this exception will be thrown.
     */
    private static void markUnmarkTask(ArrayList<Task> listOfTasks, String[] userInputSplit, String markOrUnmark)
            throws MarkUnmarkCommandException {
        Task task;
        int selectedTask;
        try {
            selectedTask = Integer.parseInt(userInputSplit[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please give a valid number from the list!");
            System.out.println("Usage: mark/unmark <task number>");
            return;
        }
        if (selectedTask < 0 || selectedTask >= listOfTasks.size()) {
            throw new MarkUnmarkCommandException();
        }
        task = listOfTasks.get(selectedTask);
        if (markOrUnmark.equals("mark")) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
        } else {
            task.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);

        }

    }

    /**
     * Add user task to an ArrayList and print out the task added.
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
