package duke;

import duke.instructions.Storage;
import duke.instructions.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private static final String UNRECOGNISED_INPUT = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String FILE_PATH = "data/tasks.txt";

    public static final String LINE = "    ____________________________________________________________";

    public static void sayBye() {
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void inputValues(String input) throws DukeException {
        String[] command = input.split("\\s+");
        switch (command[0]) {
        case "list":
            TaskList.listCommand(input);
            break;
        case "mark":
            TaskList.markCommand(command);
            break;
        case "ummark":
            TaskList.unMarkCommand(command);
            break;
        case "todo":
            TaskList.toDoCommand(input);
            break;
        case "deadline":
            TaskList.deadlineCommand(input);
            break;
        case "event":
            TaskList.eventCommand(input);
            break;
        case "delete":
            TaskList.deleteCommand(input);
            break;
        case "find":
            TaskList.findCommand(input);
            break;
        default:
            throw new DukeException(UNRECOGNISED_INPUT);

        }
    }

    public static boolean checkInput(ArrayList<Task> taskNameList) {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String taskName = userInput.nextLine();

            if (taskName.equalsIgnoreCase("bye")) {
                Storage.writeTaskToFile(FILE_PATH, taskNameList);
                return false;
            }
            try {
                Storage.loadTaskFromFile(FILE_PATH, taskNameList);
                inputValues(taskName);
            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }
    }
}
