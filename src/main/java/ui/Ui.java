package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Ui implements IUi {
    final static String LINEBREAK = "________________________________________\n";
    final static String COMMAND_TAB_STRING = "     ";
    final static String LINE_TAB_STRING = "    ";
    final static String WELCOME_STRING = "Hello! I'm Duke\nWhat can I do for you?";
    final static String GOODBYE_STRING = "Bye. Hope to see you again soon!";
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
        System.out.println(LINE_TAB_STRING + LINEBREAK.substring(0, LINEBREAK.length()-1));
        Arrays.stream(message.split("\n"))
                .map(item -> COMMAND_TAB_STRING + item + '\n')
                .forEach(System.out::print);
        System.out.println(LINE_TAB_STRING + LINEBREAK);
    }
    @Override
    public <T> void printSystemMessage(ArrayList<T> list) {
        System.out.println(LINE_TAB_STRING + LINEBREAK.substring(0, LINEBREAK.length()-1));
        IntStream.range(0, list.size())
            .mapToObj(index -> String.format("%s%d. %s", COMMAND_TAB_STRING, index+1, list.get(index)))
            .forEach(System.out::println);
        System.out.println(LINE_TAB_STRING + LINEBREAK);
    }
    /**
     * Prints greet message to user
     */
    @Override
    public void greet() {
        System.out.println(LOGO);
        printSystemMessage(WELCOME_STRING);
    }
    /**
     * Prints bye messgae to user
     */
    @Override
    public void bye() {
        printSystemMessage(GOODBYE_STRING);
    }
}
