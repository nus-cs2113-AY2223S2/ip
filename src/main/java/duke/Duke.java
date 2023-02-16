package duke;

import duke.command.ExitCommand;
import duke.task.Task;
import duke.data.FileActions;
import duke.data.DataActions;
import duke.command.Command;
import duke.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    private static ArrayList<Task> startUp() {
        Ui.greeting();
        ArrayList<Task> tasks = new ArrayList<>();

        FileActions.makeDirectory(); // make dir if it does not exist
        File dataFile = FileActions.openDataFile(); // make file if it does not exist
        DataActions.importData(dataFile, tasks); // import the data from file to program
        return tasks;
    }
    public static void main(String[] args) {
        ArrayList<Task> tasks = startUp();

        // user to input update
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals(ExitCommand.COMMAND_WORD)) {
            Command.evaluate(input, tasks);
            input = in.nextLine();
        }

        ExitCommand.exit(tasks);
    }
}