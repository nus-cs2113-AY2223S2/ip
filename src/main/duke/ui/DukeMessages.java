package duke.ui;

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
    private static final String FILE_FATAL_ERROR = "Failure to make and read file! No data will be stored!";
    private static final String NOT_FOUND = " *Rummages around cabinet* Uh oh looks like your whatever you searched for"
            + " does not exist! Check for typos!";

    public DukeMessages() {
    }

    public void printHi() {
        System.out.println(HI);
    }

    public void printBye() {
        printDiv();
        System.out.println("\n" + BYE + "\n");
        printDiv();
    }

    public void printQuery() {
        System.out.println(QUERY);
    }

    public void printDiv() {
        System.out.println(DIV);
    }

    public void printPrompt() {
        System.out.print(PROMPT);
    }

    public void printError() {
        System.out.println(ERROR);
    }

    public void printReadFileError() {
        System.out.println(READ_FILE_ERROR);
    }

    public void printFileNotFoundError() {
        System.out.println(FILE_NOT_FOUND_ERROR);
    }

    public void printWriteFileError() {
        System.out.println(WRITE_FILE_ERROR);
    }

    public void printEmpty() {
        System.out.println(EMPTY);
    }

    public void printList() {
        System.out.println(LIST);
    }

    public void printNotNumber() {
        System.out.println(NOT_NUMBER);
    }

    public void printNotInList() {
        System.out.println(NOT_IN_LIST);
    }

    public void printMark() {
        System.out.println(MARK);
    }

    public void printUnmark() {
        System.out.println(UNMARK);
    }

    public void printTodo() {
        System.out.println(TODO);
    }

    public void printDeadline() {
        System.out.println(DEADLINE);
    }

    public void printEvent() {
        System.out.println(EVENT);
    }

    public void printFileFatalError() {
        System.out.println(FILE_FATAL_ERROR);
    }

    public void printNotFound() {
        System.out.println(NOT_FOUND);
    }

    public void printListSize(int count) {
        if (count == 1) {
            System.out.println("Looks like you have " + 1 + " thing on your list!");
        } else {
            System.out.println("Looks like you have " + count + " things on your list!");
        }
    }

    public  void printFound(int count) {
        if (count == 1) {
            System.out.println("Found " + count + " matching item! *Holds shining lump up towards the sky!* ");
        } else {
            System.out.println("Found " + count + " matching items! *Tosses items up into the air*\n *The items "
                    + "arranges themselves neatly on the counter!* ");
        }
    }

    public void printMarkError(String type) {
        System.out.println("It is already " + type + "ed! *Shakes head* ");
    }

    public void printDeleted(String task) {
        System.out.println("Roger!" + task + " removed!");
    }
}
