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



    public Duke (String filePath) {
        TaskList tempTasks;
        ui = new UI();
        parser = new Parser();
        storage = new TaskStorage(filePath);
        try {
            tempTasks = new TaskList(storage.load());
        } catch (IncompleteInputException ex) {
            ui.printError(ex);
            tempTasks= new TaskList();
        }
        tasks = tempTasks;
    }

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


