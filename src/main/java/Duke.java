import exceptions.InvalidSyntaxException;
import exceptions.UnrecognizedInputException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import storage.TaskStorageManager;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import ui.Command;
import ui.UserInterface;

public class Duke {

    private static final Path PATH_SEP = Path.of(FileSystems.getDefault().getSeparator());
    // Stores to: TEMP/ip/task-storage
    public static final Path DEFAULT_FILE_PATH = Path.of(
            System.getProperty("java.io.tmpdir") + PATH_SEP + "ip" + PATH_SEP + "task-storage.dat");

    private final TaskStorageManager storage;
    private final TaskList tasks;
    private final UserInterface ui;

    private boolean isRunning;

    public Duke(Path filePath) {
        storage = new TaskStorageManager(filePath);
        tasks = storage.loadTasks();
        ui = new UserInterface();

        isRunning = true;
    }

    public void run() {
        ui.printGreeting();

        // Main user interaction loop
        while (isRunning) {
            String userInput = ui.getUserCommand();

            try {
                handleUserInput(userInput);
            } catch (UnrecognizedInputException ex) {
                System.out.println("Sorry, I don't recognize that command...");
            } catch (InvalidSyntaxException ex) {
                System.out.println("That doesn't look quite right, try: " + ex.getExpectedSyntax());
            }
        }

        ui.printGoodbye();
    }

    private void setUserTaskState(int index, boolean isDone) {
        tasks.getTask(index).setIsDone(isDone);

        if (isDone) {
            System.out.println("Nice! I've marked this task as done");
            System.out.println(tasks.getTask(index));
        } else {
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(tasks.getTask(index));
        }
    }

    private void handleAddUserTask(String cmd, String[] splitInput) throws InvalidSyntaxException {
        Task task = null;
        if (cmd.equals(Command.TODO.label)) {
            task = Task.createFromInput(splitInput);
        } else if (cmd.equals(Command.DEADLINE.label)) {
            task = DeadlineTask.createFromInput(splitInput);
        } else if (cmd.equals(Command.EVENT.label)) {
            task = EventTask.createFromInput(splitInput);
        }

        tasks.addTask(task);

        ui.printAddedTask(task);
        ui.printTaskCount(tasks);

        storage.saveTasks(tasks);
    }

    private void handleModifyUserTask(String cmd, String[] splitInput) throws InvalidSyntaxException {
        try {
            // Tasks are 0-indexed, user index is 1-indexed
            int userIndex = Integer.parseInt(splitInput[1]);
            int index = userIndex - 1;

            if (cmd.equals(Command.MARK.label)) {
                setUserTaskState(index, true);
            } else if (cmd.equals(Command.UNMARK.label)) {
                setUserTaskState(index, false);
            } else if (cmd.equals(Command.DELETE.label)) {
                Task removedTask = tasks.removeTask(index);

                ui.printRemovedTask(removedTask);
                ui.printTaskCount(tasks);
            }

            storage.saveTasks(tasks);

        } catch (NumberFormatException ex) {
            if (cmd.equals(Command.MARK.label)) {
                throw new InvalidSyntaxException(Command.MARK.expectedSyntax);
            } else if (cmd.equals(Command.UNMARK.label)) {
                throw new InvalidSyntaxException(Command.UNMARK.expectedSyntax);
            } else if (cmd.equals(Command.DELETE.label)) {
                throw new InvalidSyntaxException(Command.DELETE.expectedSyntax);
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Oops, not quite sure what task you're referring to...");
        }
    }


    private void handleUserInput(String userInput) throws UnrecognizedInputException, InvalidSyntaxException {
        String[] splitInput = userInput.split("\\s+", 2);
        // Case-insensitive to user input
        String cmd = splitInput[0].trim().toLowerCase();

        if (cmd.equals(Command.EXIT.label)) {
            isRunning = false;
        } else if (cmd.equals(Command.LIST.label)) {
            ui.printTasks(tasks);
        } else if (Command.MODIFY_TASK_COMMANDS.contains(cmd)) {
            handleModifyUserTask(cmd, splitInput);
        } else if (Command.ADD_TASK_COMMANDS.contains(cmd)) {
            handleAddUserTask(cmd, splitInput);
        } else {
            throw new UnrecognizedInputException();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(DEFAULT_FILE_PATH);
        duke.run();
    }
}
