
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
                && (!Objects.equals(commands[0], "delete"))) {
            throw new DukeException.InvalidInputException("I'm sorry, but I don't know what that means :-(");
        } else if ((commands.length == 1)
                && ((!Objects.equals(commands[0], "list")
                && (!Objects.equals(commands[0], "bye"))))) {
            throw new DukeException.IncompleteInputException("The description of a " + commands[0] + " cannot be empty!");

        }

        switch (command) {
        case "bye":
            handleBye();
            break;
        case "list":
            handleList();
            break;
        case "mark":
            handleMark(commands[1]);
            break;
        case "unmark":
            handleUnmark(commands[1]);
            break;
        case "delete":
            handleDelete(commands[1]);
            break;
        case "todo":
            handleTodo(commands[1]);
            break;
        case "deadline":
            handleDeadline(commands[1]);
            break;
        case "event":
            handleEvent(commands[1]);
            break;

        }
    }

        private static void handleBye () {
            UI.printBye();
            System.exit(0);
        }

        private static void handleList () {
            TasksList.processList();
        }

        static void handleMark (String remainingWords){

            int i = Integer.parseInt(remainingWords) - 1;
            TasksList.addMark(remainingWords, i);
        }

        static void handleUnmark (String remainingWords){

            int i = Integer.parseInt(remainingWords) - 1;
            TasksList.unMark(remainingWords, i);
        }

        static void handleDelete (String remainingWords){
            int i = Integer.parseInt(remainingWords) - 1;
            TasksList.deleteTask(i);
        }

        static void handleTodo (String remainingWords){
            TasksList.addTask(new Todo(remainingWords));
        }

        static void handleDeadline (String remainingWords){
            String[] parts = remainingWords.split("/");
            TasksList.addTask(new Deadline(parts[0], parts[1]));
        }

        static void handleEvent (String remainingWords){
            String[] parts = remainingWords.split("/");
            TasksList.addTask(new Event(parts[0], parts[1], parts[2]));
        }

    public static void main(String[] args) {
        new Duke().run();
    }

}

