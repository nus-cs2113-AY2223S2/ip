import command.*;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
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
        System.out.println(logo);
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
    }

    private static void exitDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    private static void runDuke() {
        Scanner in = new Scanner(System.in);
        while (!isDone) {
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
                System.out.println("____________________________________________________________"
                        + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + "____________________________________________________________");
            }
        }
    }
}
