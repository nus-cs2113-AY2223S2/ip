
import java.util.Objects;


/**
 * A class that represents a chatbot named Duke.
 */
public class Duke {

    /**
     * The storage object used to store and retrieve task data.
     */
    private Storage storage;

    /**
     * The user interface object used to interact with the user.
     */
    private UI ui;

    /**
     * Constructs a new Duke object with a new UI object and a new Storage object.
     * Loads the task data from a file using the Storage object.
     */
    public Duke() {
        ui = new UI();
        storage = new Storage();
        storage.loadFromFile();
    }

    /**
     * The main loop of the program that reads user input from the UI and processes it.
     * The loop continues until the user enters the "bye" command.
     */
    public void run() {
        UI.printIntro();

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                processUserInput(userInput);
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Processes the user input and performs the corresponding action.
     * Throws a DukeException if the input is invalid or incomplete.
     *
     * @param userInput the input string entered by the user
     * @throws DukeException if the input is invalid or incomplete
     */
    private void processUserInput(String userInput) throws DukeException {
        // Check if the command is valid
        String[] commands = Parser.parse(userInput);
        String command = commands[0];
        if ((!Objects.equals(commands[0], "bye"))
                && (!Objects.equals(commands[0], "list"))
                && (!Objects.equals(commands[0], "todo"))
                && (!Objects.equals(commands[0], "event"))
                && (!Objects.equals(commands[0], "deadline"))
                && (!Objects.equals(commands[0], "mark"))
                && (!Objects.equals(commands[0], "unmark"))
                && (!Objects.equals(commands[0], "delete"))
                && (!Objects.equals(commands[0], "find"))) {
            throw new DukeException.InvalidInputException("I'm sorry, but I don't know what that means :-(");
        } else if ((commands.length == 1)
                && ((!Objects.equals(commands[0], "list")
                && (!Objects.equals(commands[0], "bye"))))) {
            throw new DukeException.IncompleteInputException("The description of a " + commands[0] + " cannot be empty!");
        }

        // Perform the corresponding action based on the command
        switch (command) {
        case "bye":
            Parser.handleBye();
            break;
        case "list":
            Parser.handleList();
            break;
        case "mark":
            Parser.handleMark(commands[1]);
            break;
        case "unmark":
            Parser.handleUnmark(commands[1]);
            break;
        case "delete":
            Parser.handleDelete(commands[1]);
            break;
        case "todo":
            Parser.handleTodo(commands[1]);
            break;
        case "deadline":
            Parser.handleDeadline(commands[1]);
            break;
        case "event":
            Parser.handleEvent(commands[1]);
            break;
        case "find":
            Parser.handleFind(commands[1]);
        }
    }

    /**
     * The main method that creates a new Duke object and runs it.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}


