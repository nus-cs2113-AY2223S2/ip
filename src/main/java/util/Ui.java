package util;

import java.util.Scanner;

/**
 * Reads in input from the user
 */
public class Ui {
    public String getInput() {
        Scanner newScanner = new Scanner(System.in);
        String input = newScanner.nextLine();
        return input;
    }
}
