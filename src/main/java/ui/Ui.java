package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.YELLOW_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;

public class Ui implements IUi {
    final static String LINEBREAK = "________________________________________\n";
    final static String COMMAND_TAB_STRING = "     ";
    final static String LINE_TAB_STRING = "    ";
    final static String SYSTEM_LINE_SEPARATOR = LINE_TAB_STRING + LINEBREAK;
    final static String ERR_LINE_SEPARATOR = colorize(LINE_TAB_STRING + LINEBREAK, RED_TEXT());
    final static String WELCOME_STRING = colorize("Hello! I'm Duke\nWhat can I do for you?", YELLOW_TEXT());
    final static String GOODBYE_STRING = colorize("Bye. Hope to see you again soon!", YELLOW_TEXT());
    final static String LOGO = " ____        _        \n"
                             + "|  _ \\ _   _| | _____ \n"
                             + "| | | | | | | |/ / _ \\\n"
                             + "| |_| | |_| |   <  __/\n"
                             + "|____/ \\__,_|_|\\_\\___|\n";

                             
    /**
     * Streams message and prints each line of message based
     * on new line feed.
     * @param message Output message to print
     */
    @Override
    public void printSystemMessage(String message) {
        System.out.print(SYSTEM_LINE_SEPARATOR);
        Arrays.stream(message.split("\n"))
                .map(item -> colorize(COMMAND_TAB_STRING + item + '\n', GREEN_TEXT()))
                .forEach(System.out::print);
                
        System.out.println(SYSTEM_LINE_SEPARATOR);
    }
    @Override
    public <T> void printSystemMessage(ArrayList<T> list) {
        System.out.print(SYSTEM_LINE_SEPARATOR);
        IntStream.range(0, list.size())
            .mapToObj(index -> colorize(String.format("%s%d. %s", COMMAND_TAB_STRING, index+1, list.get(index)),GREEN_TEXT()))
            .forEach(System.out::println);

        System.out.println(SYSTEM_LINE_SEPARATOR);
    }
    /**
     * Prints greet message to user
     */
    @Override
    public void greet() {
        System.out.println(colorize(LOGO, YELLOW_TEXT()));
        printSystemMessage(WELCOME_STRING);
    }
    /**
     * Prints bye messgae to user
     */
    @Override
    public void bye() {
        printSystemMessage(GOODBYE_STRING);
    }
    @Override
    public void printSystemErrorMessage(String message) {
        System.out.print(SYSTEM_LINE_SEPARATOR);
        message = colorize(message, RED_TEXT());
        Arrays.stream(message.split("\n"))
                .map(item -> COMMAND_TAB_STRING + item + '\n')
                .forEach(System.out::print);
                
        System.out.println(SYSTEM_LINE_SEPARATOR);
    }
}
