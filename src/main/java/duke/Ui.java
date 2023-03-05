package duke;

import duke.exceptions.DukeException;
import duke.exceptions.UserInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.exceptions.UserInputException.inputExceptionType.INVALID_TASK_TYPE;

/**
 * Driver class for user interaction, including input from user and output shown to user, and the processing involved.
 */
public class Ui {
    //String constants
    static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    static final String HELLO_MESSAGE = " Hello! I'm Duke\n" + "     What can I do for you?\n";
    static final String BYE_MESSAGE = " Bye. Hope to see you again soon!";
    static final String SEPARATOR = "____________________________________________________________";
    static final String COMMAND_LIST_WORD = "list";
    static final String COMMAND_MARK_WORD = "mark";
    static final String COMMAND_UNMARK_WORD = "unmark";
    static final String COMMAND_ADD_TODO_WORD = "todo";
    static final String COMMAND_ADD_DEADLINE_WORD = "deadline";
    static final String COMMAND_ADD_EVENT_WORD = "event";
    static final String COMMAND_DELETE_WORD = "delete";
    static final String COMMAND_EXIT_WORD = "bye";
    static final String LINE_PREFIX = "    ";
    //Scanner for user input
    private static final Scanner SCANNER = new Scanner(System.in);
    private final TaskList taskList;
    public Ui(TaskList taskList){
        this.taskList = taskList;
    }

    /**
     * Returns task read from text file
     * @param nextLine next line of task in string format
     * @return task decoded in task format
     * @throws DukeException if the task format is wrong in the text file
     */
    static Task parseTask(String nextLine) throws DukeException {
        final String[] split = nextLine.trim().split("\\|", 3);
        Task nextTask;
        switch (split[0]) {
            case "T":
                nextTask = new Todo(split[2]);
                break;
            case "D":
                nextTask = new Deadline(split[2]);
                break;
            case "E":
                nextTask = new Event(split[2]);
                break;
            default:
                throw new DukeException("Wrong task format");
        }
        boolean isTaskDone = (Integer.parseInt(split[1]) == 1);
        if (isTaskDone) {
            nextTask.toggleDone();
        }
        return nextTask;
    }
    void showWelcomeMessage() {
        showToUser("Hello from\n" + LOGO);
        showToUser(SEPARATOR);
        showToUser(HELLO_MESSAGE);
        showToUser(SEPARATOR);
    }
    String getUserInput() {
        return SCANNER.nextLine();
    }

    /**
     * Parse and execute user's input command, including list, add, mark, unmark, delete, find, and exit
     * @param userInputString user input in string format
     * @throws DukeException if input is invalid
     */
    void executeCommand(String userInputString) throws DukeException {
        showToUser(SEPARATOR);
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
            case COMMAND_LIST_WORD:
                ArrayList<Task> tasks = taskList.getTASK_LIST();
                showToUser("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    showToUser((i + 1) + ". " + tasks.get(i).toString());
                }
                break;
            case COMMAND_MARK_WORD:
                String taskMarkedAsDone = taskList.markAsDone(commandArgs);
                showToUser("Nice! I've marked this task as done:");
                showToUser(taskMarkedAsDone);
                break;
            case COMMAND_UNMARK_WORD:
                String taskMarkedAsNotDone = taskList.markAsNotDone(commandArgs);
                showToUser("OK, I've marked this task as not done yet:");
                showToUser(taskMarkedAsNotDone);
                break;
            case COMMAND_EXIT_WORD:
                exitProgram();
                break;
            case COMMAND_ADD_TODO_WORD:
            case COMMAND_ADD_DEADLINE_WORD:
            case COMMAND_ADD_EVENT_WORD:
                String newTaskString = taskList.addTask(commandType, commandArgs);
                showToUser("Got it. I've added this task:",
                        newTaskString,
                        "Now you have " + (taskList.getTaskListSize() + (taskList.getTaskListSize() == 1 ? " task" : " tasks") + " in the list."));
                break;
            case COMMAND_DELETE_WORD:
                String taskToBeRemoved = taskList.deleteTask(commandArgs);
                showToUser("Noted. I've removed this task:");
                showToUser(taskToBeRemoved);
                showToUser("Now you have " + (taskList.getTaskListSize() + (taskList.getTaskListSize() == 1 ? " task" : " tasks") + " in the list."));
                break;
            default:
                throw new UserInputException(INVALID_TASK_TYPE);
        }
        showToUser(SEPARATOR);
    }

    void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
    }

    /**
     * Split user's input into two parts, in the format of array of string
     * @param rawUserInput user input in string format
     * @return array of string of user inputs
     */
    String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else case: no parameters
    }
    void exitProgram() {
        showToUser(BYE_MESSAGE, SEPARATOR);
        System.exit(0);
    }
}
