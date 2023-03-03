package duke;

import duke.command.Command;
import duke.exceptions.CorruptSaveDataException;
import duke.parser.InputParser;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The main controller for the task manager. Runs indefinitely until the exit keyword is input by the user. Input
 * data is saved in the JSON format under the directory {projectDirectory}/data/save.txt.
 */
public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        Storage storage = new Storage();
        InputParser inputParser = new InputParser();
        TaskList tasks;
        try {
            String json = storage.read();
            tasks = new TaskList(json);

            // handle shutdown event
            TaskList finalTasks = tasks;
            Thread shutdownThread = new Thread(() -> {
                try {
                    storage.save(finalTasks.toJson());
                    ui.printExit();
                } catch (IOException e) {
                    ui.printSaveFailed();
                }
            });
            Runtime.getRuntime().addShutdownHook(shutdownThread);
        } catch (CorruptSaveDataException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }

        Scanner scan = new Scanner(System.in);
        ui.printLogo();
        ui.printHelp();
        ui.greet();

        String input;
        boolean isExit = false;
        do {
            try {
                input = scan.nextLine();
                Command command = inputParser.parse(input);
                ui.printLine();
                // tasks saved after every command execution
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (NoSuchElementException e) {
                // empty catch, prevents No line found output
            } catch (IOException e) {
                ui.printSaveFailed();
            } catch (Exception e) {
                ui.print(e.getMessage());
                ui.printLine();
            }
        } while (!isExit);

        scan.close();
    }
}
