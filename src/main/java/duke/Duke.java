package duke;

import java.io.*;
import java.util.Scanner;
import duke.command.ByeCommand;
import duke.command.Command;

public class Duke implements Serializable {
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage();

    private static UI ui = new UI();

    public static void main(String[] args) throws IOException {

        ui.printWelcome();
        start();

        String input;
        Scanner in = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            input = in.nextLine();
            Command command = Parser.parse(input, ui);
            command.execute(tasks, storage, ui);

            if (command instanceof ByeCommand) {
                isRunning = false;
            }
        }
    }

    public static void start() {
        try {
            storage.loadData(tasks, ui);

        } catch (IOException e) {
            System.out.println("Unable to load");
        }
    }
}
