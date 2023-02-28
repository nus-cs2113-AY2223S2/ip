package Duke.DukeFunction;

import java.util.Scanner;

public class DukeUI {
    private static final String LINE = "____________________________________________________________";
    private static final String DukeLogo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String EditorLogo =  " _____ ____ ___ _____ ___  ____      _     ___ _   _ __________   __ _    _   _  ____\n" +
                                  "| ____|  _ \\_ _|_   _/ _ \\|  _ \\ _  | |   |_ _| | | |__  /_ _\\ \\ / // \\  | \\ | |/ ___|\n" +
                                  "|  _| | | | | |  | || | | | |_) (_) | |    | || | | | / / | | \\ V // _ \\ |  \\| | |  _\n" +
                                  "| |___| |_| | |  | || |_| |  _ < _  | |___ | || |_| |/ /_ | |  | |/ ___ \\| |\\  | |_| |\n" +
                                  "|_____|____/___| |_| \\___/|_| \\_(_) |_____|___|\\___//____|___| |_/_/   \\_\\_| \\_|\\____|\n";
    private static final String GoodbyeLogo =   "  ____  ___   ___  ____  ______   _______ _\n" +
                                    " / ___|/ _ \\ / _ \\|  _ \\| __ ) \\ / / ____| |\n" +
                                    "| |  _| | | | | | | | | |  _ \\\\ V /|  _| | |\n" +
                                    "| |_| | |_| | |_| | |_| | |_) || | | |___|_|\n" +
                                    " \\____|\\___/ \\___/|____/|____/ |_| |_____(_)\n";
    public Scanner in;

    /**
     * Constructor for DukeUI
     */
    public DukeUI() {
        in = new Scanner(System.in);
    }

    /**
     * Returns the line that reads from the command line.
     * @return String of the next line of input.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints the Duke logo.
     */
    public void printDukeLogo() { System.out.println("Hello from\n" + DukeLogo);}

    /**
     * Prints the Editor logo.
     */
    public void printEditorLogo(){
        System.out.println(EditorLogo);
    }

    /**
     * Prints all the greetings to the user.
     * Includes the Duke logo, editor logo, and the greeting message.
     */
    public void printGreeting(){
        printDukeLogo();
        printEditorLogo();
        System.out.println("Hello! I'm DukeRobot, edited by liuziyang");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints the Goodbye logo.
     */
    public void printGoodbyeLogo(){
        System.out.println(GoodbyeLogo);
    }
    public void printString(String s){
        System.out.println(s);
    }

    /**
     * Prints the message when an error occurs.
     * @param s String to be printed.
     */
    public void printError(String s){
        System.out.println("[>Error] " + s);
    }

    /**
     * Prints the dividing line.
     */
    public void printLine(){
        System.out.println(LINE);
    }
}
