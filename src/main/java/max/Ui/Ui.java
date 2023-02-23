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
        printMessage("Hello! I'm Max, your PAWsonal productivity assistant");
        printMessage("What can I do for you to MAXimize your day?");
        printBorder();
    }

    public void notifyImportant() {
        printMessage("--- BORK BORK ---- THIS IS IMPORTANT! ---- BORK BORK ---");
    }

    public void notifyError() {
        printMessage("Awoo? Max dug up an error:");
    }

}
