import Exceptions.FileParseReadingException;
import Exceptions.IncompleteInputException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import storage.Storage;
import java.io.FileNotFoundException;


import java.util.ArrayList;
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

    final static String UNRECOGNIZABLE_ERROR = "\nOOPS!!! I'm sorry, but I don't know what that means"
                                             + "Try typing \"help\" to see the valid commands you can use!\n";
    final static String PREFIX_MISSING_INPUTS_ERROR = "\nOOPS!!! The description of ";
    final static String SUFFIX_MISSING_INPUTS_ERROR = " cannot be empty.\n";
    final static String PREFIX_EMPTY_LIMIT_LIST_ERROR = "\nHELLOO???!!! Your list is either EMPTY or does not contain tasks up to the index you inputted yet,\n"
                                                      + "so you cannot use the ";
    final static String SUFFIX_EMPTY_LIMIT_LIST_ERROR = " command yet!! Try filling up the list first!\n";
    final static String FORMAT_CONVERT_ERROR = "\nMATE!!! Either you are giving me too big of a number for me to handle or putting words when I am expecting a number.\n"
                                             + "If it is the former I can only count up to 2147483647 for now... Please lower your expectations!\n"
                                             + "As for the latter, please give me proper inputs so that I can work things out for you okay?\n";
    final static String PREFIX_MISSING_DESCRIPTION_ERROR = "\nError in inputs!\n"
                                                         + "Exception occurred: java.lang.StringIndexOutOfBoundsException: ";
    final static String SUFFIX_MISSING_DESCRIPTION_ERROR = " is missing the task description!\n";

    //private static FilesMain files = new FilesMain();
    public static void printList(ArrayList<Task> l1) {
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

    public static void printMarked(boolean isChanged, Task t) {
        if (!isChanged) {
            System.out.println('\n' + "Task is originally marked as done." + '\n');
        } else {
            System.out.println('\n' + "Nice! I've marked this task as done:");
            ToDo t1;
            Deadline d1;
            Event e1;
            switch (t.getType()){
            case "T":
                t1 = (ToDo) t;
                System.out.println("  " + t1 + '\n');
                break;
            case "D":
                d1 = (Deadline) t;
                System.out.println("  " + d1 + '\n');
                break;
            case "E":
                e1 = (Event) t;
                System.out.println("  " + e1 + '\n');
            }
        }
    }
    public static void printUnmarked(boolean isChanged, Task t) {
        if (!isChanged) {
            System.out.println('\n' + "Task is originally marked as not done." + '\n');
        } else {
            System.out.println('\n' + "OK, I've marked this task as not done yet:");
            ToDo t1;
            Deadline d1;
            Event e1;
            switch (t.getType()){
            case "T":
                t1 = (ToDo) t;
                System.out.println("  " + t1 + '\n');
                break;
            case "D":
                d1 = (Deadline) t;
                System.out.println("  " + d1 + '\n');
                break;
            case "E":
                e1 = (Event) t;
                System.out.println("  " + e1 + '\n');
            }
        }
    }

    public static void printAdded(Task task, int taskSize) {
        System.out.println('\n' + "Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskSize + " tasks in the list." + '\n');
    }
    public static void printDeleted(Task task, int taskSize) {
        System.out.println('\n' + "Noted. I've removed this task:");
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
        String[] parsed = description.split(" /by ");
        if (parsed.length < 2 || parsed[1].isEmpty()) {
            throw new IncompleteInputException("deadline is missing \"BY\"!\n");
        }
        return parsed;
    }

    public static String[] parseEvent(String description) throws IncompleteInputException {
        String[] parsed = description.split(" /", 3);
        if (parsed.length < 3) {
            throw new IncompleteInputException("event is missing either \"FROM\" or \"TO\"!\n");
        }
        parsed[1] = parsed[1].substring(5);
        if (parsed[1].isEmpty()) {
            throw new IncompleteInputException("event is missing \"FROM\"!\n");
        }
        parsed[2] = parsed[2].substring(3);
        if (parsed[2].isEmpty()) {
            throw new IncompleteInputException("event is missing \"TO\"!\n");
        }
        return parsed;
    }


    public static void main(String[] args) {

        Storage files = new Storage();
        System.out.println(WELCOME_MESSAGE);
        ArrayList<Task> tasksList = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String line, commandWord, description, deadlineBy, eventFrom, eventTo;
        int taskListIndex, currTaskNumber;
        Task tempTask;
        String[] lineSpaced;
        boolean isChanged;

        try {
            tasksList = Storage.readFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("\nError in finding file!");
            System.out.println("Exception occurred: " + e);
        } catch (FileParseReadingException fe) {
            System.out.println("\nError in reading file!");
            System.out.println("Exception occurred: " + fe);
        }
        line = in.nextLine();
        lineSpaced = parseCommands(line);
        currTaskNumber = tasksList.size();
        while(!lineSpaced[0].equals("bye")) {
            commandWord = lineSpaced[0];
            switch (commandWord) {
            case "list":
                printList(tasksList);
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
                    if (!tasksList.get(taskListIndex).getIsDone()) {
                        tasksList.get(taskListIndex).markAsDone();
                        isChanged = true;
                    }
                    printMarked(isChanged, tasksList.get(taskListIndex));
                } catch (NullPointerException nu) {
                    System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
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
                    if (tasksList.get(taskListIndex).getIsDone()) {
                        tasksList.get(taskListIndex).markAsUndone();
                        isChanged = true;
                    }
                    printUnmarked(isChanged, tasksList.get(taskListIndex));
                } catch (NullPointerException nu) {
                    System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
                } catch (NumberFormatException fe) {
                    System.out.println(FORMAT_CONVERT_ERROR);
                }
                break;
            case "todo":
                try {
                    description = lineSpaced[1];
                    tasksList.add(new ToDo(description));
                    printAdded(tasksList.get(currTaskNumber), currTaskNumber + 1);
                    currTaskNumber++;
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_MISSING_INPUTS_ERROR + commandWord + SUFFIX_MISSING_INPUTS_ERROR);
                } catch (NullPointerException nu) {
                    System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
                }
                break;
            case "deadline":
                try {
                    lineSpaced = parseDeadline(line);
                    description = lineSpaced[0].substring(commandWord.length()+1);
                    deadlineBy = lineSpaced[1];
                    tasksList.add(new Deadline(description, deadlineBy));
                    printAdded(tasksList.get(currTaskNumber), currTaskNumber+1);
                    currTaskNumber++;
                } catch (IncompleteInputException inc) {
                    System.out.println("\nError in inputs!");
                    System.out.println("Exception occurred: " + inc);
                } catch (StringIndexOutOfBoundsException st) {
                    System.out.println(PREFIX_MISSING_DESCRIPTION_ERROR + commandWord +SUFFIX_MISSING_DESCRIPTION_ERROR);
                }
                break;
            case "event":
                try {
                    lineSpaced = parseEvent(line);
                    description = lineSpaced[0].substring(commandWord.length()+1);
                    eventFrom = lineSpaced[1];
                    eventTo = lineSpaced[2];
                    tasksList.add(new Event(description, eventFrom, eventTo));
                    printAdded(tasksList.get(currTaskNumber), currTaskNumber+1);
                    currTaskNumber++;
                } catch (IncompleteInputException inc) {
                    System.out.println("\nError in inputs!");
                    System.out.println("Exception occurred: " + inc);
                } catch (StringIndexOutOfBoundsException st) {
                    System.out.println(PREFIX_MISSING_DESCRIPTION_ERROR + commandWord +SUFFIX_MISSING_DESCRIPTION_ERROR);
                }
                break;
            case "delete" :
                try {
                    description = lineSpaced[1];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(PREFIX_MISSING_INPUTS_ERROR + commandWord + SUFFIX_MISSING_INPUTS_ERROR);
                    break;
                }
                try {
                    taskListIndex = Integer.parseInt(description) - 1;
                    if (tasksList.isEmpty()) {
                        System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
                    } else {
                        tempTask = tasksList.get(taskListIndex);
                        tasksList.remove(taskListIndex);
                        currTaskNumber--;
                        printDeleted(tempTask, currTaskNumber);
                    }
                } catch (IndexOutOfBoundsException inc) {
                    System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + commandWord + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
                }
                break;
            case "help" :
                System.out.println(HELP);
                break;
            default:
                System.out.println(UNRECOGNIZABLE_ERROR);
            }
            Storage.saveToFiles(tasksList);
            line = in.nextLine();
            lineSpaced = parseCommands(line);
        }
////        for debug printing saved file
//        try {
//            Storage.printFileContents();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println('\n' + "Bye. Hope to see you again soon!");
    }
}
