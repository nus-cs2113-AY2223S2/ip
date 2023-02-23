package duke.main;

import duke.Storage.Storage;
import duke.command.Command;
import duke.command.actionCommands.DeleteTask;
import duke.command.actionCommands.ListTasks;
import duke.command.actionCommands.MarkTask;
import duke.command.actionCommands.UnmarkTask;
import duke.command.taskCommands.DeadlineTask;
import duke.command.taskCommands.EventTask;
import duke.command.taskCommands.TodoTask;
import duke.exception.InvalidTaskException;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

import static duke.Storage.Storage.updateFile;
import static duke.main.Duke.printHorizontalLine;

import duke.command.Parser;

public class Ui {
    public void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm duke.main.Duke\n" + " What can I do for you?\n");
        printHorizontalLine();
    }


    public void bye() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void run(TaskList tasks, Storage storage) throws IOException {
        Scanner in = new Scanner((System.in));
        Parser parser = new Parser();

        String line;

        while (true) {
            System.out.println("Enter your command here: ");
            line = in.nextLine().trim();
            parser.processLine(line, tasks, storage);
            if (parser.isExit()) {
                break;
            }
        }
    }
}

