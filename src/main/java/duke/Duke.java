package duke;

import duke.data.FileData;
import duke.task.Task;
import duke.command.CommandWords;
import duke.command.Command;
import duke.output.Printer;

import java.util.Scanner;
import java.io.File;

public class Duke {
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Printer.greeting();
        Task[] tasks = new Task[MAX_TASKS];

        // add saved tasks to program
        FileData.makeDirectory();
        File dataFile = FileData.openDataFile();
        FileData.uploadData(dataFile, tasks);

        // asks user for updates
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals(CommandWords.BYE.COMMAND)) {
            Command.evaluate(input, tasks);
            input = in.nextLine();
        }

        // save then exit
        FileData.writeToFile(dataFile, tasks);

        Printer.bye();
    }
}