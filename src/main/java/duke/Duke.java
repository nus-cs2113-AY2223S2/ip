package duke;

import duke.task.Task;
import duke.data.FileActions;
import duke.data.DataActions;
import duke.command.CommandWords;
import duke.command.Command;
import duke.output.Printer;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    public static void main(String[] args) {
        Printer.greeting();
        ArrayList<Task> tasks = new ArrayList<>();

        // make dir if it does not exist
        FileActions.makeDirectory();
        // make file if it does not exist
        File dataFile = FileActions.openDataFile();
        // import the data from file to program
        DataActions.importData(dataFile, tasks);

        // user to input update
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals(CommandWords.BYE.COMMAND)) {
            Command.evaluate(input, tasks);
            input = in.nextLine();
        }

        // save data to file
        DataActions.updateSavedData(tasks);

        Printer.bye();
    }
}