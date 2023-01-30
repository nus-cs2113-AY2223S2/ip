import task.TaskList;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        Ui.printStartMessage();
        try (Scanner in = new Scanner(System.in)) {
            boolean isRunning = true;

            while (isRunning) {
                String str = in.nextLine();
                isRunning = CommandParser.parseCommand(str, taskList);
            }
        }
        Ui.printExitMessage();
    }
}
