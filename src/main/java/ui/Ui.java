package ui;

import command.CommandType;
import exceptions.FileParseReadingException;
import exceptions.IncompleteInputException;
import exceptions.TaskListEmptyError;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final static String logo = "____   _   _  ____   _____   -        \n"
                             + "|  _ \\ | | | ||  _ \\ |  ___| | |    \n"
                             + "| | | || | | || | | || |__   | |      \n"
                             + "| | | || | | || | | ||  __|  | |      \n"
                             + "| |_| || |_| || |_| || |___   -      \n"
                             + "|____/ \\____/ |____/ |_____|  O     \n";
    private final static String WELCOME_MESSAGE = "\nHELLLOO there! I am \n "
            + logo + '\n'
            + "Your personal robot assistant! \n"
            + "What can I do for you today? \n \n"
            + "You can start by adding items to a task list that I can generate, simply follow the format below: \n \n"
            + "Todos      : \"todo <insert todo task description>\" \n"
            + "Deadlines  : \"deadline <insert deadline task description> /by <insert date in \"YYYY-MM-DD\" format> \" \n"
            + "Events     : \"event <insert event description> /from <insert date in \"YYYY-MM-DD\" format> /to <insert date in \"YYYY-MM-DD\" format>\" \n \n"
            + "If you wish to view the full list of commands, simply type \"help\"! \n";
    private final static String HELP = "\nNO WORRIES! Help is here! You may find the list of commands below useful. \n \n \n"
            + "COMMANDS LIST: \n \n \n"
            + "## List ## \n"
            + "Description : Displays the current tasks queued in the list. \n"
            + "Format      : \"list\" \n"
            + "Example use : \"list\" \n \n"
            + "## Todos ## \n"
            + "Description : Adds a new task without any time element in the task list. \n"
            + "Format      : \"todo <insert todo task description>\"\n"
            + "Example use : \"todo eat dinner\" \n \n"
            + "## Deadlines ## \n"
            + "Description : Adds a new task with ONE time element indicating the due date in the task list. \n"
            + "Format      : \"deadline <insert deadline task description> /by <insert date in \"YYYY-MM-DD\" format> \" \n"
            + "Example use : \"deadline CS2040C Kattis Week 6 /by 2023-03-02\" \n \n"
            + "## Events ## \n"
            + "Description : Adds a new task with TWO time elements indicating the starting date/time and the ending date/time. \n"
            + "Format      : \"event <insert event description> /from <insert date in \"YYYY-MM-DD\" format> /to <insert date in \"YYYY-MM-DD\" format>\" \n"
            + "Example use : \"event Recess Week /from 2023-03-02 /to 2023-03-05\" \n \n"
            + "## Delete ## \n"
            + "Description : Removes a task from the task list, if it exists. Requires the task index on the list as an input. \n"
            + "Format      : \"delete <insert task index number>\" \n"
            + "Example use : \"delete 10\" \n \n"
            + "## Mark ## \n"
            + "Description : Crosses out tasks which are done, requires the task index on the list as an input. \n"
            + "Format      : \"mark <insert task index number>\" \n"
            + "Example use : \"mark 10\" \n \n"
            + "## Unmark ## \n"
            + "Description : Undo the cross of any tasks which are done, requires the task index on the list as an input. \n"
            + "Format      : \"unmark <insert task index number>\" \n"
            + "Example use : \"unmark 10\" \n \n"
            + "## Bye ## \n"
            + "Description : Ends the programme. See you next time! \n"
            + "Format      : \"bye\" \n"
            + "Example use : \"bye\" \n \n \n"
            + "Hope this list has been informational to you! \n";
    private final static String BYE_MESSAGE = "\nBye. Hope to see you again soon!\n";
    private final static String DUDE_MAIN_ERROR = "\nError in my run method!"
                                                + "\nException occurred : ";
    private final static String UNRECOGNIZABLE_ERROR = "\nOOPS!!! I'm sorry, but I don't know what that means"
                                                     + "\nTry typing \"help\" to see the valid commands you can use!\n";
    private final static String MISSING_INPUTS_ERROR = "\nError in inputs!"
                                                     + "\nException occurred: ";
    private final static String PREFIX_EMPTY_LIMIT_LIST_ERROR = "\nError in finding index!"
                                                              + "\nException occurred: Your list is either EMPTY or does not contain tasks up to the index you inputted yet,\n"
                                                              + "so you cannot use the ";
    private final static String SUFFIX_EMPTY_LIMIT_LIST_ERROR = " command yet! Try filling up the list first!\n";
    private final static String MARKING_DEFAULT_ERROR = "\nError in marking / unmarking task!"
                                                      + "\nException occurred: ";
    private final static String TASK_ADDING_DEFAULT_ERROR = "\nError in adding task!"
                                                          + "\nException occurred: ";
    private final static String TASK_DELETING_DEFAULT_ERROR = "\nError in deleting task!"
                                                            + "\nException occurred: ";
    private final static String FORMAT_CONVERT_ERROR = "\nError in inputs!"
                                                     + "\nException occurred: The number is too big to process or the inputs contains words when it is supposed to be numbers."
                                                     + "\nIf it is the former I can only process up to 2,147,483,647 for now... Please lower your expectations!"
                                                     + "\nAs for the latter, please provide me with proper inputs!\n";
    private final static String MISSING_DESCRIPTION_ERROR = "\nError in description of inputs!"
                                                          + "\nException occurred: ";
    private final static String PARSING_STRING_ERROR = "\nError in parsing string!"
                                                     + "\nException occurred: ";
    private final static String PARSING_DATE_ERROR = "\nError in date format!"
                                                   + "\nException occurred: ";
    private final static String FILE_NOT_FOUND_ERROR = "\nError in finding file!"
                                                     + "\nException occurred: ";
    private final static String FILE_PARSE_READING_ERROR = "\nError in reading file!"
                                                         + "\nException occurred: ";
    private final static String FILE_IO_ERROR = "\nError in file IO!"
                                              + "\nException occurred: ";
    private final static String FILE_LOADING_DEFAULT_ERROR = "\nError in loading file!"
                                                           + "\nException occurred: ";

    private final static String LINE = "__________________________________________________________";

    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public static void showTaskList(ArrayList<Task> l1) {
        if (l1.size() == 0) {
            System.out.println('\n' + "MATE! \uD83D\uDE24 Your list is empty!" + '\n');
            return;
        }
        System.out.println("\nTASKS LIST\n");
        int i = 1;
        for (Task t : l1) {
            System.out.println(i + ". " + t.toString());
            i += 1;
        }
        System.out.println();
    }
    public static void showMarkTask(boolean isChanged, Task task, CommandType command) {
        if (!isChanged) {
            if (command == CommandType.MARK) {
                System.out.println('\n' + "Task is originally marked as done." + '\n');
            } else  {
                System.out.println('\n' + "Task is originally marked as not done." + '\n');
            }
        } else {
            if (command == CommandType.MARK) {
                System.out.println('\n' + "Nice! I've marked this task as done:");
            } else  {
                System.out.println('\n' + "OK, I've marked this task as not done yet:");
            }
            switch (task.getType()){
            case "T":
                ToDo t1 = (ToDo) task;
                System.out.println("  " + t1 + '\n');
                break;
            case "D":
                Deadline d1 = (Deadline) task;
                System.out.println("  " + d1 + '\n');
                break;
            case "E":
                Event e1 = (Event) task;
                System.out.println("  " + e1 + '\n');
            }
        }
    }
    public static void showTaskAdded(Task task, int taskSize) {
        System.out.println('\n' + "Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskSize + " tasks in the list." + '\n');
    }
    public static void showTaskDeleted(Task task, int taskSize) {
        System.out.println('\n' + "Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskSize + " tasks in the list." + '\n');
    }
    public static void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }
    public static void showBye() {
        System.out.println(BYE_MESSAGE);
    }
    public static void showHelp() {
        System.out.println(HELP);
    }
    public static void showLine() {
        System.out.println(LINE);
    }
    public static void showDudeMainError(Exception e) {
        if (e instanceof IOException) {
            System.out.println(FILE_IO_ERROR + e);
        } else {
        System.out.println(DUDE_MAIN_ERROR + e);
        }
    }
    public static void showUnrecognizableErrorMessage() {
        System.out.println(UNRECOGNIZABLE_ERROR);
    }
    public static void showLoadingErrorMessage(Exception e) {
        if (e instanceof FileNotFoundException) {
            System.out.println(FILE_NOT_FOUND_ERROR + e + "\nNew file and directory will be created.");
        } else if (e instanceof FileParseReadingException) {
            System.out.println(FILE_PARSE_READING_ERROR + e);
        } else {
            System.out.println(FILE_LOADING_DEFAULT_ERROR + e);
        }
    }
    public static void showMarkingErrorMessage(Exception e, CommandType type) {
        if (e instanceof NumberFormatException) {
            System.out.println(FORMAT_CONVERT_ERROR);
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException) {
            System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + type + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_INPUTS_ERROR + e);
        } else {
            System.out.println(MARKING_DEFAULT_ERROR + e);
        }
    }
    public static void showAddingTaskErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println(PARSING_STRING_ERROR + e);
        } else if (e instanceof DateTimeParseException) {
            System.out.println(PARSING_DATE_ERROR + e);
        } else {
            System.out.println(TASK_ADDING_DEFAULT_ERROR + e);
        }
    }
    public static void showDeletingTaskErrorMessage(Exception e, CommandType type) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException || e instanceof TaskListEmptyError) {
            System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + type + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(TASK_DELETING_DEFAULT_ERROR + e);
        }
    }
}
