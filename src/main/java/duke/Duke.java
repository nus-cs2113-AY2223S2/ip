package duke;

import duke.exceptions.EmptyListException;
import duke.exceptions.ExcessInputsException;
import duke.exceptions.MissingDescriptionException;
import duke.task.Task;

import java.util.ArrayList;

public class Duke {
    private static Storage storage;
    static ArrayList<Task> tasks = new ArrayList<>();
    public static final String LINE = "    ____________________________________________________________\n";

    /**
     * Processes inputs from user and executes command based on the input.
     * @param line a line input by the user that contains commands and descriptions.
     */
    private static void processCommands(String line) {
        while (!line.equals("bye")) {
            String[] words = Parser.splitCommand(line," ", 2);
            String command = words[0];
            try {
                if (command.contains("mark")) {
                        TaskList.editMarkStatus(tasks, words, command);
                } else {
                    switch (command) {
                    case "list":
                        Ui.printList(tasks, words);
                        break;
                    case "delete":
                        TaskList.deleteTask(tasks, words);
                        break;
                    case "find":
                        TaskList.findString(tasks, words);
                        break;
                    case "todo":
                        TaskList.addTodo(tasks, words);
                        Ui.showAddedTask(tasks);
                        break;
                    case "deadline":
                        TaskList.addDeadline(tasks, words);
                        Ui.showAddedTask(tasks);
                        break;
                    case "event":
                        TaskList.addEvent(tasks, words);
                        Ui.showAddedTask(tasks);
                        break;
                    case "help":
                        Ui.showHelp();
                        break;
                    default:
                        Ui.printError();
                        line = Ui.readInput();
                        continue;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(LINE + "Command must only be followed by the index number!\n" +
                        "Enter \"list\" to check the index.\n" + LINE);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(LINE + "Missing/Invalid task index number!\n" +
                        "Enter \"list\" to check the index.\n" + LINE);
            } catch (NullPointerException e) {
                System.out.println(LINE + "Invalid task index number!\n" +
                        "Enter \"list\" to check the index.\n" + LINE);
            } catch (ExcessInputsException e) {
                System.out.println(LINE + "Too many inputs! Please only enter \"list\"\n" + LINE);
            } catch (MissingDescriptionException e) {
                System.out.println(LINE + "Missing task description details.\n" +
                    "Type 'help' to view list of command formats.\n" + LINE);
            } catch (EmptyListException e) {
                System.out.println(LINE + "Oops there is nothing in your list yet, try adding a task item first. :)\n" + LINE);
            }
            line = Ui.readInput();
            storage.saveChanges(tasks);
        }
    }

    /**
     * Initialises an ui to read user commands and to print messages to the terminal.
     * Initialises a storage space to store user inputs as well as reads past inputs.
     */
    private void run(){
        storage = new Storage();
        Ui.showWelcome();
        Storage.load(tasks);
        String line = Ui.initialiseScanner();
        processCommands(line);
        Ui.sayBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}