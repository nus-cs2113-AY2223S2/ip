package duke;

import duke.command.Command;

public class Duke {

    private Storage database;
    
    public TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        database = new Storage();
        tasks = new TaskList(database.tasks);
        Ui.greeting();
    }

    public void run() {
        boolean isProgramRunning = true;
        while (isProgramRunning) {
            String fullCommand = ui.readCommand();
            Command currentCommand = Parser.parse(fullCommand, tasks, database);
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
