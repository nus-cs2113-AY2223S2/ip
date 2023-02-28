package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static TaskList tasks;

    public Duke(){
        try {
            Storage.checkFileAccess();
            tasks = new TaskList(Storage.load());
        } catch (FileNotFoundException err) {
            UI.printMessage("File not Found");
        } catch (IOException err) {
            UI.printMessage("Something went wrong: " + err.getMessage());
        }
        String input;
        Scanner in = new Scanner(System.in);
        UI.showWelcomeMessage();
        do {
            input = in.nextLine();
            if (DukeException.hasError(input)) {
                continue;
            }
            run(input);
        } while (!input.equals("bye"));
    }
    private static void run(String input) {
        Command c = Parser.parse(input);
        c.execute(tasks);
    }
    public static void main(String[] args) {
        new Duke();
    }
}
