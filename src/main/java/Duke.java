
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private UI ui;

    public Duke() {
        ui = new UI();
        storage = new Storage();
        storage.loadFromFile();
    }


    public void run() {
        ui.printIntro();

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

    private void processUserInput(String userInput) throws DukeException {
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

    public static void main(String[] args) {
        new Duke().run();
    }

}

