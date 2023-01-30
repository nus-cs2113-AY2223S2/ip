package duke.commands;

import duke.task.TaskList;

import java.util.Scanner;

public class UI {

    private final static String lineBreak = "____________________________________________________________\n";

    public static String getLineBreak() {
        return lineBreak;
    }

    /**
     * Prints a greeting message to the user
     */
    public void greet() {
        System.out.println(lineBreak + "Hello I'm\n" +
                "    ____        _        \n" +
                "   |  _ \\ _   _| | _____ \n" +
                "   | | | | | | | |/ / _ \\\n" +
                "   | |_| | |_| |   <  __/\n" +
                "   |____/ \\__,_|_|\\_\\___|" + "\nWhat can I do for you?\n" +
                "Input your tasks and I'll keep track of them!\n" + lineBreak);
    }

    /**
     * Prints a farewell message to the user
     */
    public void sayBye() {
        System.out.println("Aww you're going? Hope to see you again soon!\n" + lineBreak);
    }

    /**
     * Takes in user input and processes it until the user inputs "bye"
     *
     * @param taskList List containing the tasks input by user
     */
    public void inputFunction(TaskList taskList){
        Scanner in = new Scanner(System.in);
        CommandHandler newProcess = new CommandHandler();

        String line;
        boolean isRunning = true;

        do {
            System.out.print("Enter Your Command Here: ");
            line = in.nextLine().trim();
            System.out.println(lineBreak);
            newProcess.updateLine(line);
            if (line.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else {
                newProcess.handleCommand(taskList);
            }

        } while (isRunning);
    }
}
