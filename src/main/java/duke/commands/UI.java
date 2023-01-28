package duke.commands;

import duke.commands.taskCommands.DeadlineCommand;
import duke.commands.taskCommands.EventCommand;
import duke.commands.taskCommands.ToDoCommand;
import duke.task.TaskList;

import java.util.Scanner;

public class UI {

    private static String lineBreak = "____________________________________________________________\n";

    public static String getLineBreak() {
        return lineBreak;
    }

    public void greet() {
        System.out.println(lineBreak + "Hello I'm\n" +
                "    ____        _        \n" +
                "   |  _ \\ _   _| | _____ \n" +
                "   | | | | | | | |/ / _ \\\n" +
                "   | |_| | |_| |   <  __/\n" +
                "   |____/ \\__,_|_|\\_\\___|" + "\nWhat can I do for you?\n" +
                "Input your tasks and I'll keep track of them!\n" + lineBreak);
    }

    public void sayBye() {
        System.out.println("Aww you're going? Hope to see you again soon!\n" + lineBreak);
    }

    public String parseCommand(String line){
        return line.contains(" ") ? line.split(" ")[0] : line;
    }

    public void inputFunction(TaskList taskList){
        Scanner in = new Scanner(System.in);
        String line;

        boolean isRunning = true;

        do {
            System.out.print("Enter Your Command Here: ");
            line = in.nextLine().trim();
            System.out.println(lineBreak);

            if (line.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else {

                String command = parseCommand(line);

                switch (command) {

                case "todo":
                    ToDoCommand newTodo = new ToDoCommand();
                    newTodo.handleCommand(line, taskList);
                    break;

                case "deadline":
                    DeadlineCommand newDeadline = new DeadlineCommand();
                    newDeadline.handleCommand(line, taskList);
                    break;

                case "event":
                    EventCommand newEvent = new EventCommand();
                    newEvent.handleCommand(line, taskList);
                    break;

                case "":
                    Command newCommand = new Command();
                    newCommand.handleCommand(line, taskList);
                    break;

                case "list":
                    ListCommand newList = new ListCommand();
                    newList.handleCommand(line, taskList);
                    break;

                case "mark":
                    MarkCommand newMark = new MarkCommand();
                    newMark.handleCommand(line, taskList);
                    break;

                case "unmark":
                    UnmarkCommand newUnmark = new UnmarkCommand();
                    newUnmark.handleCommand(line, taskList);
                    break;

                case "help":


                default:
                    DukeException.printError();
                    System.out.println(lineBreak);
                }
            }

        } while (isRunning);
    }
}
