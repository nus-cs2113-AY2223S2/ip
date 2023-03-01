package chronos.userinterface;

import chronos.commandhandler.Command;
import chronos.commandhandler.InputParser;
import chronos.inoutput.Input;
import chronos.inoutput.Output;
import chronos.savehandler.Storage;
import chronos.tasktype.Event;
import chronos.tasktype.Task;
import chronos.tasktype.Stash;
import chronos.tasktype.Deadline;
import chronos.tasktype.Todo;
import chronos.exceptions.ChronosExceptions;

import java.util.ArrayList;
import java.util.Scanner;

import chronos.timer.Clock;

/**
 * TaskManager class manages the input commands and the task stash.
 */
public class TaskManager {
    private static Input inOut;
    private static Stash stash;

    /**
     * Constructor for TaskManager class.
     *
     * @param inOut The Input object used for input/output operations.
     * @param stash The Stash object used to store tasks.
     */
    public TaskManager(Input inOut, Stash stash) {
        this.inOut = inOut;
        this.stash = stash;
    }

    /**
     * Adds a new task to the stash and prints a message indicating that the task was added
     *
     * @param task the task to be added
     */
    public void addNew(Task task) {
        stash.addNewTask(task);
        Output.printNewTask(task, stash.ObtainTaskCount());
    }

    /**
     * Deletes a task from the task list based on the user input details.
     *
     * @param details the details of the task to be deleted.
     * @throws ChronosExceptions if the input is invalid.
     */
    public void deleteTask(String details) throws ChronosExceptions {
        try {
            int index = Integer.parseInt(details) - 1;
            Task task = stash.getTask(index);
            stash.deleteTask(index);
            //Output.printIsDone(stash, index);
            Output.printDeletedTask(task, stash.ObtainTaskCount());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The index you have entered is invalid");
        }

    }

    /**
     * Toggles the status of a task.
     *
     * @param details the index of the task to toggle the status of
     */
    public void toggleTaskStatus(String details) {
        try {
            int index = Integer.parseInt(details) - 1;
            Task task = stash.getTask(index);
            task.toggleDone();
            Output.printIsDone(stash, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The index you have entered is invalid");
        }
    }

    /**
     * This method starts the timer module which allows users to keep track of their work and break times.
     * Users can start a work session and then press Enter to start a break, or type 'cancel' to stop the timer.
     *
     * @throws IllegalStateException if the timer cannot be started
     */

    public void timerModule() {
        Scanner timerCommand = new Scanner(System.in);
        Clock clock = new Clock();
        clock.startWork();
        System.out.println("Press Enter ONCE to START a break, or type 'cancel' to stop the timer: ");
        while (timerCommand.hasNextLine()) {
            String line = timerCommand.nextLine();
            if (line.equals("cancel")) {
                clock.cancelClock();
                break;
            } else {
                clock.startBreak();
            }
            timerCommand.close();
        }
    }

    /**
     * This method continuously reads input from the user and executes the corresponding action.
     * The method will keep running until the user enters the "done" command.
     *
     * @throws ChronosExceptions if the user enters an invalid input
     */
    public void inputCommands() {
        while (true) {
            try {
                Command userCommand = InputParser.parseInput(inOut.readInput());
                String category = userCommand.getAction();

                switch (category) {
                case "list":
                    Output.printList(stash);
                    continue;
                case "mark":
                case "unmark":
                    toggleTaskStatus(userCommand.getDetails());
                    continue;
                case "help":
                    Output.printHelp();
                    continue;
                case "todo":
                    addNew(new Todo(userCommand.getDetails()));
                    continue;
                case "delete":
                    deleteTask(userCommand.getDetails());
                    continue;
                case "event":
                    addNew(new Event(userCommand.getDetails(), userCommand.getStart(), userCommand.getEnd()));
                    continue;
                case "deadline":
                    addNew(new Deadline((userCommand.getDetails()), userCommand.getDue()));
                    continue;
                case "done":
                    System.out.println("Bye bye, hope to see you some time soon!");
                    return;
                case "find":
                    Output.printSearchResults(stash, userCommand.getDetails());
                    continue;
                case "timer":
                    timerModule();
                    continue;
                default:
                    throw new ChronosExceptions(category);
                }
            } catch (ChronosExceptions exceptions) {
                System.out.println("Sorry, I do not understand the input at this point in time.");
            }
        }
    }

}