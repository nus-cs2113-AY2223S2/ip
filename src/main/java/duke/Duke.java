package duke;

import java.util.Scanner;
import duke.Tasks.*;
import java.util.ArrayList;

public class Duke {

    /**
     * Checks if the first word of the user input is in the correct format
     * Throws exception if not
     * @param firstWord the first word in the user input
     */
    public static void inputChecker(String firstWord) {
        if (!(firstWord.equals("Todo") || firstWord.equals("Deadline") || firstWord.equals("Event")
                || firstWord.equals("Bye") || firstWord.equals("Mark")
                || firstWord.equals("Unmark") || firstWord.equals("Find") || firstWord.equals("Delete")
                || firstWord.equals("List"))) {
            throw new IllegalArgumentException();
        }
    }
    /**
     * Main contains a While loop that keeps taking in user input and executes code accordingly
     * Will only exit when the user inputs "Bye"
     */

    public static void main(String[] args) {
        UI ui = new UI();
        ui.printWelcome();
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);
        Storage.fillTaskList(taskList);
        Command command = null;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            Parser parser = new Parser(userInput);
            String firstWord = parser.getFirstWord(userInput);
            String remainingWords = parser.getRemainingWords(userInput);

            try {
                inputChecker(firstWord);
            } catch (IllegalArgumentException e){
                ui.printInputError();
            }

            if (command == null) {
                command = new Command(firstWord, remainingWords);
            } else {
                command.setCommand(firstWord);
                command.setDetails(remainingWords);
            }
            command.executeCommand(taskList);

            if (userInput.equals("Bye")) {
                ui.printBye();
                break;
            }
        }
        Storage.writeToFile(taskList);
    }
}
