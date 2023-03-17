package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Parser {
    /**
     * Makes sense of user input, outputs the first word of the string which the user has inputted.
     * @param readInput The string which the user inputs into the command line.
     * @return The first word of the entire string which the user inputs, which is the command.
     */
    public static String parseType(String readInput) {
        String[] readInputAsArray = readInput.split(" ", 2);
        return readInputAsArray[0];
    }

    /**
     * Makes sense of user input, outputs the user input without the first word which the user has inputted.
     * @param readInput The string which the user inputs into the command line.
     * @return The string of the user input without the first word.
     */
    public static String parseTaskDescOrTaskNo(String readInput) {
        String[] readInputAsArray = readInput.split(" ", 2);
        String TaskDescOrTaskNo = " ";
        if (readInputAsArray.length > 1) {
            TaskDescOrTaskNo = readInputAsArray[1];
        }

        return TaskDescOrTaskNo;
    }
}
