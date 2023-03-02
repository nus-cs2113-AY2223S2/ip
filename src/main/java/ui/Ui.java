package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private final String LINE = "____________________________________________________________";
    private final Scanner IN;

    public Ui(InputStream in) {
        this.IN = new Scanner(in);
    }

    public Ui(File file) throws FileNotFoundException {
        this.IN = new Scanner(file);
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
        System.out.println(LINE);
        System.out.print(result);
        System.out.println(LINE);
    }

    public String getNextLineInput() {
        return IN.nextLine();
    }

    public boolean hasNextLineInput() {
        return IN.hasNextLine();
    }

    public void closeScanner() {
        IN.close();
    }
}
