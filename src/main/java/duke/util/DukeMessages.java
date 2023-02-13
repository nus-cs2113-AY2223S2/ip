package duke.util;

public class DukeMessages {
    static private final String HI = "\n" +
            "██████╗░░█████╗░██████╗░\n" +
            "██╔══██╗██╔══██╗██╔══██╗\n" +
            "██████╦╝██║░░██║██████╦╝\n" +
            "██╔══██╗██║░░██║██╔══██╗\n" +
            "██████╦╝╚█████╔╝██████╦╝\n" +
            "╚═════╝░░╚════╝░╚═════╝░" + "\n";
    static private final String BYE =
            "██████╗░░█████╗░██████╗░  ░██████╗░█████╗░██╗░░░██╗░██████╗  ██████╗░██╗░░░██╗███████╗\n" +
            "██╔══██╗██╔══██╗██╔══██╗  ██╔════╝██╔══██╗╚██╗░██╔╝██╔════╝  ██╔══██╗╚██╗░██╔╝██╔════╝\n" +
            "██████╦╝██║░░██║██████╦╝  ╚█████╗░███████║░╚████╔╝░╚█████╗░  ██████╦╝░╚████╔╝░█████╗░░\n" +
            "██╔══██╗██║░░██║██╔══██╗  ░╚═══██╗██╔══██║░░╚██╔╝░░░╚═══██╗  ██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
            "██████╦╝╚█████╔╝██████╦╝  ██████╔╝██║░░██║░░░██║░░░██████╔╝  ██████╦╝░░░██║░░░███████╗\n" +
            "╚═════╝░░╚════╝░╚═════╝░  ╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░  ╚═════╝░░░░╚═╝░░░╚══════╝";
    static private final String DIV = "===============================================================================";
    static private final String QUERY = "What do you need from me boss?";
    static private final String PROMPT = "\nWhat you need: ";
    static private final String ERROR = "Wrong command boss! Try again!";
    static private final String READ_FILE_ERROR = "Reading file failed";
    static private final String FILE_NOT_FOUND_ERROR = "File not found\nCreating file";
    static private final String WRITE_FILE_ERROR = "Unforeseen error occurred! List not saved!";
    static private final String EMPTY = "*Tumbleweed passes by*\nUh oh! Looks like your list is empty!";
    static private final String LIST = "Here's your list boss! *Crosses arms and nods* : ";
    static private final String NOT_NUMBER = "This is not a number boss! Try again!";
    static private final String NOT_IN_LIST = "This number is not in the list boss! Try again!";
    static private final String MARK = "Bob commends you! *Nods head* ";
    static private final String UNMARK = "A mistake! *Shakes head* ";
    static private final String TODO = "Understood! *Salutes* Task added!";
    static private final String DEADLINE = "Understood *Salutes* Task with deadline added!\nRemember to complete it by "
            + "the deadline!";
    static private final String EVENT = "Understood *Salutes* Event added!\nRemember the starting time! Don't be late!";

    static public void printHi() {
        System.out.println(HI);
    }

    static public void printBye() {
        System.out.println("\n" + BYE);
    }

    static public void printQuery() {
        System.out.println(QUERY);
    }

    static public void printDiv() {
        System.out.println(DIV);
    }

    static public void printPrompt() {
        System.out.print(PROMPT);
    }

    public static void printError() {
        System.out.println(ERROR);
    }

    public static void printReadFileError() {
        System.out.println(READ_FILE_ERROR);
    }

    public static void printFileNotFoundError() {
        System.out.println(FILE_NOT_FOUND_ERROR);
    }

    public static void printWriteFileError() {
        System.out.println(WRITE_FILE_ERROR);
    }

    public static void printEmpty() {
        System.out.println(EMPTY);
    }

    public static void printList() {
        System.out.println(LIST);
    }

    public static void printNotNumber() {
        System.out.println(NOT_NUMBER);
    }

    public static void printNotInList() {
        System.out.println(NOT_IN_LIST);
    }

    public static void printMark() {
        System.out.println(MARK);
    }

    public static void printUnmark() {
        System.out.println(UNMARK);
    }

    public static void printTodo() {
        System.out.println(TODO);
    }

    public static void printDeadline() {
        System.out.println(DEADLINE);
    }

    public static void printEvent() {
        System.out.println(EVENT);
    }
}
