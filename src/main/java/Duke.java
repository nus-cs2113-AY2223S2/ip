import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
public class Duke {

    private static String command;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /*
     * Constructs ui, storage and tasks elements and intialises storage.
     */
    public Duke(String storageFilePath){
        ui = new Ui();
        storage = new Storage(storageFilePath);
        tasks = new TaskList();
        storage.initialise();
    }
    /*
     * Continuously takes in and executes user commands
     */
    public void run(){
        while(true){
            tasks.executeCommand(ui.getCommand());
        }
    }

    /*
     * Creates a new duke element and executes the run command to execute commands.
     */
    public static void main(String[] args) {
        new Duke("data/araxys.txt").run();
    }
}
