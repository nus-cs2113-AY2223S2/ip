package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Parser {
    public static String parseType(String readInput) {
        String[] readInputAsArray = readInput.split(" ", 2);
        return readInputAsArray[0];
    }
    public static String parseTaskDescOrTaskNo(String readInput) {
        String[] readInputAsArray = readInput.split(" ", 2);
        String TaskDescOrTaskNo = " ";
        if (readInputAsArray.length > 1) {
            TaskDescOrTaskNo = readInputAsArray[1];
        }
        return TaskDescOrTaskNo;
    }
}
