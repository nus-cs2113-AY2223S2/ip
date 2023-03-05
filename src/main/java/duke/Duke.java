package duke;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.exceptions.UserInputException.inputExceptionType;
import static duke.exceptions.UserInputException.inputExceptionType.INVALID_TASK_TYPE;

public class Duke {
    //String constants
    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String HELLO_MESSAGE = " Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String BYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_MARK_WORD = "mark";
    private static final String COMMAND_UNMARK_WORD = "unmark";
    private static final String COMMAND_ADD_TODO_WORD = "todo";
    private static final String COMMAND_ADD_DEADLINE_WORD = "deadline";
    private static final String COMMAND_ADD_EVENT_WORD = "event";
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String LINE_PREFIX = "    ";
    //Scanner for user input
    private static final Scanner SCANNER = new Scanner(System.in);
    //Task list
    private static final ArrayList<Task> TASK_LIST = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        // reused from contacts Contacts1.java with modification
        showWelcomeMessage();
        while (true) {
            try{
                String userCommand = getUserInput();
                executeCommand(userCommand);
            }catch(DukeException err){
                showToUser(err.ProduceErrorMessage());
                showToUser(SEPARATOR);
            }
        }
    }

    private static void showWelcomeMessage() {
        showToUser("Hello from\n" + LOGO);
        showToUser(SEPARATOR);
        showToUser(HELLO_MESSAGE);
        showToUser(SEPARATOR);
    }

    private static String getUserInput() {
        return SCANNER.nextLine();
    }

    private static void executeCommand(String userInputString) throws DukeException {
        showToUser(SEPARATOR);
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case COMMAND_LIST_WORD:
            listTask();
            break;
        case COMMAND_MARK_WORD:
            markAsDone(commandArgs);
            break;
        case COMMAND_UNMARK_WORD:
            markAsNotDone(commandArgs);
            break;
        case COMMAND_EXIT_WORD:
            exitProgram();
            break;
        case COMMAND_ADD_TODO_WORD:
        case COMMAND_ADD_DEADLINE_WORD:
        case COMMAND_ADD_EVENT_WORD:
            addTask(commandType, commandArgs);
            break;
        case COMMAND_DELETE_WORD:
            deleteTask(commandArgs);
            break;
        default:
            throw new UserInputException(INVALID_TASK_TYPE);
        }
        showToUser(SEPARATOR);
    }

    public static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
    }

    private static void addTask(String newTaskType, String newTaskInfo) throws DukeException {
        switch (newTaskType) {
        case COMMAND_ADD_TODO_WORD:
            TASK_LIST.add(new Todo(newTaskInfo));
            break;
        case COMMAND_ADD_DEADLINE_WORD:

            TASK_LIST.add( new Deadline(newTaskInfo));
            break;
        case COMMAND_ADD_EVENT_WORD:

            TASK_LIST.add( new Event(newTaskInfo));
            break;
        default:
            throw new UserInputException(INVALID_TASK_TYPE);
        }
        showToUser("Got it. I've added this task:",
                TASK_LIST.get(TASK_LIST.size()-1).toString(),
                "Now you have " + (TASK_LIST.size()) + (TASK_LIST.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    private static void markAsDone(String taskNumber) {
        Task taskToBeMarked = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        taskToBeMarked.toggleDone();
        showToUser("Nice! I've marked this task as done:");
        showToUser(taskToBeMarked.toString());
    }

    private static void markAsNotDone(String taskNumber) {
        Task taskToBeUnmarked = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        taskToBeUnmarked.toggleDone();
        showToUser("OK, I've marked this task as not done yet:");
        showToUser(taskToBeUnmarked.toString());
    }
    private static void deleteTask(String taskNumber){
        Task taskToBeRemoved = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        showToUser("Noted. I've removed this task:");
        showToUser(taskToBeRemoved.toString());
        TASK_LIST.remove(Integer.parseInt(taskNumber)-1);
        showToUser("Now you have " + (TASK_LIST.size()) + (TASK_LIST.size() == 1 ? " task" : " tasks") + " in the list.");

    }
    private static void listTask() {
        showToUser("Here are the tasks in your list:");
        for (int i = 0; i < TASK_LIST.size(); i++) {
            showToUser((i + 1) + ". " + TASK_LIST.get(i).toString());
        }
    }

    private static void exitProgram() {
        showToUser(BYE_MESSAGE, SEPARATOR);
        System.exit(0);
    }

    //reused from Contacts1.java
    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else case: no parameters
    }
}
