import duke.DukeException;
import java.util.Scanner;

/**
 * Represents a parser that parses the user's commands to make sense of
 * what the user wants to do.
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs new TaskList and UI objects to be used to handle appropriate
     * task operations based on the user's commands.
     */
    public Parser() {
        tasks = new TaskList();
        ui = new Ui();
    }

    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Interprets user commands by checking the first word of the user input.
     * Checks whether user command is List, Find, Mark, Unmark, Delete, To-do,
     * Deadline or Event and calls the appropriate method to handle the command.
     * Handles unidentified commands by giving the user a message.
     */
    public void handleCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!(command.equals("bye"))) {
            try {
                String[] input = command.split(" ", 2); //only check the first word
                String firstWord = input[0];
                switch (firstWord) {
                    case "list" :
                        tasks.listTasks();
                        break;
                    case "find" :
                        tasks.findTask(input);
                        break;
                    case "mark":
                        tasks.markTask(input);
                        break;
                    case "unmark":
                        tasks.unmarkTask(input);
                        break;
                    case "delete":
                        tasks.deleteTask(input);
                        break;
                    case "todo":
                        tasks.addTodo(input);
                        break;
                    case "deadline":
                        tasks.addDeadline(input);
                        break;
                    case "event":
                        tasks.addEvent(input);
                        break;
                    default:
                        throw new DukeException();
                }
            } catch (DukeException e) {
                ui.handleUnidentifiedCommand();
            }
            command = in.nextLine();
        }
    }
}