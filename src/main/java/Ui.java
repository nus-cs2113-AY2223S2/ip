import java.util.Scanner;

public class Ui {

    private final String LOGO = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";



    private final String LINE = "    ____________________________________________________________\n";

    private final String SMALL_SPACE = "     ";
    private final String BIG_SPACE = "       ";

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readInput() {
        String input = in.nextLine();
        return input;
    }
    public void printLogo() {
        System.out.println(LOGO);
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Error loading from memory. Please restart the program.");
    }

    public void showLoadingSuccess() {
        System.out.println(LINE);
        System.out.print(SMALL_SPACE);
        System.out.println("All Tasks loaded from memory successfully.");
        System.out.println(LINE);
    }

    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
