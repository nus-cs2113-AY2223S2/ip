package max.Ui;

public class Ui {
    private static final String LOGO = "" +
            " /$$      /$$  /$$$$$$  /$$   /$$\n" +
            "| $$$    /$$$ /$$__  $$| $$  / $$\n" +
            "| $$$$  /$$$$| $$  \\ $$|  $$/ $$/       /^ ^\\\n" +
            "| $$ $$/$$ $$| $$$$$$$$ \\  $$$$/       / 0 0 \\\n" +
            "| $$  $$$| $$| $$__  $$  >$$  $$       V\\ Y /V\n" +
            "| $$\\  $ | $$| $$  | $$ /$$/\\  $$       / - \\\n" +
            "| $$ \\/  | $$| $$  | $$| $$  \\ $$      /    |\n" +
            "|__/     |__/|__/  |__/|__/  |__/     V__)  ||";
    private static final String BORDER = "────────────────────────────────────────────────────────────";
    private static final String CMD_PROMPT = "~$ ";
    private static final String MESSAGE_GREET = "Hello! I'm Max, your PAWsonal productivity assistant";
    private static final String MESSAGE_GREET_1 = "What can I do for you to MAXimize your day?";
    private static final String MESSAGE_IMPORTANT = "--- BORK BORK ---- THIS IS IMPORTANT! ---- BORK BORK ---";
    private static final String MESSAGE_ERROR = "Awoo? Max dug up an error:";

    public Ui() {

    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printError(String exceptionMessage) {
        notifyError();
        printMessage(exceptionMessage);
    }

    public void printBorder() {
        System.out.println(BORDER);
    }

    public void printBorderedMessage(String[] messages) {
        printBorder();
        for (String message : messages) {
            printMessage(message);
        }
        printBorder();
    }

    public void printCommandPrompt() {
        System.out.print(CMD_PROMPT);
    }

    public void greet() {
        printMessage(LOGO);
        printMessage(MESSAGE_GREET);
        printMessage(MESSAGE_GREET_1);
        printBorder();
    }

    public void notifyImportant() {
        printMessage(MESSAGE_IMPORTANT);
    }

    public void notifyError() {
        printMessage(MESSAGE_ERROR);
    }

}
