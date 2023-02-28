package duke;

import duke.exception.DukeException;
import duke.exception.EmptyListError;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static TaskList tasks;

    private static void run(String input) {
        String c = Parser.getFirstCommand(input);
        Command command = new Command(tasks, c, input);
        command.execute();
    }
    public static void main(String[] args) {
        try {
            Storage.checkFileAccess();
            tasks = new TaskList(Storage.convertToList());
        } catch (FileNotFoundException err) {
            System.out.println("File not found");
        } catch (IOException err) {
            System.out.println("Something went wrong: " + err.getMessage());
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
}
