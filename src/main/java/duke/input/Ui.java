package duke.input;

import duke.input.Parser;
import java.util.Scanner;

public class Ui {
    
    final static String BARRIER = "____________________________________________________________";
    final static String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    
    public void printGreeting() {
        String GREETING = BARRIER + "\n\nAhoy there! I be\n" + LOGO + "\nWhat can I do for ye?"+
        "\nFor a list of commands, type 'help' me hearties!\n" + BARRIER + "\n";
        System.out.println(GREETING);
    }

    public void printExit() {
        String EXIT = BARRIER + "\n\nFarewell! Hope to see ye again soon, ye scallywag!\n" + BARRIER;
        System.out.println(EXIT);
    }

    public void takeInput() {
        final Scanner CONSOLE = new Scanner(System.in);
        String input = CONSOLE.nextLine();
        Parser parser = new Parser();
        while (!input.equals("bye")) {
            parser.handleInput(input);
            input = CONSOLE.nextLine();
        }
    }
}