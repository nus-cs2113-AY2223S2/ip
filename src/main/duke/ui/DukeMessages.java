package duke.ui;

/**
 * Text UI for the application.
 */
public class DukeMessages {
    private static final String HI = "\n" +
            "██████╗░░█████╗░██████╗░\n" +
            "██╔══██╗██╔══██╗██╔══██╗\n" +
            "██████╦╝██║░░██║██████╦╝\n" +
            "██╔══██╗██║░░██║██╔══██╗\n" +
            "██████╦╝╚█████╔╝██████╦╝\n" +
            "╚═════╝░░╚════╝░╚═════╝░" + "\n";
    private static final String BYE =
            "██████╗░░█████╗░██████╗░  ░██████╗░█████╗░██╗░░░██╗░██████╗  ██████╗░██╗░░░██╗███████╗\n" +
            "██╔══██╗██╔══██╗██╔══██╗  ██╔════╝██╔══██╗╚██╗░██╔╝██╔════╝  ██╔══██╗╚██╗░██╔╝██╔════╝\n" +
            "██████╦╝██║░░██║██████╦╝  ╚█████╗░███████║░╚████╔╝░╚█████╗░  ██████╦╝░╚████╔╝░█████╗░░\n" +
            "██╔══██╗██║░░██║██╔══██╗  ░╚═══██╗██╔══██║░░╚██╔╝░░░╚═══██╗  ██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
            "██████╦╝╚█████╔╝██████╦╝  ██████╔╝██║░░██║░░░██║░░░██████╔╝  ██████╦╝░░░██║░░░███████╗\n" +
            "╚═════╝░░╚════╝░╚═════╝░  ╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░  ╚═════╝░░░░╚═╝░░░╚══════╝";
    private static final String DIV = "===============================================================================";
    private static final String QUERY = "What do you need from me boss?";
    private static final String PROMPT = "\nWhat you need: ";
    private static final String ERROR = "Wrong command boss! Try again!";
    private static final String READ_FILE_ERROR = "Reading file failed";
    private static final String FILE_NOT_FOUND_ERROR = "File not found\nCreating file";
    private static final String WRITE_FILE_ERROR = "Unforeseen error occurred! List not saved!";
    private static final String EMPTY = "*Tumbleweed passes by*\nUh oh! Looks like your list is empty!";
    private static final String LIST = "Here's your list boss! *Crosses arms and nods* : ";
    private static final String NOT_NUMBER = "This is not a number boss! Try again!";
    private static final String NOT_IN_LIST = "This number is not in the list boss! Try again!";
    private static final String MARK = "Bob commends you! *Nods head* ";
    private static final String UNMARK = "A mistake! *Shakes head* ";
    private static final String TODO = "Understood! *Salutes* Task added!";
    private static final String DEADLINE = "Understood *Salutes* Task with deadline added!\nRemember to complete it by "
            + "the deadline!";
    private static final String EVENT = "Understood *Salutes* Event added!\nRemember the starting time! Don't be late!";
    private static final String NOT_FOUND = " *Rummages around cabinet* Uh oh looks like your whatever you searched for"
            + " does not exist! Check for typos!";
    private static final String CLEAR = " *Tosses cabinet into incinerator* All records dumped boss!";
    /**
     * Initialization for the text UI.
     */
    public DukeMessages() {
    }

    /**
     * Prints startup message.
     */
    public void printHi() {
        System.out.println(HI);
    }

    /**
     * Prints exit message.
     */
    public void printBye() {
        printDiv();
        System.out.println("\n" + BYE + "\n");
        printDiv();
    }

    /**
     * Prints query portion of startup message.
     */
    public void printQuery() {
        System.out.println(QUERY);
    }

    /**
     * Prints a line to separate each block of printed messages.
     */
    public void printDiv() {
        System.out.println(DIV);
    }

    public void printPrompt() {
        System.out.print(PROMPT);
    }

    /**
     * Prints default error message.
     */
    public void printError() {
        System.out.println(ERROR);
    }

    /**
     * Prints error message when failed to read datafile.
     */
    public void printReadFileError() {
        System.out.println(READ_FILE_ERROR);
    }

    /**
     * Prints error message when <code>path</code> does not lead to datafile.
     */
    public void printFileNotFoundError() {
        System.out.println(FILE_NOT_FOUND_ERROR);
    }

    /**
     * Prints error message when unable to write to datafile.
     */
    public void printWriteFileError() {
        System.out.println(WRITE_FILE_ERROR);
    }

    /**
     * Prints message when list is empty.
     */
    public void printEmpty() {
        System.out.println(EMPTY);
    }

    /**
     * Prints message when list is non-empty.
     */
    public void printList() {
        System.out.println(LIST);
    }

    /**
     * Prints error message if user input is supposed to be a number but a number is not input.
     */
    public void printNotNumber() {
        System.out.println(NOT_NUMBER);
    }

    /**
     * Prints error message if user input for number does not exist in the list.
     */
    public void printNotInList() {
        System.out.println(NOT_IN_LIST);
    }

    /**
     * Prints message for successfully changing task status to marked.
     */
    public void printMark() {
        System.out.println(MARK);
    }

    /**
     * Prints message for successfully changing task status to unmarked.
     */
    public void printUnmark() {
        System.out.println(UNMARK);
    }

    /**
     * Prints message for successfully adding a <code>ToDo</code>.
     */
    public void printTodo() {
        System.out.println(TODO);
    }

    /**
     * Prints message for successfully adding a <code>Deadline</code>.
     */
    public void printDeadline() {
        System.out.println(DEADLINE);
    }

    /**
     * Prints message for successfully adding an <code>Event</code>.
     */
    public void printEvent() {
        System.out.println(EVENT);
    }

    /**
     * Prints message when the user input for <code>find</code> or <code>date</code> does not find any entries.
     */
    public void printNotFound() {
        System.out.println(NOT_FOUND);
    }

    /**
     * Prints message displaying the size of list.
     *
     * @param count number of items in the list.
     */
    public void printListSize(int count) {
        if (count == 1) {
            System.out.println("Looks like you have " + 1 + " thing on your list!");
        } else {
            System.out.println("Looks like you have " + count + " things on your list!");
        }
    }

    /**
     * Prints the number of matching entries when <code>find</code> or <code>date</code> is called.
     *
     * @param count number of entries found.
     */
    public  void printFound(int count) {
        if (count == 1) {
            System.out.println("Found " + count + " matching item! *Holds shining lump up towards the sky!* ");
        } else {
            System.out.println("Found " + count + " matching items! *Tosses items up into the air*\n *The items "
                    + "arranges themselves neatly on the counter!* ");
        }
    }

    /**
     * Prints error message when <code>Task</code> is already marked/unmarked.
     *
     * @param type Mark/Unmark depending on which is called by user input.
     */
    public void printMarkError(String type) {
        System.out.println("It is already " + type + "ed! *Shakes head* ");
    }

    /**
     * Prints message when <code>delete</code> is called.
     *
     * @param task The task deleted.
     */
    public void printDeleted(String task) {
        System.out.println("Roger!" + task + " removed!");
    }

    public void printClear() {
        System.out.println(CLEAR);
    }
}
