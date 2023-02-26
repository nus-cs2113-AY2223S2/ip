package duke;

import duke.exceptions.InvalidCommandException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    static String FILEPATH = "duke.txt";
    static ArrayList <Task> tasks = new ArrayList<>();


    public static void printLine() {
        System.out.println("____________________________________________________________");
    }


    /**
     * This function will start process user input and perform task correspondingly.
     * @param s The text entered by the user
     * @throws InvalidCommandException The exception will be thrown if the command doesn't any duke command.
     */
    public static void process(String s) throws InvalidCommandException {
        Command command = new Command();
        command.execute(tasks, s);
    }

    /**
     * Scan in the user input and trim extra white space.
     * If there is no input, continue to scan the next line for input.
     * @return The string that the user entered
     */
    private static String inputCommand() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        return s;
    }


    /**
     * Initialize essential class for the program to start.
     * Including Ui Class, Storage CLass, and setting patterns for Parser Class.
     */
    private void start() {
        this.ui = new Ui();
        this.storage = new Storage();
        Parser.setKnownPatterns();
        ui.showWelcomeMessage();
        storage.initializeStorage(tasks, FILEPATH);
    }

    /**
     * Run the program after initialization,
     * it will continue to get user input until the target word trigger the program to end.
     *
     */
    public void run() {
        start();
        String s = ui.getUserCommand();
        while (!s.equals("bye")) {
            try {
                process(s);
            } catch (InvalidCommandException e) {
                System.out.println("WOOF!! The command is not found, please type 'help' for more info");
                printLine();
            }
            s = inputCommand();
        }
        ui.showGoodByeMessage();
        storage.storeChanges(FILEPATH, tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
