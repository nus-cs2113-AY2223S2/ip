package duke;

import duke.command.Command;
import duke.exceptions.CorruptSaveDataException;
import duke.parser.InputParser;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

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
        } catch (CorruptSaveDataException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }

        Scanner scan = new Scanner(System.in);
        ui.printLogo();
        ui.greet();

        String input;
        boolean isExit = false;
        do {
            try {
                input = scan.nextLine();
                ui.printLine();
                Command command = inputParser.parse(input);
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
