package duke.commands;

import duke.commands.Parser;
import duke.exceptions.InvalidTaskException;
import duke.save.Storage;
import duke.tasks.TaskList;

import java.util.Scanner;

import static duke.constants.Constants.LINEBREAK;

public class Ui {

    public static final String INTRO = LINEBREAK + "\nHello I'm\n" +
            "    ____        _        \n" +
            "   |  _ \\ _   _| | _____ \n" +
            "   | | | | | | | |/ / _ \\\n" +
            "   | |_| | |_| |   <  __/\n" +
            "   |____/ \\__,_|_|\\_\\___|" + "\nWhat can I do for you?\n" +
            "Input your tasks and I'll keep track of them!\n" +
            "If you're unsure of how to use me, type \"help\" to see what I can do!\n" + LINEBREAK;

    /**
     * Prints a greeting message to the user.
     */
    public void greet() {
        System.out.println(INTRO);
    }

    /**
     * Prints a farewell message to the user.
     */
    public void sayBye() {
        System.out.println("Aww you're going? Hope to see you again soon!\n" + LINEBREAK);
    }

    /**
     * Takes in user input and processes it until the user inputs "bye".
     *
     * @param taskList List containing the tasks input by user.
     */
    public void run(TaskList taskList, Storage storage) {
        Scanner in = new Scanner(System.in);
        Parser newProcess = new Parser();

        String line;
        boolean isRunning = true;

        do {
            System.out.print("Enter Your Command Here: ");
            line = in.nextLine().trim();
            System.out.println(LINEBREAK);
            newProcess.updateLine(line);
            if (newProcess.isExit()) {
                isRunning = false;
            } else {
                try {
                    newProcess.handleCommand(taskList, storage);
                } catch (InvalidTaskException e) {
                    System.out.println(e.getMessage());
                }

            }

        } while (isRunning);
    }
}
