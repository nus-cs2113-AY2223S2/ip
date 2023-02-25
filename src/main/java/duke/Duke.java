package duke;

import duke.exceptions.InvalidCommandException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    static String FILEPATH = "duke.txt";
    static ArrayList <Task> tasks = new ArrayList<>();

    public static void addTask(Task t) {
        tasks.add(t);
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }


    /**
     * Show a short user guide to user. Including the usage of different comments.
     */
    public static void info() {
        printLine();
        System.out.println("This command is not valid, please read through the info and try again :)");
        System.out.println("Type: [todo] [something], and the system will add a new todo item to your list");
        System.out.println("Type: [event] [something] from: [when] to: [when], and the system will add an event and the timing");
        System.out.println("Type: [deadline] [something] by: [when], and the system will add a deadline");
        System.out.println("Type: [mark] [number], and the system will mark the item of the number as done");
        System.out.println("Type: [unmark] [number], and the system will unmark the item of the number.");
        System.out.println("Type: bye, to say goodbye to Duke!");
        System.out.println("Hope it helps!! woof a nice day ੯•໒꒱❤︎");
        printLine();
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
