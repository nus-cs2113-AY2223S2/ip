package io.github.haoyangw.rica;

import io.github.haoyangw.rica.exception.RicaException;
import io.github.haoyangw.rica.exception.RicaTaskException;
import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

import java.util.Scanner;

public class Rica {
    private static final String BYE_PHRASE = " Leaving so soon? Come back anytime, I'll be happy to help!";
    private static final String BYE_TRIGGER = "bye";
    private static final String DEADLINE_TRIGGER = "deadline";
    private static final String DELETE_TRIGGER = "delete";
    private static final String EVENT_TRIGGER = "event";
    private static final String LINE = "____________________________________________________________";
    private static final String LIST_TRIGGER = "list";
    private static final String MARK_TRIGGER = "mark";
    private static final String TODO_TRIGGER = "todo";
    private static final String UNKNOWN_CMD_ERROR = " ??? Sorry, I don't understand this command. Sent to the wrong bot? xD";
    private static final String UNMARK_TRIGGER = "unmark";
    private static TaskManager taskManager;
    private static TextUi textUi;

    private static TaskManager getTaskManager() {
        return Rica.taskManager;
    }

    private static TextUi getTextUi() {
        return Rica.textUi;
    }

    private static void printlnWithIndent(String line) {
        System.out.print("    ");
        System.out.println(line);
    }

    /**
     * Parse the command entered into CLI and execute the corresponding actions
     */
    private static void runCommands() throws RicaTaskException {
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            command = in.nextLine();
            Rica.getTextUi().printHeader();
            String[] params = command.split(" ");
            switch (params[0]) {
            case Rica.MARK_TRIGGER:
                int indexOfTodo = Integer.parseInt(params[1]) - 1;
                Rica.getTaskManager().markDone(indexOfTodo);
                break;
            case Rica.UNMARK_TRIGGER:
                indexOfTodo = Integer.parseInt(params[1]) - 1;
                Rica.getTaskManager().unmarkDone(indexOfTodo);
                break;
            case Rica.LIST_TRIGGER:
                Rica.getTaskManager().printTasks();
                break;
            case Rica.DEADLINE_TRIGGER:
            case Rica.EVENT_TRIGGER:
            case Rica.TODO_TRIGGER:
                try {
                    Rica.getTaskManager().createTaskFrom(command);
                } catch (RicaTaskException exception) {
                    Rica.getTextUi().printlnWithIndent(exception.getMessage());
                }
                break;
            case Rica.DELETE_TRIGGER:
                try {
                    Rica.getTaskManager().rmTask(command);
                } catch (RicaTaskException exception) {
                    Rica.getTextUi().printlnWithIndent(exception.getMessage());
                }
                break;
            case Rica.BYE_TRIGGER:
                Rica.getTextUi().printGoodbyeMessage();
                break;
            default:
                Rica.getTextUi().printlnWithIndent(UNKNOWN_CMD_ERROR);
            }
            Rica.getTextUi().printFooter();
        } while (!command.equals(Rica.BYE_TRIGGER));
    }

    public static void main(String[] args) {
        Rica.textUi = new TextUi();
        Rica.getTextUi().printWelcomeMessage();
        Rica.taskManager = new TaskManager();
        try {
            Rica.runCommands();
        } catch (RicaException exception) {
            Rica.getTextUi().printErrorMessage(exception);
        }
    }

}
