package duke;
import java.util.Scanner;

public class Duke {

    private static final String HELLO_IM_DUKE = "Hello! I'm Duke";
    private static final String KEYWORD_TO_SEE_THE_INSTRUCTIONS = "Please type 'help' if you want to see the instructions";
    private static final String ASKING_MESSAGE = "What can I do for you?";
    public static final String MEANINGLESS_SENTENCE_AFTER_KEYWORD = "OPPS!!! The sentence after keyword has no meaning";
    public static String userInput;
    public static Scanner in = new Scanner(System.in);
    public static CommandCenter commandCenter = new CommandCenter();
    public static void main(String[] args) {
        start();
        giveInputUntilExit();
    }

    private static void start() {
        Common.dataFile.moveDataToArrayList();
        showWelcomeMessage();
    }

    private static void giveInputUntilExit() {
        while (true) {
            userInput = getInput(in);
            commandCenter.setVariables(userInput);
            commandCenter.handleInput();
        }
    }

    private static String getInput(Scanner in) {
        String userInput;
        System.out.println(Common.HORIZONTAL_LINE);
        System.out.println();
        userInput = in.nextLine();
        System.out.println(Common.HORIZONTAL_LINE);
        return userInput;
    }

    private static void showWelcomeMessage() {
        System.out.println(Common.HORIZONTAL_LINE);
        System.out.println(HELLO_IM_DUKE);
        System.out.println(KEYWORD_TO_SEE_THE_INSTRUCTIONS);
        System.out.println(ASKING_MESSAGE);
    }
}

