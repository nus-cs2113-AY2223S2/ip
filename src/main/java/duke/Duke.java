package duke;

import duke.task.Task;
import duke.command.CommandWords;
import duke.command.Command;
import duke.output.Printer;

import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Printer.greeting();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals(CommandWords.BYE.COMMAND)) {
            Command.evaluate(input, tasks);
            input = in.nextLine();
        }
        Printer.bye();
    }
}