package wilsonoh.sagyo;

import java.util.ArrayList;
import java.util.Scanner;

import wilsonoh.sagyo.commands.Command;
import wilsonoh.sagyo.exceptions.SagyoException;
import wilsonoh.sagyo.parser.CommandParser;
import wilsonoh.sagyo.tasks.Task;
import wilsonoh.sagyo.ui.TextFormatter;

public class Main {

    private static final String[] GREETING =
        new String[] {"Welcome to sagyo, your task manager!", "What can I do for you?"};

    private static final String PROMPT = "> ";

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Runner function of the class, executes the main loop
     */
    public void run() {
        TextFormatter ui = new TextFormatter(2, 120);
        CommandParser cmdParser = new CommandParser(tasks);
        boolean isRunning = true;
        ui.printLinesInfo(GREETING);
        // try-with-resources to close the scanner automatically, preventing resource leaks
        try (Scanner sc = new Scanner(System.in)) {
            // Loop will break on execution of the ByeCommand
            while (isRunning) {
                System.out.print(PROMPT);
                String line = sc.nextLine();
                try {
                    Command cmd = cmdParser.parseCommand(line);
                    cmd.executeCommand();
                    ui.printLinesInfo(cmd.getCommandMessage());
                    if (cmd.isExit) {
                        isRunning = false;
                    }
                } catch (SagyoException e) {
                    ui.printLinesError(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
