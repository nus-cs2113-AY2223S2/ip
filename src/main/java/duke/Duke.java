package duke;

import duke.command.Command;

/**
 * Duke Object that runs the entire program
 * It works with other Objects such as Storage, TaskList, Ui, Parser that handles the different
 * commands from the user
 */
public class Duke {
    /**
     * Storage object that deals with loading tasks from the file and saving tasks in the file
     */
    private Storage database;
    /**
     * TaskList object that contains the task list e.g., it has operations to add/delete tasks in the list
     */
    public TaskList tasks;
    /**
     * Ui object that deals with interactions with the user
     */
    private Ui ui;

    /**
     * Initialises the objects of the Duke program and gives a greeting
     */
    public Duke() {
        ui = new Ui();
        database = new Storage();
        tasks = new TaskList(database.tasks);
        Ui.greeting();
    }

    /**
     * Runs the program by reading inputs from user line by line until user types bye to exit
     */
    public void run() {
        boolean isProgramRunning = true;
        while (isProgramRunning) {
            String fullCommand = ui.readCommand();
            Command currentCommand = Parser.parse(fullCommand, tasks);
            if (currentCommand == null) {
                continue;
            }
            currentCommand.execute(tasks, database);
            isProgramRunning = !currentCommand.isExit;
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
