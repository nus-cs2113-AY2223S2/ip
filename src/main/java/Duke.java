import java.util.Scanner;
import java.util.ArrayList;
import exceptions.DukeException;
import storage.TaskStorage;
import task.*;
import static storage.TaskStorage.*;
import static task.TaskList.*;

public class Duke {
    private static String LINE = "____________________________________________________________";
    private static String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static TaskList tasks;

    public Duke(String dataPath){
        TaskStorage.dataPath = dataPath;
        tasks = new TaskList();
        TaskStorage.loadSaveData();
    }

    public static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void runCommand(String input, String command) throws DukeException {
        if (command.equalsIgnoreCase("todo")) {
            addTodo(input);
        } else if (command.equalsIgnoreCase("deadline")) {
            addDeadline(input);
        } else if (command.equalsIgnoreCase("event")) {
            addEvent(input);
        } else if (command.equalsIgnoreCase("list")) {
            printList();
        } else if (command.equalsIgnoreCase("mark")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            markTask(taskIdx);
        } else if (command.equalsIgnoreCase("unmark")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            unmarkTask(taskIdx);
        }
        else if (command.equalsIgnoreCase("delete")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            deleteTask(taskIdx);
        } else {
            throw new DukeException(errorMessage);
        }
        writeSaveData(tasks);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        new Duke("tasks.txt");
        greet();
        String input, command;
        while (true) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            command = input.split(" ")[0];
            if (command.equalsIgnoreCase("bye")) {
                bye();
                break;
            }
            try {
                runCommand(input, command);
            } catch (DukeException e) {
                printError(e);
            }
        }
    }
}
