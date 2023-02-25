import java.util.ArrayList;
public class Ui {
    /** String to be printed if an error is detected */
    private static final String OOPS = "OOPS! ";
    /** String to be printed after the user enters a new prompt */
    private static final String LINEDIVIDER = "___________________________________________";

    /**
     * Prints list of String to terminal
     *
     * @param responseList ArrayList<String> to be printed.
     */
    public static void printResponse(ArrayList<String> responseList) {
        for (String s : responseList) {
            System.out.println(s);
        }
    }

    /**
     * Prints error message to terminal
     *
     * @param e Exception that occurred due to user input.
     */
    public static void printErrorMessage(Exception e) {
        System.out.println(Ui.OOPS + e.getMessage());
    }

    /**
     * Prints a sequence of characters to demarcate
     * each line of input by user
     */
    public static void printLineDivider() {
        System.out.println(Ui.LINEDIVIDER);
        System.out.println("");
    }

}
