package duke;

import command.Command;
import parser.Parser;
import storage.TaskStorage;
import ui.UI;
import task.TaskList;
import exception.IncompleteInputException;
import exception.InvalidInputException;

import java.time.format.DateTimeParseException;


public class Duke {
    private final TaskStorage storage;
    private final TaskList tasks;
    private final UI ui;
    private final Parser parser;

    /**
     * Initializes the ui,parser and storage object to be used later and load the existing task in filePath.
     * All exceptions have been handled in this function and will be printed out accordingly
     * @param filePath The path of the file to load from (if any).
     */
    public Duke (String filePath) {
        TaskList tempTasks;
        ui = new UI();
        parser = new Parser();
        storage = new TaskStorage(filePath);
        try {
            tempTasks = new TaskList(storage.loadTasks());
        } catch (IncompleteInputException ex) {
            ui.printError(ex);
            tempTasks= new TaskList();
        } catch (DateTimeParseException ex) {
            ui.printError(ex);
            tempTasks = new TaskList();
        }
        tasks = tempTasks;
    }

    /**
     * Runs the parser whenever the user has not exited the program, parse the input and execute the appropriate commands
     * using the tasks storage and ui which have been initialized in {@link #Duke} and finally print out the errors if any.
     */
    public void run() {
        ui.printGreeting();
        while(parser.isExecuting()) {
            String fullCommand = ui.getCommand();
            try {
                Command cmd = parser.parse(fullCommand);
                cmd.execute(tasks, storage, ui);
            } catch (IncompleteInputException ex) {
                ui.printError(ex);
            } catch(InvalidInputException ex) {
                ui.printError(ex);
            } catch (NumberFormatException ex) {
                ui.printError(ex);
            } catch (DateTimeParseException ex) {
                System.out.println("Exception occurred : " +ex);
                System.out.println("Please enter the  time in YYYY-MM-DD HH:mm format");
            }
        }
    }

    public static void main(String[] args) {
        new Duke ("data/duke.txt").run();
    }
}


