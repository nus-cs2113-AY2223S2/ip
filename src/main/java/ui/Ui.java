package ui;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private final String LINE = "____________________________________________________________";

    private final Scanner IN;

    public Ui(InputStream in) {
        this.IN = new Scanner(in);
    }

    public void greetUser() {
        System.out.println(LINE + "\nHello! I'm Duke\nWhat can I do for you?\n" + LINE);
    }

    public void byeUser() {
        System.out.println(LINE + "\nBye. Hope to see you again soon!\n" + LINE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(LINE + '\n' + errorMessage + '\n' + LINE);
    }

    public void printDataLoadSuccess() {
        System.out.println(LINE + '\n' + "\"./data/duke.txt\" found.\nData loaded into Duke!" + '\n' + LINE);
    }

    public void printFileCreated() {
        System.out.println(LINE + '\n' + "No existing data found.\nCreated new file \"./data/duke.txt\"" + '\n' + LINE);
    }

    public void printCommandResult(String result) {
        if (result == null) {
            return;
        }
        System.out.println(result);
    }

    public String getNextLineInput() {
        String input = IN.nextLine();
        return input;
    }
}
