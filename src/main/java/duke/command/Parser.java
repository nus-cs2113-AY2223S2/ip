package duke.command;

import duke.Storage.Storage;
import duke.command.actionCommands.*;

import duke.command.taskCommands.*;

import duke.exception.InvalidTaskException;
import duke.tasks.TaskList;

import java.io.IOException;

import static duke.Storage.Storage.updateFile;
import static duke.main.Duke.printHorizontalLine;

/**
 * handle input command by user
 */
public class Parser {
    private boolean isExit = false;

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * process line input by user
     *
     * @param line    input by user
     * @param tasks   list of tasks
     * @param storage database of task list in local device
     */
    public void processLine(String line, TaskList tasks, Storage storage) {
        String[] args = line.split(" ", 2);
        Command newCommand;
        try {
            switch (args[0]) {
            case "bye":
                isExit = true;
                return;
            case "list":
                newCommand = new ListTasks();
                newCommand.processCommand(tasks, line);
                break;
            case "delete":
                newCommand = new DeleteTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "mark":
                newCommand = new MarkTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "unmark":
                newCommand = new UnmarkTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "todo":
                newCommand = new TodoTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "deadline":
                newCommand = new DeadlineTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "event":
                newCommand = new EventTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "find":
                newCommand = new FindTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            default:
                throw new InvalidTaskException();
            }
        } catch (InvalidTaskException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please enter all command parameter!");
            printHorizontalLine();
        }

        try {
            updateFile(tasks);
        } catch (IOException e) {
            System.out.println("I/O Error!");
        }
    }
}
