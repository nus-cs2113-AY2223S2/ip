import duke.DukeException;
import java.util.Scanner;

/**
 * Represents a parser that parses the user's commands to make sense of
 * what the user wants to do.
 */
public class Parser {
    private final TaskList tasks;

    /**
     * Constructs a new TaskList object to be used to handle appropriate
     * task operations based on the user's commands.
     */
    public Parser() {
        tasks = new TaskList();
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
                    //list tasks
                    case "list" :
                        tasks.listTasks();
                        break;
                    //find task
                    case "find" :
                        tasks.findTask(input);
                        break;
                    //mark task
                    case "mark":
                        tasks.markTask(input);
                        break;
                    //un-mark task
                    case "unmark":
                        tasks.unmarkTask(input);
                        break;
                    //delete task
                    case "delete":
                        tasks.deleteTask(input);
                        break;
                    //to-do
                    case "todo":
                        tasks.addTodo(input);
                        break;
                    //deadline
                    case "deadline":
                        tasks.addDeadline(input);
                        break;
                    //event
                    case "event":
                        tasks.addEvent(input);
                        break;
                    //unidentified command
                    default:
                        throw new DukeException();
                }
            } catch (DukeException e) {
                printLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means.");
                printLine();
            }
            command = in.nextLine(); //read next command
        }
    }
}