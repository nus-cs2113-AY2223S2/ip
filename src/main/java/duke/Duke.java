package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println("ERROR 404");
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        System.out.println("Hello, my name is Duke what can I help with today?");
        boolean isExit = false;
        while (!isExit) {
            try {
                storage.save(tasks.getList());
                Scanner in = new Scanner(System.in);
                String fullCommand = in.nextLine();
                Command c = Parser.parse(fullCommand);
                if (c != null) {
                    c.execute(tasks, storage);
                    isExit = c.isExit();
                }
            } catch (DukeException e) {
                System.out.println("ERROR 404");
            }
        }
    }
}