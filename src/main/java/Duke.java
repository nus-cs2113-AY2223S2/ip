import java.util.Scanner;
import java.util.ArrayList;

import duke.Task;
import duke.Deadline;
import duke.Event;
import duke.Todo;

public class Duke {
    private static int userTaskCount = 0;
    private static final String LINE = "--------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static ArrayList<Task> userTaskList = new ArrayList<>();
    private static String userInput;
    private static Scanner scan = new Scanner(System.in);

    private enum taskType {
        TODO, DEADLINE, EVENT
    }

    public static void reportError(String description) {
        System.out.println(LINE);
        System.out.println("[ERROR] " + description);
        System.out.println(LINE);
    }

    public static void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("How can I help you?\n");
        System.out.println("Here are some possibly useful commands:");
        System.out.println(LINE);
        System.out.println("/todo { description } - Add a todo task to ur task list.");
        System.out.println("/deadline { description } /by { cutoff } - Add a deadline task to ur task list.");
        System.out.println(
                "/event { description } /start { start time } /end { end time } - Add an event task to ur task list.");
        System.out.println("/list - List out all the tasks in ur task list.");
        System.out.println("/mark { numerical index } - Mark a specific task done.");
        System.out.println("/unmark { numerical index } - Mark a specific task undone.");
        System.out.println("/bye - Terminate the program.");
        System.out.println(LINE);
    }

    public static void goodbyeUser() {
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void addTask(String[] userInputArray, taskType variation) throws MissingCommandException {
        /** Check if task description is left empty **/
        if (userInputArray.length == 1) {
            throw new MissingCommandException("Please enter a description for your task!");
        }
        /** Handle different task types **/
        if (variation == taskType.TODO) {
            /** Handle todo tasks **/
            userTaskList.add(new Todo(userInput));
        } else if (variation == taskType.DEADLINE) {
            /** Handle deadline tasks **/
            if (!userInput.contains("/by")) {
                throw new MissingCommandException("Please specify a deadline via the /by command!");
            }
            userTaskList.add(new Deadline(userInput));
        } else if (variation == taskType.EVENT) {
            /** Handle event tasks **/
            if (!userInput.contains("/start") || !userInput.contains("/end")) {
                throw new MissingCommandException(
                        "Please specify both start and end dates/times via the /start and /end commands!");
            }
            userTaskList.add(new Event(userInput));
        }
        System.out.println(LINE);
        System.out.println("Added the following task:\n" + userTaskList.get(userTaskCount));
        System.out.println(LINE);
        userTaskCount++;

    }

    public static void listTasks() {
        if (userTaskCount == 0) {
            reportError("You have no tasks added!");
            return;
        }
        System.out.println("These are the following tasks you have:");
        for (int i = 0; i < userTaskCount; i++) {
            System.out.println(LINE);
            int index = i;
            index++;
            System.out.print(index + ".");
            System.out.println(userTaskList.get(i));
            System.out.println(LINE);
        }

    }

    public static void markTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            userTaskList.get(taskIndex).markAsDone();
            System.out.println(LINE + System.lineSeparator() + "The following task has been marked done: [X] "
                    + userTaskList.get(taskIndex).description + System.lineSeparator() + LINE);
        } catch (Exception e) {
            reportError("Please enter a valid numerical index of the task!");
            return;
        }
    }

    public static void unmarkTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            userTaskList.get(taskIndex).markAsUndone();
            System.out.println(LINE + System.lineSeparator() + "The following task has been marked undone: [ ] "
                    + userTaskList.get(taskIndex).description + System.lineSeparator() + LINE);
        } catch (Exception e) {
            // potential IndexOutOfBoundsException or NumberFormatException
            reportError("Please enter a valid numerical index of the task!");
        }
    }

    public static void deleteTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            System.out.println(LINE + System.lineSeparator() + "The following task has been removed: [ ] "
                    + userTaskList.get(taskIndex).description + System.lineSeparator() + LINE);
            userTaskList.remove(taskIndex);
            userTaskCount--;
        } catch (Exception e) {
            reportError("Please enter a valid numerical index of the task!");
        }
    }

    public static void processUserInput() {
        userInput = scan.nextLine();
        /** Handle single-word input commands with no arguments **/
        if (userInput.equals("/bye")) {
            goodbyeUser();
            scan.close();
            System.exit(0);
        }
        if (userInput.equals("/list")) {
            listTasks();
        } else {
            /** Handle multi-word input commands with required arguments **/
            String[] userInputArray = userInput.split(" ");
            if (userInputArray[0].equals("/todo")) {
                try {
                    addTask(userInputArray, taskType.TODO);
                } catch (MissingCommandException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInputArray[0].equals("/deadline")) {
                try {
                    addTask(userInputArray, taskType.DEADLINE);
                } catch (MissingCommandException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInputArray[0].equals("/event")) {
                try {
                    addTask(userInputArray, taskType.EVENT);
                } catch (MissingCommandException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInputArray[0].equals("/mark")) {
                markTask(userInputArray);
            } else if (userInputArray[0].equals("/unmark")) {
                unmarkTask(userInputArray);
            } else if (userInputArray[0].equals("/delete")) {
                deleteTask(userInputArray);

            } else {
                /** Handle non-command inputs **/
                reportError("Please enter a valid command!");
            }
        }

    }

    public static void main(String[] args) {
        greetUser();
        while (scan.hasNextLine()) {
            processUserInput();
        }
    }
}
