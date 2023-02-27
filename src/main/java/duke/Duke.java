package duke;

import command.Command;
import exception.CommandNotRecognisedException;
import exception.IllegalCharacterException;


public class Duke {

    public static void main(String[] args) {

        Storage.openFile();
        Ui.printStartMessage();

        String userInput;
        do {
            userInput = Ui.getUserInput();
            try {
                Parser.processCommand(userInput, TaskList.tasks);
            } catch (CommandNotRecognisedException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                Ui.printDivider();
            } catch (IllegalCharacterException e) {
                System.out.println("☹ OOPS!!! Input should not contain '|' or '-'.");
                Ui.printDivider();
            }
        } while (!userInput.equals(Command.COMMAND_BYE));

        Storage.writeToFile(TaskList.tasks);
    }
}