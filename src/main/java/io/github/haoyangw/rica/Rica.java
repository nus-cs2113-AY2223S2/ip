package io.github.haoyangw.rica;

import io.github.haoyangw.rica.exception.RicaException;
import io.github.haoyangw.rica.exception.RicaTaskException;
import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

import java.util.Scanner;

public class Rica {
    private static final String BYE_TRIGGER = "bye";
    private static final String DEADLINE_TRIGGER = "deadline";
    private static final String DELETE_TRIGGER = "delete";
    private static final String EVENT_TRIGGER = "event";
    private static final String LIST_TRIGGER = "list";
    private static final String MARK_TRIGGER = "mark";
    private static final String TODO_TRIGGER = "todo";
    private static final String UNKNOWN_CMD_ERROR = " ??? Sorry, I don't understand this command. Sent to the wrong bot? xD";
    private static final String UNMARK_TRIGGER = "unmark";
    private final TaskManager taskManager;
    private final TextUi textUi;

    public Rica() {
        this.taskManager = new TaskManager();
        this.textUi = new TextUi();
    }

    private TaskManager getTaskManager() {
        return this.taskManager;
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    /**
     * Parse the command entered into CLI and execute the corresponding actions
     */
    private void runCommands() throws RicaTaskException {
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            command = in.nextLine();
            this.getTextUi().printHeader();
            String[] params = command.split(" ");
            switch (params[0]) {
            case Rica.MARK_TRIGGER:
                int indexOfTodo = Integer.parseInt(params[1]) - 1;
                this.getTaskManager().markDone(indexOfTodo);
                break;
            case Rica.UNMARK_TRIGGER:
                indexOfTodo = Integer.parseInt(params[1]) - 1;
                this.getTaskManager().unmarkDone(indexOfTodo);
                break;
            case Rica.LIST_TRIGGER:
                this.getTaskManager().printTasks();
                break;
            case Rica.DEADLINE_TRIGGER:
            case Rica.EVENT_TRIGGER:
            case Rica.TODO_TRIGGER:
                try {
                    this.getTaskManager().createTaskFrom(command);
                } catch (RicaTaskException exception) {
                    this.getTextUi().printlnWithIndent(exception.getMessage());
                }
                break;
            case Rica.DELETE_TRIGGER:
                try {
                    this.getTaskManager().rmTask(command);
                } catch (RicaTaskException exception) {
                    this.getTextUi().printlnWithIndent(exception.getMessage());
                }
                break;
            case Rica.BYE_TRIGGER:
                this.getTextUi().printGoodbyeMessage();
                break;
            default:
                this.getTextUi().printlnWithIndent(UNKNOWN_CMD_ERROR);
            }
            this.getTextUi().printFooter();
        } while (!command.equals(Rica.BYE_TRIGGER));
    }

    public void start() {
        this.getTextUi().printWelcomeMessage();
        try {
            this.runCommands();
        } catch (RicaException exception) {
            this.getTextUi().printErrorMessage(exception);
        }
    }

    public static void main(String[] args) {
        new Rica().start();
    }

}
