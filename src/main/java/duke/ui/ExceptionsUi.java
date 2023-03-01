package duke.ui;

public abstract class ExceptionsUi extends Ui {

    public static void printIOExceptionError() {
        System.out.print(BREAK_LINE + "\t(!) IOException error @ get existing file data.\n");
    }

    public static void printInvalidFileContentError() {
        System.out.print(BREAK_LINE + "\t(!) IllegalStateException: (!) Invalid file contents.\n" + BREAK_LINE);
    }

    public static void printArrayIndexOutOfBoundsExceptionError() {
        System.out.print(BREAK_LINE + "\t(!) Please provide the appropriate information for the task\n" + BREAK_LINE);
    }

    public static void printInvalidCommandError() {
        System.out.print(BREAK_LINE + "\t(!) Invalid command :(\n" + BREAK_LINE);
    }

    public static void printFindKeywordMissing() {
        System.out.print(BREAK_LINE + "\t(!) Please provide the keyword :(\n" + BREAK_LINE);
    }

    public static void printInvalidTaskNumberError() {
        System.out.print(BREAK_LINE + "\t(!) Invalid task number. Please try again :(" + BREAK_LINE);
    }
}
