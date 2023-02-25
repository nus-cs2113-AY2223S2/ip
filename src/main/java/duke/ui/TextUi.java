package duke.ui;

import duke.CommandCenter;
import duke.Common;

import java.util.Scanner;

public class TextUi {

    private static final String HELLO_IM_DUKE = "Hello! I'm Duke";
    private static final String KEYWORD_TO_SEE_THE_INSTRUCTIONS = "Please type 'help' if you want to see the instructions";
    private static final String ASKING_MESSAGE = "What can I do for you?";
    private String userInput;
    private Scanner in;
    private CommandCenter commandCenter;

    public TextUi() {
        this.in = new Scanner(System.in);
        this.commandCenter = new CommandCenter();
    }

    public void giveInputUntilExit() {
        while (true) {
            userInput = getInput(in);
            commandCenter.setVariables(userInput);
            commandCenter.handleInput();
        }
    }

    public String getInput(Scanner in) {
        String userInput;
        System.out.println(Common.HORIZONTAL_LINE);
        System.out.println();
        userInput = in.nextLine();
        System.out.println(Common.HORIZONTAL_LINE);
        return userInput;
    }

    public void showWelcomeMessage() {
        System.out.println(Common.HORIZONTAL_LINE);
        System.out.println(HELLO_IM_DUKE);
        System.out.println(KEYWORD_TO_SEE_THE_INSTRUCTIONS);
        System.out.println(ASKING_MESSAGE);
    }
}
