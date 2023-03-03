package Alex;

import Alex.command.ByeCommand;
import Alex.command.Command;
import Alex.command.CommandResult;
import Alex.exception.AlexException;
import Alex.exception.AlexTaskException;
import Alex.parser.Parser;
import Alex.storage.StorageFile;
import Alex.task.TaskList;
import Alex.ui.Ui;
import java.io.*;

public class Alex {
    public static TaskList taskList;

    public static void main(String[] args) {
        taskList = new TaskList();
        try {
            StorageFile.readData(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
        new Alex().start();
    }

    private void runCommands() throws AlexTaskException {
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = new Parser().parseCommand();
                CommandResult result = command.execute(taskList);
                String feedback = result.getCommandResult();
                Ui.showOutput(feedback);
                try {
                    StorageFile.saveData(taskList);
                } catch (IOException e){
                    Ui.showOutput("Error...aborting save" + e.getMessage());
                }
                isExit = ByeCommand.isExit(command);
            } catch (IndexOutOfBoundsException e) {
                Ui.showOutput("☹ OOPS!!! You are inputted an invalid index, try again!");
            } catch (AlexException e) {
                Ui.showOutput((e.getMessage()));
            } catch (NumberFormatException e) {
                Ui.showOutput("☹ OOPS!!! You are inputted an invalid index, try again!");
            }

        }


    }

    public void start() {
        Ui.showWelcomeMessage();
        try {
            this.runCommands();
        }catch (AlexException e)
        {
            Ui.showOutput(e.getMessage());
        }
    }
}
