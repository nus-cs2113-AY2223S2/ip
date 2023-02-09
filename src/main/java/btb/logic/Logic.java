package btb.logic;

import btb.tasks.*;

public class Logic {

    /**
     * executes the different commands entered
     * by the user.
     *
     * @param tasks     the ArrayList of tasks that user entered
     * @param userInput string entered by user in command line
     */
    public static void runCommand(TaskManager tasks, String userInput) {
        String[] inputs = Parser.handleInput(userInput);
        String command = inputs[0];
        String description;

        if (inputs.length == 1) {
            description = "";
        } else {
            description = inputs[1];
        }
        Task task;

        try {
            switch (command) {
            case "todo":
                task = new Todo(description);
                tasks.addTask(task, command);
                break;
            case "deadline":
                // splits the description into [task description, dueDate]
                String[] deadlineDescriptions = Parser.handleDeadline(description);
                task = new Deadline(deadlineDescriptions[0], deadlineDescriptions[1]);
                tasks.addTask(task, command);
                break;
            case "event":
                // splits the description into [task description, startDate, endDate]
                String[] eventDescription = Parser.handleEvent(description);
                task = new Event(eventDescription[0], eventDescription[1], eventDescription[2]);
                tasks.addTask(task, command);
                break;
            case "mark":
                tasks.markTask(description);
                break;
            case "unmark":
                tasks.unmarkTask(description);
                break;
            case "list":
                tasks.listTasks();
                break;
            case "":
                System.out.println("\t Please enter some commands");
                break;
            default:
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            System.out.println("\t Please enter a valid command (╬▔皿▔)╯.");
        }
    }
}
