import command.*;
import exception.DukeException;
import parser.Parser;
import task.Task;

import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static boolean isDone = false;

    public static void main(String[] args) {
        greetUser();
        runDuke();
        exitDuke();
    }

    private static void greetUser() {
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
    }

    private static void exitDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    private static void runDuke() {
        Scanner in = new Scanner(System.in);
        while (!isDone) {
            try {
                String input = in.nextLine();
                String[] commands = Parser.parse(input);
                Command commandObject;
                switch (commands[0]) {
                case "bye":
                    isDone = true;
                    break;
                case "list":
                    commandObject = new ListCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    break;
                case "mark":
                    commandObject = new MarkCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    break;
                case "unmark":
                    commandObject = new UnmarkCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    break;
                case "todo":
                    commandObject = new TodoCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    taskCount += 1;
                    break;
                case "deadline":
                    commandObject = new DeadlineCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    taskCount += 1;
                    break;
                case "event":
                    commandObject = new EventCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    taskCount += 1;
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                String errorMessage = e.getMessage();
                System.out.println(line + '\n' + errorMessage + '\n' + line);
            }
        }
    }
}
