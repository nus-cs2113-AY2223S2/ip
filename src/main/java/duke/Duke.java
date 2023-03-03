package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.ByeCommand;
import duke.command.Command;

/**
 * Runs Duke.
 */
public class Duke {
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage();

    private static UI ui = new UI();

    public static void main(String[] args) throws IOException {

        ui.printWelcome();
        ui.printSeparator();
        start();

        String input;
        Scanner in = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            input = in.nextLine();

            try {
                Command command = Parser.getCommand(input, ui);
                command.execute(tasks, storage, ui);

                if (command instanceof ByeCommand) {
                    isRunning = false;
                }

            } catch (NullPointerException e) {
                ui.printCommandExecutionFailure();
                ui.printSeparator();
            }
        }
    }

    /**
     * Loads .json file containing previously saved tasks.
     */
    public static void start() {
        try {
            storage.loadData(tasks, ui);

        } catch (IOException e) {
            ui.printLoadingError();
            ui.printSeparator();
        }
    }
}
