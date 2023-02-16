import exceptions.InvalidSyntaxException;
import exceptions.UnrecognizedInputException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import storage.TaskStorageManager;
import task.TaskList;
import ui.Parser;
import ui.UserInterface;
import ui.command.Command;

public class Duke {

    private static final Path PATH_SEP = Path.of(FileSystems.getDefault().getSeparator());
    // Stores to: TEMP/ip/task-storage
    public static final Path DEFAULT_FILE_PATH = Path.of(
            System.getProperty("java.io.tmpdir") + PATH_SEP + "ip" + PATH_SEP + "task-storage.dat");

    private final TaskStorageManager storage;
    private final TaskList tasks;
    private final UserInterface ui;
    private final Parser parser;


    public Duke(Path filePath) {
        ui = new UserInterface();
        parser = new Parser();

        storage = new TaskStorageManager(filePath);

        TaskList loadedTasks;
        try {
            loadedTasks = storage.loadTasks();
        } catch (IOException | ClassNotFoundException ex) {
            ui.printLoadFailure(ex);
            loadedTasks = new TaskList();
        }

        tasks = loadedTasks;
    }

    public void run() {
        ui.printGreeting();

        // Main user interaction loop
        while (parser.isRunning()) {
            String userInput = ui.getUserCommand();
            try {
                Command cmd = parser.parse(userInput);
                cmd.execute(tasks, storage, ui);
            } catch (UnrecognizedInputException ex) {
                ui.printUnrecognizedCommand();
            } catch (InvalidSyntaxException ex) {
                ui.printInvalidSyntax(ex);
            } catch (IndexOutOfBoundsException ex) {
                ui.printUnknownTask();
            }
        }

        ui.printGoodbye();
    }


    public static void main(String[] args) {
        Duke duke = new Duke(DEFAULT_FILE_PATH);
        duke.run();
    }
}
