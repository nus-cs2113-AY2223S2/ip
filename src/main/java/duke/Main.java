package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.ui.TextUi;
public class Main {
    private static final String VERSION = "0.1";
    private TextUi ui;
    private TaskList taskList;

    public static void main(String... args) {
        new Main().run(args);
    }

    private void run(String... args) {
        init();
        loopUntilExit();
        finish();
    }

    private void init(String... args) {
        try {
            this.ui = new TextUi();
            this.taskList = new TaskList();
            ui.printWelcomeMsg(VERSION);
            ui.printDivider();
            ui.printIntroMsg();
            ui.printDivider();
        } catch (Exception e) {
            ui.printMessage("init failed!");
            throw new RuntimeException();
        }
    }

    private void loopUntilExit() {
        Command command;
        do {
            String userInput = ui.getUserCommand();
            try {
                command = new Parser().parseCommand(userInput);
                ui.printDivider();
                CommandResult result = executeCommand(command);
                ui.printResult(result);
            } catch (Exception e) {
                command = null;
                ui.printMessage(e.getMessage());
            }
            ui.printDivider();
        } while (!ExitCommand.isExitCommand(command));
    }

    private void finish() {
        ui.printGoodByeMsg();
        System.exit(0);
    }

    private void prepareData(Command command) {
        try {
            command.setTaskList(taskList);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private CommandResult executeCommand(Command command) {
        try {
            prepareData(command);
            return command.execute();
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
