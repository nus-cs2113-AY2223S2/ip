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
     * @param s The text entered by the user
     * @throws InvalidCommandException The exception will be thrown if the command doesn't any duke command.
     */
    public static void process(String s) throws InvalidCommandException {
        Command command = new Command();
        command.execute(tasks, s);
    }

    /**
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


    private void start() {
        this.ui = new Ui();
        this.storage = new Storage();
        Parser.setKnownPatterns();
        ui.showWelcomeMessage();
        storage.initializeStorage(tasks, FILEPATH);
    }
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
