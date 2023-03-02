package duke;

import java.util.Scanner;

public class Ui {
    private final String LINE_SPACING = "\t____________________________________________________________";
    private Scanner scan;
    public Ui() {
        scan = new Scanner(System.in);
    }
    public void showLine() {
        System.out.println(LINE_SPACING);
    }
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(LINE_SPACING);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE_SPACING);
    }

    public String readCommand() {
        String input = scan.nextLine();
        return input;
    }

    public void showError(String message) {

    }

    public void showLoadingError() {

    }
}
