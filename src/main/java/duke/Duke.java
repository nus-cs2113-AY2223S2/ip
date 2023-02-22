package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.tasks.*;
import duke.storage.Storage;
import duke.ui.UI;

import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.read());
        Parser parser = new Parser();

        Scanner scan = new Scanner(System.in);
        ui.printLogo();
        ui.greet();

        String input;
        boolean isExit = false;
        do {
            try {
                input = scan.nextLine();
                ui.printLine();
                Command command = parser.parse(input);
                // tasks saved after every command execution
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.print(e.getMessage());
                ui.printLine();
            }
        } while (!isExit);

        scan.close();
    }
}

