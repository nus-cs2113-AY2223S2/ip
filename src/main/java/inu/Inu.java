package inu;

import inu.commands.CommandResult;
import inu.task.TaskList;
import inu.storage.StorageFile;
import inu.commons.Ui;
import inu.commands.Command;
import inu.parser.Parser;

public class Inu {
    public TaskList taskList;

    public void startInu() {
        taskList = new TaskList();
        StorageFile.loadTaskListFromFile(taskList);
        Ui.printGreeting();
    }

    public void runInu() {
        boolean isExit = false;
        while (!isExit) {
            final Command c = Parser.parseCommand(taskList);
            final CommandResult result = c.execute(taskList);
            final String output = result.getCommandResult();
            Ui.printOutput(output);
            isExit = c.isExit();
        }
    }

    public void closeInu() {
        Ui.printFarewell();
        StorageFile.saveTaskListToFile(taskList);
    }

    public Inu() {
        startInu();
        runInu();
        closeInu();
    }

    public static void main(String[] args) {
        new Inu();
    }
}