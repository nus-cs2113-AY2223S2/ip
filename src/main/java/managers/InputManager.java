package managers;

import commands.Command;
import commands.ExitCommand;
import enums.ErrorDialogueTypes;
import translators.Parser;
import errors.InvalidDeadlineException;
import errors.InvalidEventException;

import java.io.IOException;
import java.util.Scanner;

public class InputManager {
    private TaskManager tasks;
    private OutputDialogueManager display;
    private SaveManager storage;
    private Scanner scanner;

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

