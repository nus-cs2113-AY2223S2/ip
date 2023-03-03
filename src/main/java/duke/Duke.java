package duke;

import duke.exception.UnknownCommandException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String COMMAND_MARK_TASK = "mark";
    private static final String COMMAND_UNMARK_TASK = "unmark";

    private static final String COMMAND_LIST_TASK_ITEM = "list";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String FILE_PATH = "./data/duke.txt";

    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    public static String getInput(Scanner in) {
        String line = in.nextLine();
        return line;
    }

    public static void handleInput(String input) {
        try {
            processInput(input);
        } catch (UnknownCommandException e) {
            ui.showUnknownCmdErr();
        }
    }

    public static void processInput(String input) throws UnknownCommandException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case COMMAND_LIST_TASK_ITEM:
            ui.listTask(tasks.getTasks());
            break;
        case COMMAND_MARK_TASK:
            try {
                int taskNo = Integer.parseInt(words[1]) - 1;
                tasks.markTask(taskNo);
                ui.showTaskCompleted(tasks.getTasks(), taskNo);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showEmptyTaskNoErr();
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNoOutOfRangeErr();
            } catch (NumberFormatException e) {
                ui.showWrongTaskNoFormatErr();
            }
            break;
        case COMMAND_UNMARK_TASK:
            try {
                int taskNo = Integer.parseInt(words[1]) - 1;
                tasks.unmarkTask(taskNo);
                ui.showTaskIncomplete(tasks.getTasks(), taskNo);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showEmptyTaskNoErr();
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNoOutOfRangeErr();
            } catch (NumberFormatException e) {
                ui.showWrongTaskNoFormatErr();
            }
            break;
        case COMMAND_DELETE_TASK:
            try {
                int taskNo = Integer.parseInt(words[1]) - 1;
                ui.showTaskDeleted(tasks.getTasks(), taskNo);
                tasks.deleteTask(taskNo);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showEmptyTaskNoErr();
            } catch (IndexOutOfBoundsException e) {
                ui.showTaskNoOutOfRangeErr();
            } catch (NumberFormatException e) {
                ui.showWrongTaskNoFormatErr();
            }
            break;
        case COMMAND_ADD_DEADLINE:
            try {
                words = words[1].split(" /by ");
                String deadlineTaskName = words[0];
                String by = words[1];
                tasks.addDeadlineTask(deadlineTaskName, by);
                ui.showTaskAdded(tasks.getTasks());
            } catch (IndexOutOfBoundsException e) {
                ui.showEmptyDeadlineDescErr();
            }
            break;
        case COMMAND_ADD_EVENT:
            try {
                words = words[1].split(" /from ");
                String eventTaskName = words[0];
                words = words[1].split(" /to ");
                String from = words[0];
                String to = words[1];
                tasks.addEventTask(eventTaskName, from, to);
                ui.showTaskAdded(tasks.getTasks());
            } catch (IndexOutOfBoundsException e) {
                ui.showEmptyEventDescErr();
            }
            break;
        case COMMAND_ADD_TODO:
            try {
                String todoTaskName = words[1];
                tasks.addTodoTask(todoTaskName);
                ui.showTaskAdded(tasks.getTasks());
            } catch (IndexOutOfBoundsException e) {
                ui.showEmptyTodoDescErr();
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    public static void saveTaskListToFile() {
        try {
            storage.writeFile(tasks.getTasks());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("File not found/empty file. Creating new empty task list...");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = getInput(in);

            if (input.equals(COMMAND_EXIT)) {
                ui.showExitMessage();
                break;
            } else {
                handleInput(input);
                saveTaskListToFile();
            }
        }

    }

    public static void main(String[] args) {
        new Duke().run();
    }
}