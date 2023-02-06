package duke.main;

import duke.command.Command;
import duke.command.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        // Try-with-resources to automatically close Scanner
        try (Scanner in = new Scanner(System.in)) {
            Ui.printStartMessage();
            boolean isRunning = true;

            while (isRunning) {
                String str = in.nextLine();
                Command command = Parser.parseCommand(str, taskList);
                command.run(taskList);
                isRunning = !command.isExit();
            }
        }
    }
}
