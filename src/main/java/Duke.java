import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.DukeException;
import parser.Parser;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();
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
                ArrayList<String> commands = Parser.parse(input);
                Command commandObject;
                switch (commands.get(0)) {
                case "bye":
                    isDone = true;
                    break;
                case "list":
                    commandObject = new ListCommand(commands);
                    commandObject.doCommand(tasks);
                    break;
                case "mark":
                    commandObject = new MarkCommand(commands);
                    commandObject.doCommand(tasks);
                    break;
                case "unmark":
                    commandObject = new UnmarkCommand(commands);
                    commandObject.doCommand(tasks);
                    break;
                case "todo":
                    commandObject = new TodoCommand(commands);
                    commandObject.doCommand(tasks);
                    break;
                case "deadline":
                    commandObject = new DeadlineCommand(commands);
                    commandObject.doCommand(tasks);
                    break;
                case "event":
                    commandObject = new EventCommand(commands);
                    commandObject.doCommand(tasks);
                    break;
                case "delete":
                    commandObject = new DeleteCommand(commands);
                    commandObject.doCommand(tasks);
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
