import java.util.Scanner;

//Solution below adapted and reused from Student Oh Yi Xiu Wilson
// with modifications made by Wilson Lee Jun Wei

/**
 * This class represents Coffee Bot that helps users to keep track of their personal schedules.
 * Coffee Bot takes in commands by users to keep track of Todos, Deadlines and events tasks.
 */
public class Duke {
    public static void main(String[] arguments) {
        TaskList listItems = new TaskList();
        Parser parserCommands = new Parser(listItems);
        Storage storageItems = new Storage(listItems);
        listItems.getTasksFromStorage(storageItems);
        Ui userInterface = new Ui();
        userInterface.print(Ui.COFFEE_GREETING);
        while (true) {
            Scanner command = new Scanner(System.in);
            String inputCommand = command.nextLine();
            if (inputCommand.contains("bye")) {
                break;
            }
            String commandMessage = parserCommands.parseInput(inputCommand);
            userInterface.print(commandMessage);
            storageItems.writeTasksToFile();
        }
    }
}
//@@ Student Oh Yi Xiu Wilson