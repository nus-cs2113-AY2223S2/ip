package duke;

import command.Command;
import exception.CommandNotRecognisedException;
import exception.IllegalCharacterException;


public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser();
        storage.openFile(taskList);
    }

    public void run() {
        ui.printStartMessage();

        String userInput;
        do {
            userInput = ui.getUserInput();
            try {
                parser.processCommand(userInput, taskList);
            } catch (CommandNotRecognisedException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                ui.printDivider();
            } catch (IllegalCharacterException e) {
                System.out.println("☹ OOPS!!! Input should not contain '|' or '-'.");
                ui.printDivider();
            }
        } while (!userInput.equals(Command.COMMAND_BYE));

        storage.writeToFile(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}