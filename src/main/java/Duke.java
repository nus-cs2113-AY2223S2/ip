import Exceptions.DukeException;
import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import UI.UserInterface;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UserInterface ui;
    public Duke(Path filePath) throws Exception {
        ui = dukeStart();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>());
        }

    }

    public void run() {
        try {
            Parser.readCommand(ui, tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.run();
        }
    }

    public static void main(String[] args) throws Exception {
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "savefile");
        new Duke(path).run();
    }

    // Printing the startup code
    public static UserInterface dukeStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        UserInterface ui =  new UserInterface();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        return ui;
    }

    // The bulk of the logic (should change name soon)


    //Code not needed for Level-1
    public static void dukeEcho(Scanner inputScanner) {
        while (true) {
            String echo = inputScanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(echo);
        }
    }
}
