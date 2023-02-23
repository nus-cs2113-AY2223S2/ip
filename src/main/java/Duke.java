import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    final static String logo = "____   _   _  ____   _____   -        \n"
                             + "|  _ \\ | | | ||  _ \\ |  ___| | |    \n"
                             + "| | | || | | || | | || |__   | |      \n"
                             + "| | | || | | || | | ||  __|  | |      \n"
                             + "| |_| || |_| || |_| || |___   -      \n"
                             + "|____/ \\____/ |____/ |_____|  O     \n";
    final static String WELCOME_MESSAGE = "HELLLOO there! I am \n "
            + logo + '\n'
            + "Your personal robot assistant! \n"
            + "What can I do for you today? \n \n"
            + "You can start by adding items to a task list that I can generate, simply follow the format below: \n \n"
            + "Todos      : \"todo <insert todo task description>\" \n"
            + "Deadlines  : \"deadline <insert deadline task description> /by <insert date/time> \" \n"
            + "Events     : \"event <insert event description> /from <insert start date/time> /to <insert end date/time>\" \n \n"
            + "If you wish to view the full list of commands, simply type \"help\"! \n";

    final static String HELP = "\nNO WORRIES! Help is here! You may find the list of commands below useful. \n \n \n"
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
            + "Format      : \"deadline <insert deadline task description> /by <insert date/time> \" \n"
            + "Example use : \"deadline CS2040C Kattis Week 6 /by 2359hrs tmr\" \n \n"
            + "## Events ## \n"
            + "Description : Adds a new task with TWO time elements indicating the starting date/time and the ending date/time. \n"
            + "Format      : \"event <insert event description> /from <insert start date/time> /to <insert end date/time>\" \n"
            + "Example use : \"event Recess Week /from 20th Feb 2023 /to 24th Feb 2023\" \n \n"
            + "## Mark ## \n"
            + "Description : Crosses out tasks which are done, requires the task index on the list as an input. \n"
            + "Format      : \"mark <insert task index number>\" \n"
            + "Example use : \"mark 10\" \n \n"
            + "## Unmark ## \n"
            + "Description : Undo the cross of any tasks which are done, requires the task index on the list as an input. \n"
            + "Format      : \"unmark <insert task index number>\" \n"
            + "Example use : \"unmark 10\" \n \n \n"
            + "Hope this list has been informational to you! \n";

    final static String UNRECOGNIZABLE_ERROR = "\n\uD83D\uDE16 OOPS!!! I'm sorry, but I don't know what that means \uD83D\uDE1E\n"
                                             + "Try typing \"help\" to see the valid commands you can use!\n";
    final static String PREFIX_MISSING_INPUTS_ERROR = "\n\uD83D\uDE13 OOPS!!! The description of ";
    final static String SUFFIX_MISSING_INPUTS_ERROR = " cannot be empty.\n";
    final static String PREFIX_EMPTY_LIST_ERROR = "\n\uD83D\uDE20 HELLOO???!!! Your list is either EMPTY or does not contain tasks up to the index you inputted yet, so you cannot use ";
    final static String SUFFIX_EMPTY_LIST_ERROR = " command yet!! Try filling up the list first!\n";
    final static String PREFIX_LIST_LIMIT_ERROR = "\n\uD83D\uDE05 SORRY MATE!!! I can only take in up to 100 tasks for now... \uD83D\uDE47 So please only ";
    final static String SUFFIX_LIST_LIMIT_ERROR = " up to that amount and not anything more!\n";
    final static String FORMAT_CONVERT_ERROR = "\n\uD83D\uDE31 MATE!!! Either you are giving me too big of a number for me to handle or putting words when I am expecting a number.\n"
                                             + "If it is the former I can only count up to 2147483647 for now... Please lower your expectations! \uD83D\uDE29 \n"
                                             + "As for the latter, please give me proper inputs so that I can work things out for you okay? \uD83D\uDE11 \n";

    public static void printList(Task[] l1, int currListIndex) {
        int index;
        if (l1[0] == null) {
            System.out.println('\n' + "MATE! \uD83D\uDE24 Your list is empty!" + '\n');
            return;
        }
        System.out.println("\nTASKS LIST\n");
        for (int i = 0; i < currListIndex; i += 1) {
            index = i + 1;
            System.out.println(index + ". " + l1[i].toString());
        }
        System.out.println();
    }

    public static void printMarked(boolean isChanged, String icon, String description) {
        if (!isChanged) {
            System.out.println('\n' + "Task is originally marked as done." + '\n');
        } else {
            System.out.println('\n' + "Nice! I've marked this task as done:");
            System.out.println("  [" + icon + "] " + description + '\n');
        }
    }
    public static void printUnmarked(boolean isChanged, String icon, String description) {
        if (!isChanged) {
            System.out.println('\n' + "Task is originally marked as not done." + '\n');
        } else {
            System.out.println('\n' + "OK, I've marked this task as not done yet:");
            System.out.println("  [" + icon + "] " + description + '\n');
        }
    }

    public static void printAdded(Task task, int taskSize) {
        System.out.println('\n' + "Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskSize + " tasks in the list." + '\n');
    }

    public static String[] parseCommands(String line) {
        String[] lineSpaced = line.split(" ");
        for (int i = 2; i < lineSpaced.length; i++) {
            lineSpaced[1] = lineSpaced[1].concat(" " + lineSpaced[i]);
        }
        return lineSpaced;
    }
    public static String[] parseDeadline(String description) throws IncompleteInputException {
        String[] parsed = description.split(" /by");
        if (parsed.length < 2) {
            throw new IncompleteInputException("Deadline is missing \"BY\"!\n");
        }
        return parsed;
    }

    public static String[] parseEvent(String description) throws IncompleteInputException {
        String[] parsed = description.split(" /", 3);
        if (parsed.length < 3) {
            throw new IncompleteInputException("Event is missing either \"FROM\" or \"TO\"!\n");
        }
        parsed[1] = parsed[1].substring(4);
        parsed[2] = parsed[2].substring(2);
        return parsed;
    }

    public static void printLineSpaced(String [] line) {
        for (String a : line) {
            System.out.print(a + " ");
        }
    }

    public static void main(String[] args) {

        System.out.println(WELCOME_MESSAGE);
        Task[] tasksList = new Task[100];
        Scanner in = new Scanner(System.in);
        String line, commandWord, description;
        int taskListIndex, currTaskNumber;
        String[] lineSpaced;
        boolean isChanged = false;

        line = in.nextLine();
        lineSpaced = parseCommands(line);
        currTaskNumber = 0;
        while(!lineSpaced[0].equals("bye")) {
            commandWord = lineSpaced[0];
            switch (commandWord) {
            case "list":
                printList(tasksList, currTaskNumber);
                break;
            case "mark":
                try {
                    description = lineSpaced[1];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_MISSING_INPUTS_ERROR + commandWord + SUFFIX_MISSING_INPUTS_ERROR);
                    break;
                }
                try {
                    taskListIndex = Integer.parseInt(description) - 1;
                    isChanged = false;
                    if (!tasksList[taskListIndex].getIsDone()) {
                        tasksList[taskListIndex].markAsDone();
                        isChanged = true;
                    }
                    printMarked(isChanged, tasksList[taskListIndex].getStatusIcon(), tasksList[taskListIndex].getDescription());
                } catch (NullPointerException nu) {
                    System.out.println(PREFIX_EMPTY_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIST_ERROR);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_LIST_LIMIT_ERROR + commandWord + SUFFIX_LIST_LIMIT_ERROR);
                } catch (NumberFormatException fe) {
                    System.out.println(FORMAT_CONVERT_ERROR);
                }
                break;
            case "unmark":
                try {
                    description = lineSpaced[1];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_MISSING_INPUTS_ERROR + commandWord + SUFFIX_MISSING_INPUTS_ERROR);
                    break;
                }
                try {
                    taskListIndex = Integer.parseInt(description) - 1;
                    isChanged = false;
                    if (tasksList[taskListIndex].getIsDone()) {
                        tasksList[taskListIndex].markAsUndone();
                        isChanged = true;
                    }
                    printUnmarked(isChanged, tasksList[taskListIndex].getStatusIcon(), tasksList[taskListIndex].getDescription());
                } catch (NullPointerException nu) {
                    System.out.println(PREFIX_EMPTY_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIST_ERROR);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_LIST_LIMIT_ERROR + commandWord + SUFFIX_LIST_LIMIT_ERROR);
                } catch (NumberFormatException fe) {
                    System.out.println(FORMAT_CONVERT_ERROR);
                }
                break;
            case "todo":
                try {
                    description = lineSpaced[1];
                    tasksList[currTaskNumber] = new ToDo(description);
                    printAdded(tasksList[currTaskNumber], currTaskNumber + 1);
                    currTaskNumber++;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_MISSING_INPUTS_ERROR + commandWord + SUFFIX_MISSING_INPUTS_ERROR);
                } catch (NullPointerException nu) {
                    System.out.println(PREFIX_EMPTY_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIST_ERROR);
                }
                break;
            case "deadline":
                try {
                    lineSpaced = parseDeadline(line);
                    tasksList[currTaskNumber] = new Deadline(lineSpaced[0].substring(commandWord.length()+1), lineSpaced[1]);
                    printAdded(tasksList[currTaskNumber], currTaskNumber+1);
                    currTaskNumber++;
                } catch (IncompleteInputException inc) {
                    System.out.println("\nError in inputs!");
                    System.out.println("Exception occurred: " + inc);
                }
                break;
            case "event":
                try {
                    lineSpaced = parseEvent(line);
                    tasksList[currTaskNumber] = new Event(lineSpaced[0].substring(commandWord.length()+1), lineSpaced[1],lineSpaced[2]);
                    printAdded(tasksList[currTaskNumber], currTaskNumber+1);
                    currTaskNumber++;
                } catch (IncompleteInputException inc) {
                    System.out.println("\nError in inputs!");
                    System.out.println("Exception occurred: " + inc);
                }
                break;
            case "help" :
                System.out.println(HELP);
                break;
            default:
                System.out.println(UNRECOGNIZABLE_ERROR);
            }
            line = in.nextLine();
            lineSpaced = parseCommands(line);
        }
        System.out.println('\n' + "Bye. Hope to see you again soon!");
    }
}
