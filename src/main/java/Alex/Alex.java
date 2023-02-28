package Alex;

import Alex.command.ByeCommand;
import Alex.command.Command;
import Alex.command.CommandResult;
import Alex.exception.DukeException;
import Alex.parser.Parser;
import Alex.storage.StorageFile;
import Alex.task.TaskList;
import Alex.task.Deadline;
import Alex.task.Event;
import Alex.task.Task;
import Alex.task.Todo;
import Alex.ui.Ui;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Alex {
    public static TaskList taskList;

    public static void main(String[] args) {
        taskList = new TaskList();
        try {
            StorageFile.readData(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
        Ui.showWelcomeMessage();
        /** Constantly asks for user input until bye is received */
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
                    System.out.println("Error...aborting save" + e.getMessage());
                }
                isExit = ByeCommand.isExit(command);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
            } /*catch (DukeException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            printLine();
        }*/

        }

    }
}
