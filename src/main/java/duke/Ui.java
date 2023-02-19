package duke;

public class Ui {

    static final String LINE = "\t____________________________________________________________";
    static final String COMMAND_BYE = "bye";
    static final String COMMAND_LIST = "list";
    static final String COMMAND_MARK = "mark";
    static final String COMMAND_UNMARK = "unmark";
    static final String COMMAND_EVENT = "event";
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_DELETE = "delete";
    static final String COMMAND_DEADLINE = "deadline";

    public static void printInvalidNumber(String taskType) {
        System.out.println(LINE);
        System.out.println("\t☹ Error! Invalid input.");
        System.out.println("\tPlease provide a task number for \"" + taskType + "\" command.");
        System.out.println("\tPlease use \"list\" command to see your task numbers.");
        System.out.println(LINE);
    }

    public static void printEmptyCommand(String taskType) {
        System.out.println(LINE);
        System.out.println("\t☹ Error! \"" + taskType + "\" command cannot be empty.");
        System.out.println("\tPlease provide more details");
        System.out.println(LINE);
    }

    public static void printInvalidFormat(String taskType) {
        System.out.println(LINE);
        System.out.println("\t☹ Error! Invalid format for \"" + taskType + "\" command.");
        System.out.println(LINE);
    }

    public static void printEmptyFile() {
        System.out.println(LINE);
        System.out.println("\tSaved file is empty.");
        System.out.println(LINE);
    }

    public static void printIncorrectTaskFormat() {
        System.out.println(LINE);
        System.out.println("\t☹ Error! Tasks saved in incorrect format.");
        System.out.println(LINE);
    }

    public static void printFileCreated(boolean fileCreated) {
        if (fileCreated) {
            System.out.println(LINE);
            System.out.println("\tFile \"duke.txt\" created!");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("\t☹ Error! Failed to create file.");
            System.out.println(LINE);
        }
    }

    public static void doCommandGreet() {
        System.out.println(LINE);
        System.out.println("\tHello! I'm Duke.");
        System.out.println("\tHow can I help you today?\n");
        System.out.println(LINE);
    }

    public static void doCommandBye() {
        System.out.println(LINE);
        System.out.println("\tBye! Remember to finish your tasks.\n");
        System.out.println(LINE);
    }
}
