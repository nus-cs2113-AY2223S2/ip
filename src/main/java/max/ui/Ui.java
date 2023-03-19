package max.ui;

/**
 * Ui helps with the console output formatting for MAX
 * <p>
 * Ui provides an interface to format and output common patterns, such as:
 * 1. Printing borders
 * 2. Printing MAX's logo
 * 3. Printing errors
 * 4. Printing messages
 * 5. Wrapping messages with brackets
 */
public class Ui {
    private static final String BORDER = "────────────────────────────────────────────────────────────";
    private static final String CMD_PROMPT = "~$ ";
    private static final String MESSAGE_GREET = "Hello! I'm Max, your PAWsonal productivity assistant";
    private static final String MESSAGE_GREET_1 = "What can I do for you to MAXimize your day?";
    private static final String MESSAGE_IMPORTANT = "--- BORK BORK ---- THIS IS IMPORTANT! ---- BORK BORK ---";
    private static final String MESSAGE_ERROR = "Awoo? Max dug up an error:";
    private static final String NEWLINE = "\n";
    private static final String SINGLE_SPACE = " ";
    private static final String WRAPPER_OPEN = "[";
    private static final String WRAPPER_CLOSE = "]";

    /**
     * Construts a Ui object to handle console output
     */
    public Ui() {

    }

    private static String getLogo() {
        String logoString = "";
        logoString = logoString.concat(" /$$      /$$  /$$$$$$  /$$   /$$\n");
        logoString = logoString.concat("| $$$    /$$$ /$$__  $$| $$  / $$\n");
        logoString = logoString.concat("| $$$$  /$$$$| $$  \\ $$|  $$/ $$/       /^ ^\\\n");
        logoString = logoString.concat("| $$ $$/$$ $$| $$$$$$$$ \\  $$$$/       / 0 0 \\\n");
        logoString = logoString.concat("| $$  $$$| $$| $$__  $$  >$$  $$       V\\ Y /V\n");
        logoString = logoString.concat("| $$\\  $ | $$| $$  | $$ /$$/\\  $$       / - \\\n");
        logoString = logoString.concat("| $$ \\/  | $$| $$  | $$| $$  \\ $$      /    |\n");
        logoString = logoString.concat("|__/     |__/|__/  |__/|__/  |__/     V__)  ||");
        return logoString;
    }

    /**
     * Utility to help print messages to console
     *
     * @param message Message to be printed to console
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Notifies the user of an error, before printing it to console
     *
     * @param exceptionMessage errors to be printed
     */
    public void printError(String exceptionMessage) {
        notifyError();
        printMessage(exceptionMessage);
    }

    /**
     * Prints a pretty border to console
     */
    public void printBorder() {
        System.out.println(BORDER);
    }

    /**
     * Prints the command prompt cursor
     */
    public void printCommandPrompt() {
        System.out.print(CMD_PROMPT);
    }

    /**
     * Prints the standard welcome message by MAX
     */
    public void greet() {
        String logo = getLogo();
        printMessage(logo);
        printMessage(MESSAGE_GREET);
        printMessage(MESSAGE_GREET_1);
        printBorder();
    }

    /**
     * Notify the user via output of something important that will be printed
     */
    public void notifyImportant() {
        printMessage(MESSAGE_IMPORTANT);
    }

    /**
     * Notify the user via output of some error that will be printed
     */
    public void notifyError() {
        printMessage(MESSAGE_ERROR);
    }

    /**
     * Convenience method to get a single space character
     * <p>
     * This is used for padding and minor formatting requirements
     *
     * @return single space character
     */
    public String getSingleSpace() {
        return SINGLE_SPACE;
    }

    /**
     * Wraps an input message with brackets and returns the string
     * <p>
     * For example, message = "Hello, world!" <br>
     * Becomes: "[Hello, world!]"
     *
     * @param message to be wrapped
     * @return message wrapped with brackets
     */
    public String wrapMessage(String message) {
        String wrappedMessage = WRAPPER_OPEN;
        wrappedMessage += message;
        wrappedMessage += WRAPPER_CLOSE;
        return wrappedMessage;
    }

}
