import Exceptions.DukeException;
import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import UI.UserInterface;

import java.nio.file.Path;
import java.util.ArrayList;

public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final UserInterface ui;
    private final Path filepath;

    /**
     * The main function of Duke. The path is defined here. Therefore, if one wants to store multiple checklists, they
     * just need to edit the file path here.
     */
    public Duke(Path filePath) {
        this.filepath = filePath;
        ui = dukeStart();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile(filePath));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>());
        }

    }

    /**
     * Starts running Duke. This method will run Parser.readCommand which will wait for input. If an exception is thrown
     * the program will catch the exception, print the error message, and then call run() again.
     */
    public void run() {
        try {
            Parser.readCommand(ui, tasks, storage, this.filepath);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.run();
        }
    }

    /**
     * The main function of Duke. The path is defined here. Therefore, if one wants to store multiple checklists, they
     * just need to edit the file path here.
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "savefile");
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
        UserInterface ui = new UserInterface();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        return ui;
    }
}
