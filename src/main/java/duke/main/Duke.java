package duke.main;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.*;
import duke.command.actionCommands.DeleteTask;
import duke.command.actionCommands.ListTasks;
import duke.command.actionCommands.MarkTask;
import duke.command.actionCommands.UnmarkTask;
import duke.command.taskCommands.DeadlineTask;
import duke.command.taskCommands.EventTask;
import duke.command.taskCommands.TodoTask;
import duke.exception.InvalidTaskException;
import duke.tasks.*;


public class Duke {
    static final int limitTask = 100;
    //    static Task[] tasksList = new Task[limitTask];
    static ArrayList<Task> tasksList = new ArrayList<>();
    public static int taskCount = 0;

    public static void startBot() {
        Scanner in = new Scanner((System.in));
        while (true) {
            String input = in.nextLine();
            String[] args = input.split(" ", 2);
            Command newCommand;


            try {
                switch (args[0]) {
                case "bye":
                    return;
                case "list":
                    newCommand = new ListTasks();
                    newCommand.processCommand(tasksList, input);
                    break;
                case "delete":
                    newCommand = new DeleteTask();
                    newCommand.processCommand(tasksList, args[1]);
                    break;
                case "mark":
                    newCommand = new MarkTask();
                    newCommand.processCommand(tasksList, args[1]);
                    break;
                case "unmark":
                    newCommand = new UnmarkTask();
                    newCommand.processCommand(tasksList, args[1]);
                    break;
                case "todo":
                    newCommand = new TodoTask();
                    newCommand.processCommand(tasksList, args[1]);
                    break;
                case "deadline":
                    newCommand = new DeadlineTask();
                    newCommand.processCommand(tasksList, args[1]);
                    break;
                case "event":
                    newCommand = new EventTask();
                    newCommand.processCommand(tasksList, args[1]);
                    break;
                default:
                    throw new InvalidTaskException();
                }
            } catch (InvalidTaskException e) {
                printHorizontalLine();
                System.out.println(e.getMessage());
                printHorizontalLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                printHorizontalLine();
                System.out.println("Please enter all command parameter!");
                printHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("Hello! I'm duke.main.Duke\n" + " What can I do for you?\n");
        printHorizontalLine();

        startBot();

        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
