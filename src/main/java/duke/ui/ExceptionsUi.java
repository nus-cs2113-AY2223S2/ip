package duke.ui;

public class ExceptionsUi extends Ui {

    public void printIOExceptionError() {
        System.out.print("\t(!) IOException error: get existing file data.\n");
    }

    public void printIllegalStateExceptionError() {
        System.out.print("\t(!) IllegalStateException: (!) Invalid file contents.\n" + BREAK_LINE);
    }

    public void printArrayIndexOutOfBoundsExceptionError() {
        System.out.print(BREAK_LINE + "\t(!) Please provide the appropriate information for the task\n" + BREAK_LINE);
    }

    public void printInvalidCommandError() {
        System.out.print(BREAK_LINE + "\t(!) Invalid command :(\n" + BREAK_LINE);
    }

    public void printFindKeywordMissing() {
        System.out.print(BREAK_LINE +"\t(!) Please provide the keyword\n" + BREAK_LINE);
    }
}
