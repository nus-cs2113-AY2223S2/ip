package com.ethanyidong.bunny;

import java.util.Arrays;
import java.util.Scanner;

public class BunnyUI {
    private final static String DIVIDER = "____________________________________________________________";
    private final static String GLOBAL_INDENTATION = "\t";
    private final static String MESSAGE_INDENTATION = " ";

    private final Scanner in;

    private boolean isSuppressed;

    public BunnyUI() {
        this.in = new Scanner(System.in);
    }

    public void printMessage(String message) {
        this.printMessage(Arrays.asList(message.split("\n")));
    }

    public void printMessage(Iterable<String> messageLines) {
        if(this.isSuppressed) {
            return;
        }
        String output = "";
        output += GLOBAL_INDENTATION + DIVIDER + "\n";
        for (String line : messageLines) {
            output += GLOBAL_INDENTATION + MESSAGE_INDENTATION + line + "\n";
        }
        output += GLOBAL_INDENTATION + DIVIDER + "\n";

        System.out.print(output);
    }

    public String getNextCommandString() {
        return in.nextLine();
    }

    public void setIsSuppressed(boolean isSuppressed) {
        this.isSuppressed = isSuppressed;
    }
}
