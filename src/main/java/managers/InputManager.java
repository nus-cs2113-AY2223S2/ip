package managers;

import commands.Command;
import commands.ExitCommand;
import enums.ErrorDialogueTypes;
import translators.Parser;
import errors.InvalidDeadlineException;
import errors.InvalidEventException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A manager that connects the other three managers to process the command entered
 * by the user and produce an output.
 */
public class InputManager {
    private TaskManager tasks;
    private OutputDialogueManager display;
    private SaveManager storage;
    private Scanner scanner;

    /**
     * Constructs an InputManager object by creating Objects of the other three managers
     * and a new Scanner. Handles the exceptions that may occur when TaskManager gets
     * created.
     */
    public InputManager() {
        this.display = new OutputDialogueManager();
        this.storage = new SaveManager();
        this.scanner = new Scanner(System.in);
        try {
            this.tasks = new TaskManager(storage.initialiseData());
        } catch (IOException e) {
            display.printErrorDialogue(ErrorDialogueTypes.ERROR_WHEN_SAVING);
        } catch (InvalidDeadlineException e) {
            display.printErrorDialogue(ErrorDialogueTypes.DEADLINE_WRONG_FORMAT);
        } catch (InvalidEventException e) {
            display.printErrorDialogue(ErrorDialogueTypes.EVENT_WRONG_FORMAT);
        }
    }

    /**
     * Processes one input from the user according to the command provided, then return
     * a boolean value indicating if this processing can end.
     *
     * @return True if the command calls to end the program, else return false.
     */
    public boolean processOneInput() {
        String line = scanner.nextLine();
        boolean canStop = false;
        try {
            Command command = Parser.parse(line);
            command.execute(tasks, storage, display);
            canStop = ExitCommand.isDone(command);
        } catch (NumberFormatException e) {
            display.printErrorDialogue(ErrorDialogueTypes.INVALID_TASK_NUMBER);
            tasks.listAllItems(display);
        } catch (IndexOutOfBoundsException e) {
            display.printErrorDialogue(ErrorDialogueTypes.EMPTY_TASK_NAME);
        }
        return canStop;
    }
}

