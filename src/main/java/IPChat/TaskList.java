package IPChat;
import ipchatExceptions.IPChatExceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class that contains methods of each of the tasks commanded by the user
 *
 * @author DeepanjaliDhawan
 */
public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int tasksCount = 0; // used camelCase
    public static int checkInput = 0;

    /**
     * Method that checks the input and = 1 when the program is terminated by user
     */
    public static void checkStatements() {
        checkInput = 1;
    }

    /**
     * Says bye to the user and exits the program
     * @param statements Commands inputted by the user
     * @throws IPChatExceptions throws exception if the bye command is written incorrectly
     */
    public static void sayBye(String statements) throws IPChatExceptions {
        if (statements.length() != 3) {
            throw new IPChatExceptions("Too long a goodbye makes me emotional and too short a goodbye makes me feel ignored!\n");
        } else {
            System.out.println("------------------------------------------");
            System.out.println("Bye, Hope to see you soon");
            System.out.println("------------------------------------------");
            checkStatements();
        }
        System.exit(0);
    }

    /**
     * Create a list of the task
     * @param statements Commands inputted by the user
     * @throws IPChatExceptions throws exception if user commands an empty list
     */
    public static void listTasks(String statements) throws IPChatExceptions {
        if (tasksCount == 0) {
            throw new IPChatExceptions("Please give an input");
        } else {
            System.out.println("------------------------------------------");
            System.out.println("Here is the list of tasks for the day! All the best :) \n");
            for (int i = 0; i < tasksCount; i += 1) {
                System.out.println((i + 1) + ". " + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).toString());
            }
            System.out.println("------------------------------------------");
        }
    }

    /**
     * // Marks a task as done
     * @param statements Commands inputted by the user
     * @throws IPChatExceptions throws exception if the user enters non-existent task number
     */
    public static void markDone (String statements) throws IPChatExceptions {
        if (tasksCount != 0) {
            try {
                int taskIndex = Integer.parseInt(statements.substring(statements.lastIndexOf(" ") + 1)) - 1; // changed index to taskIndex
                tasks.get(taskIndex).markAsDone();
                System.out.println("I have marked the task as done");
                System.out.println("------------------------------------------");
                System.out.println("  " + tasks.get(taskIndex).toString() + "\n");
            } catch (NullPointerException e) {
                System.out.println("Invalid Task Number");
            }
        } else {
            throw new IPChatExceptions("To start listing");
        }
    }

    /**
     * tasks to do
     * @param statements Commands inputted by the user
     */
    public static void toDoTasks (String statements) {
        if (statements.length() == 4) {
            System.out.println("Please continue");
        } else {
            int todoLength = statements.length();
            String todoName = statements.substring(5, todoLength);
            tasks.add(tasksCount, new ToDo(todoName));
            System.out.println("------------------------------------------");
            System.out.println("Got it. I've added this task:\n" + tasks.get(tasksCount).toString());
            tasksCount += 1;
            System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
            System.out.println("------------------------------------------");
        }
    }

    /**
     * Tasks having deadlines
     * @param statements Commands inputted by the user
     */
    public static void deadlineTasks(String statements) {
        if (statements.length() == 8) {
            System.out.println("Please provide correct input");
        } else {
            int end = statements.indexOf("/");
            String deadlineName = statements.substring(9, end);
            int deadlineLength = statements.length();
            String by = statements.substring(end + 4, deadlineLength);
            LocalDate date = LocalDate.parse(by);
            LocalDate day = LocalDate.parse(by);
            tasks.add(tasksCount, new Deadline(deadlineName, by));
            System.out.println("------------------------------------------");
            System.out.println("Date of deadline: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
            System.out.println("Day of deadline: " + day.getDayOfWeek());
            System.out.println("Got it. I've added this task:\n");
            System.out.println(tasks.get(tasksCount).toString());
            tasksCount += 1;
            System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
            System.out.println("------------------------------------------");
        }
    }

    /**
     * / Events of the task
     * @param statements Commands inputted by the user
     */
    public static void eventTasks(String statements) {
        if (statements.length() == 5) {
            System.out.println("Please provide correct input");
        } else if (!statements.contains("/")) {
            System.out.println("Please provide timings");
        } else {
            int fromStart = statements.indexOf(" /from");
            int toStart = statements.indexOf(" /to");

            String eventName = statements.substring(5, fromStart);
            String fromBegin = statements.substring(fromStart + 7, toStart);
            String toBegin = statements.substring(toStart + 5);

            tasks.add(tasksCount, new Event(eventName, fromBegin, toBegin));
            System.out.println("------------------------------------------");
            System.out.println("Got it. I've added this task:\n" + tasks.get(tasksCount).toString());
            tasksCount += 1;
            System.out.println("Now you have " + tasksCount + " tasks in the list.\n");
            System.out.println("------------------------------------------");
        }
    }

    /**
     * Delete a task
     * @param statements Commands inputted by the user
     * @throws IPChatExceptions throws exception if user wants to delete a non existent task number
     */
    public static void deleteTasks(String statements) throws IPChatExceptions {
        if (tasksCount != 0) {
            try {
                int finalNum = Integer.parseInt(statements.substring(statements.lastIndexOf(" ") + 1)) - 1;
                Task removeTask = tasks.get(finalNum);
                tasks.remove(finalNum);
                tasksCount--;
                System.out.println("------------------------------------------");
                System.out.println("Deleting the following items");
                System.out.println("  " + removeTask.toString() + "\n" + "\nYou now have " + tasksCount + " tasks left.\n");
                System.out.println("------------------------------------------");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Task number !!");
            }
        } else {
            throw new IPChatExceptions("Please give tasks to delete!");
        }
    }

    /**
     *  Find the tasks by keywords
     * @param statements Commands inputted by the user
     */
    public static void findTasks (String statements) {
        String[] contents = statements.split(" ", 2);
        String mainWord = contents[1];
        int matchCount = 0;

        ArrayList<String> neededTasks = new ArrayList<>();
        for (Task currentTasks: tasks) {
            if (currentTasks.toString().contains(mainWord)) {
                neededTasks.add(currentTasks.toString());
                matchCount += 1;
            }
        }

        if (matchCount != 0) {
            System.out.println("Here are the matching tasks as requested");
            System.out.println("------------------------------------------");
            for (int i = 0 ; i < neededTasks.size(); i += 1) {
                System.out.println((i + 1) + ". " + neededTasks.get(i) + "\n");
            }
        }
    }
}
