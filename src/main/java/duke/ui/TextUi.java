package duke.ui;

import duke.common.Common;
import duke.parser.Parser;

import java.util.Scanner;

/**
 * Provides the text-based user interface application.
 * Accepts user input, passes it to the Parser for processing, and displays output to the console.
 */
public class TextUi {

    private static final String HELLO_IM_DUKE = "Hello! I'm Duke";
    private static final String KEYWORD_TO_SEE_THE_INSTRUCTIONS = "Please type 'help' if you want to see the instructions";
    private static final String ASKING_MESSAGE = "What can I do for you?";

    private String userInput;
    private Scanner in;
    private Parser parser;

    public TextUi() {
        this.in = new Scanner(System.in);
        this.parser = new Parser();
    }

    /**
     * Receives user input, refines it, and passes it to the Parser for processing.
     * The loop continues until the user types the "bye" command to exit the application.
     */
    public void executeInputUntilExit() {
        while (true) {
            getInput();
            String refinedUserInput = userInput.trim().replaceAll("\\s+", " ");
            parser.splitKeywordAndDescription(refinedUserInput);
            parser.executeUserInput();
        }
    }

    private void getInput() {
        System.out.println(Common.HORIZONTAL_LINE);
        System.out.println();
        userInput = in.nextLine();
        System.out.println(Common.HORIZONTAL_LINE);
    }

    /** Displays the welcome message to the user.  */
    public void showWelcomeMessage() {
        System.out.println(Common.HORIZONTAL_LINE);
        System.out.println(HELLO_IM_DUKE);
        System.out.println(KEYWORD_TO_SEE_THE_INSTRUCTIONS);
        System.out.println(ASKING_MESSAGE);
    }

}
