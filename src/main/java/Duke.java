/**
 * The Duke class represents a chatbot that can respond to user input.
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
public class Duke {
    public static void main(String[] args) {
        new Duke("DUKE/Duke.txt");
    }
    private Memory memory;
    private IO io;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for Duke object.
     * Initializes a new Duke object and loads tasks from a file.
     * Prints a welcome message and loops for user input until the user types "bye".
     *
     * @param filepath Path to the file to load tasks from.
     */
    public Duke(String filepath) {
        memory = new Memory(filepath);
        io = new IO();
        tasks = new TaskList();
        Scanner in = new Scanner(System.in);
        io.printWelcome();
        try {
            storeTasksInList(memory.loadFile());
        } catch (IOException e) {
            io.showLoadingError();
        }
        while (true) {
            String userInput = in.nextLine();
            processUserInput(userInput, true);
        }
    }

    /**
     * Loads a list of input commands to the TaskList.
     * Each input command is processed by the processUserInput method.
     *
     * @param inputCommands A list of input commands to load to the TaskList.
     * @throws IOException If there is an error reading the file.
     */
    public void storeTasksInList(ArrayList<String> inputCommands) throws IOException {
        for (String s : inputCommands) {
            processUserInput(s, false);
        }
    }

    /**
     * Processes user input by executing the appropriate action.
     * Prints an error message if an invalid command is entered.
     *
     * @param userInput     The user's input.
     * @param fileDoesExist Whether the file exists or not.
     */
    private void processUserInput(String userInput, boolean fileDoesExist) {
        parser = new Parser();
        String[] actionAndDescription = parser.splitActionAndDescription(userInput);
        String action = actionAndDescription[0];
        String description = actionAndDescription[1];
        try {
            switch (action) {
                case "bye":
                    io.printBye();
                    break;
                case "list":
                    executeListAction(fileDoesExist);
                    break;
                case "mark":
                    executeMarkAction(description, fileDoesExist);
                        if (fileDoesExist) {
                            memory.writeToFile();
                        }
                    break;
            case "unmark":
                    executeUnmarkAction(description, fileDoesExist);
                        if (fileDoesExist) {
                            memory.writeToFile();
                    }
                    break;
            case "todo":
                    executeTodoAction(description, fileDoesExist);
                        if (fileDoesExist) {
                            memory.appendToFile(userInput);
                        }
                    break;
            case "event":
                    executeEventAction(description, fileDoesExist);
                        if (fileDoesExist) {
                            memory.appendToFile(userInput);
                        }
                    break;
            case "deadline":
                    executeDeadlineAction(description, fileDoesExist);
                        if (fileDoesExist) {
                            memory.appendToFile(userInput);
                        }
                    break;
            case "delete":
                    executeDeleteAction(description);
                    break;
            case "find":
                    executeFindAction(description);
                    break;
            default:
                    io.showInvalidCommand();
            }
        } catch (IOException e) {
            io.showCannotEditFile();
        }
    }

    /**
     * Executes the "list" action by printing the list of tasks to the user.
     *
     * @param fileDoesExist A boolean indicating if the file exists or not.
     */
    private void executeListAction(boolean fileDoesExist) {
        if (fileDoesExist) {
            io.printList(tasks.getTasks());
        }
    }

    /**
     * Executes the "mark" action by marking the specified task as done and printing an acknowledgement message to the user.
     *
     * @param description   The task description provided by the user.
     * @param fileDoesExist A boolean indicating if the file exists or not.
     */
    private void executeMarkAction(String description, boolean fileDoesExist) {
        int index = Integer.parseInt(description) - 1;
        tasks.markAtIndex(index);
        if (fileDoesExist) {
            io.printMarked(tasks.getTasks(), index);
        }
    }

    /**
     * Executes the "unmark" action by marking the specified task as undone and printing an acknowledgement message to the user.
     *
     * @param description   The task description provided by the user.
     * @param fileDoesExist A boolean indicating if the file exists or not.
     */
    private void executeUnmarkAction(String description, boolean fileDoesExist) {
        int index = Integer.parseInt(description) - 1;
        tasks.unmarkAtIndex(Integer.parseInt(description) - 1);
        if (fileDoesExist) {
            io.printUnmarked(tasks.getTasks(), index);
        }
    }

    /**
     * Executes the "todo" action by adding a new Todo task to the task list and printing an acknowledgement message to the user.
     *
     * @param description   The task description provided by the user.
     * @param fileDoesExist A boolean indicating if the file exists or not.
     */
    private void executeTodoAction(String description, boolean fileDoesExist) {
        try {
            tasks.addTodoToList(description);
            if (fileDoesExist) {
                io.printAcknowledgement(tasks.getTasks());
            }
        } catch (Exception e) {
            io.showInsufficientTodo();
        }
    }

    /**
     * Executes an event action if the file exists.
     * Parses the description into name, fromDate and toDate.
     * Adds the parsed task to the task list and prints acknowledgement if the file exists.
     * Otherwise, shows an error message.
     *
     * @param description   The description of the event task.
     * @param fileDoesExist Indicates whether the file exists.
     */
    private void executeEventAction(String description, boolean fileDoesExist) {
        if (fileDoesExist) {
            try {
                String[] indexArr = splitDescriptionEvent(description);
                String name = indexArr[0].trim();
                String fromDate = indexArr[1].substring(5).trim();
                String toDate = indexArr[2].substring(3).trim();
                tasks.addEventToList(name, fromDate, toDate);
                if (fileDoesExist) {
                    io.printAcknowledgement(tasks.getTasks());
                }
            } catch (DukeException e) {
                io.showInsufficientEvent();
            }
        }
    }

    /**
     * Parses the description of an event task and returns an array containing the name,
     * fromDate and toDate.
     *
     * @param description The description of the event task.
     * @return An array containing the name, fromDate and toDate.
     * @throws DukeException If there are insufficient arguments to parse the description.
     */

    private static String[] splitDescriptionEvent(String description) throws DukeException {
        String[] indexArr = description.split("/", 3);
        if (indexArr.length < 3) {
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }

    /**
     * Executes a deadline action.
     * Parses the description into name and byDate.
     * Adds the parsed task to the task list and prints acknowledgement if the file exists.
     * Otherwise, shows an error message.
     *
     * @param description   The description of the deadline task.
     * @param fileDoesExist Indicates whether the file exists.
     */
    private void executeDeadlineAction(String description, boolean fileDoesExist) {
        try {
            String[] indexArr = splitDescriptionDeadline(description);
            String name = indexArr[0].trim();
            String byDate = indexArr[1].substring(3).trim();
            tasks.addDeadlineToList(name, byDate);
            if (fileDoesExist) {
                io.printAcknowledgement(tasks.getTasks());
            }
        } catch (DukeException e) {
            io.showInsufficientDeadline();
        }
    }

    /**
     * Parses the description of a deadline task and returns an array containing the name and byDate.
     *
     * @param description The description of the deadline task.
     * @return An array containing the name and byDate.
     * @throws DukeException If there are insufficient arguments to parse the description.
     */
    private static String[] splitDescriptionDeadline(String description) throws DukeException {
        String[] indexArr = description.split("/", 2);
        if (indexArr.length < 2) {
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }

    /**
     * Executes a delete action.
     * Deletes the task at the specified index and prints the deleted task and task list.
     *
     * @param description The index of the task to delete.
     */
    private void executeDeleteAction(String description) {
        Integer index = Integer.parseInt(description) - 1;
        String deletedTask = tasks.deleteTaskAtIndex(index);
        io.printDeleted(tasks.getTasks(), deletedTask);
    }

    /**
     * Executes a find action.
     * Finds tasks containing the specified keyword and prints them.
     *
     * @param description The keyword to search for.
     */
    private void executeFindAction(String description) {
        ArrayList<Task> foundTasks = tasks.findTasks(description);
        io.printFind();
        io.printList(foundTasks);
    }
}