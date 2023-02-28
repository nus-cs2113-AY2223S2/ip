package duke.commands;

import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import duke.ui.TextUi;
import duke.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;

public class Duke {
    private TextUi ui;
    private TaskList taskList;


    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        this.ui = new TextUi();
        ui.showWelcomeMessage();

        File duke = new File("./duke.txt");
        if (duke.exists()) {
            System.out.println("Task list already exists.");
            new Storage("./duke.txt").FileReading(taskList);
            new ListCommand().execute();
        } else {
            System.out.println("Your new task list is created!");
        }

    }

    private void exit() {
        new Storage("./duke.txt").saveFile(taskList);
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            executeCommand(command);
        } while (!ExitCommand.isExit(command));
    }

    private void executeCommand(Command command) {
        try {
            command.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }




    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}
