package duke.ui;

import java.util.Scanner;

public class ReadUserInput {
    public static String[] readInput() {
        Scanner input = new Scanner(System.in);
        String[] userCommand = new String[2];
        userCommand[0] = input.next().toLowerCase();
        userCommand[1] = input.nextLine();
        return userCommand;
    }
}
