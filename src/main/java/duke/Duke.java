package duke;

import duke.data.FileData;
import duke.task.Task;
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

        FileData.writeToFile(dataFile, tasks);

        Printer.bye();
    }
}