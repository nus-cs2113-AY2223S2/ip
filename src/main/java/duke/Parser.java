package duke;

import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Parser {

    /**
     * Takes in the user input and split the sentence into 2 parts by the " "
     *
     * @param input line that user has inputted
     * @return an array of Strings that contains the command at index 0 and the description of the task at index 1
     */
    public static String[] parse(Scanner input) {
        String text = input.nextLine(); // input the whole sentence into text
        return text.split(" ", 2);
    }
}
