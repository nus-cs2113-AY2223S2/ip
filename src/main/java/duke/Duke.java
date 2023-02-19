package duke;

import duke.command.Command;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.InvalidTaskFormatException;
import duke.exceptions.NoTaskException;
import duke.parser.Parser;
import duke.tasks.*;

import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    // commands


    // messages

    private static UI ui;
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;

    public static void main(String[] args) {
        ui = new UI();
        storage = new Storage();
        tasks = new TaskList(storage.read());
        parser = new Parser();

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

