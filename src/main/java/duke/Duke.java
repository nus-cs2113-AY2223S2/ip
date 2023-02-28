package duke;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import duke.command.ByeCommand;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Todo;
import duke.command.Command;

public class Duke implements Serializable {
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage();

    public static void main(String[] args) throws IOException {

        UI.printWelcome();
        start();

        String input;
        Scanner in = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            input = in.nextLine();
            Command command = Parser.parse(input);
            command.execute(tasks, storage);

            if (command instanceof ByeCommand) {
                isRunning = false;
            }
        }
    }

    public static void start() {
        try {
            storage.loadData(tasks);

        } catch (IOException e) {
            System.out.println("Unable to load");
        }
    }
}
