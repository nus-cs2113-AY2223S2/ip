package duke;

import java.util.Scanner;

public class Parser {

    public static String[] parse(Scanner input) {
        String text = input.nextLine(); // input the whole sentence into text
        return text.split(" ", 2);
    }
}
