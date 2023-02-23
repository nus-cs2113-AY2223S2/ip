package ui;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private final String line = "____________________________________________________________";

    private final Scanner in;

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public void greetUser() {
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
    }

    public void byeUser() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(line + '\n' + errorMessage + '\n' + line);
    }

    public void printDataLoadSuccess() {
        System.out.println(line + '\n' + "\"./data/duke.txt\" found.\nData loaded into Duke!" + '\n' + line);
    }

    public void printFileCreated() {
        System.out.println(line + '\n' + "No existing data found.\nCreated new file \"./data/duke.txt\"" + '\n' + line);
    }

    public void printCommandResult(String result) {
        if (result == null) {
            return;
        }
        System.out.println(result);
    }

    public String getNextLineInput() {
        String input = in.nextLine();
        return input;
    }
}
