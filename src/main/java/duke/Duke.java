package duke;

import duke.commands.Command;
import duke.commands.CommandResult;

/**
 * Adapted from nus-cs2113-AY2223S2/personbook
 */

public class Duke {

    private Ui ui;
    private FileProcessor fileProcessor;
    private TaskList taskList = new TaskList();

    public static void main(String args[]) {
        new Duke().run();
    }
    public void run() {
        start();
        runLoopTillExit();
        exit();
    }

    private void start() {
        this.ui = new Ui();
        this.fileProcessor = new FileProcessor(taskList.getListOfTasks());
        ui.greet();

    }

    private void exit() {
        ui.farewell();
        fileProcessor.writeFile(taskList.getListOfTasks());
        System.exit(0);
    }

    private void runLoopTillExit() {
        Command command;
        String userCommand;
        userCommand = ui.getUserInput();
        while (!userCommand.equals("bye")) {
            command = new Parser().parseCommand(userCommand);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
            userCommand = ui.getUserInput();
        }
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(taskList);
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
