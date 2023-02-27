package util;

import java.util.ArrayList;
import util.Task;
import util.Deadline;
import util.Todo;
import util.Event;
import util.Ui;

/**
 * 
 * The Parser class parses user input and generates appropriate output messages.
 * It interacts with the Ui class and updates the Task list as required.
 */
public class Parser {
    protected boolean isExit;
    private Ui ui;

    /**
     * Constructor for Parser class. Initializes isExit to false.
     */
    public Parser() {
        isExit = false;
    }

    public boolean IsExit() {
        return isExit;
    }

    /**
     * Parses the user input and generates appropriate output messages.
     * updates the Task list as required.
     * 
     * @param line     the user input string
     * @param commands the ArrayList of Tasks
     * @return the updated ArrayList of Tasks
     */
    public ArrayList<Task> answerCommand(String line, ArrayList<Task> commands) {
        ui = new Ui();
        if (line.equals("bye")) {
            ui.printByeMessage();
            this.isExit = true;
        } else if (line.equals("list")) {
            ui.displayCommandsList(commands);
        } else if (line.split(" ")[0].equals("mark")) {
            int index = Integer.parseInt(line.split(" ")[1]);
            commands.get(index - 1).setDone(true);
            ui.printDashLine();
            ui.printSpecificTask(index, commands, "Nice! I've marked this task as done:");
            ui.printDashLine();
        } else if (line.split(" ")[0].equals("unmark")) {
            int index = Integer.parseInt(line.split(" ")[1]);
            commands.get(index - 1).setDone(false);
            ui.printDashLine();
            ui.printSpecificTask(index, commands, "OK, I've marked this task as not done yet:");
            ui.printDashLine();
        } else if (line.split(" ")[0].equals("todo")) {
            if (line.split(" ").length == 1) {
                ui.printDashLine();
                System.out.println("\t OOPS!!! The description of a todo cannot be empty.");
                ui.printDashLine();
            } else {
                commands.add(new Todo(line.substring(5)));
                ui.printDashLine();
                ui.printSpecificTask(commands.size(), commands, "Got it. I've added this task:");
                ui.printDashLine();
            }
        } else if (line.split(" ")[0].equals("event")) {
            int indexFrom = line.indexOf("/");
            int indexTo = line.indexOf("/", indexFrom + 1);
            commands.add(new Event(line.substring(6, indexFrom - 1),
                    line.substring(indexFrom + 6, indexTo - 1), line.substring(indexTo + 4)));
            ui.printDashLine();
            ui.printSpecificTask(commands.size(), commands, "Got it. I've added this task:");
            ui.printDashLine();
        } else if (line.split(" ")[0].equals("deadline")) {
            int index_by = line.indexOf("/");
            commands.add(new Deadline(line.substring(9, index_by - 1), line.substring(index_by + 4)));
            ui.printDashLine();
            ui.printSpecificTask(commands.size(), commands, "Got it. I've added this task:");
            ui.printDashLine();
        } else if (line.split(" ")[0].equals("delete")) {
            int index = Integer.parseInt(line.split(" ")[1]);
            ui.printDashLine();
            ui.printSpecificTask(index, commands, "Noted. I've removed this task:");
            commands.remove(index - 1);
            ui.printLenghtOfTaskList(commands);
            ui.printDashLine();
        } else {
            ui.printDashLine();
            System.out.println("\t OOPS!!! I'm sorry, but I don't know what that means :-(");
            ui.printDashLine();
        }
        return commands;
    }
}
