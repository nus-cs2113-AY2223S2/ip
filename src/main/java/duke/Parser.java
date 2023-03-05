package duke;

import duke.instructions.Storage;
import duke.instructions.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private static final String UNRECOGNISED_INPUT = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String LINE = "    ____________________________________________________________";

    public static void sayBye() {
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Excute different instructions based on the command
     * @param input user input
     * @param taskNameInput list of task
     * @throws DukeException check for errors
     */
    public static void inputValues(String input, ArrayList<Task> taskNameInput) throws DukeException {
        String[] command = input.split("\\s+");
        switch (command[0]) {
        case "list":
            TaskList.listCommand(input, taskNameInput);
            break;
        case "mark":
            TaskList.markCommand(command, taskNameInput);
            break;
        case "ummark":
            TaskList.unMarkCommand(command, taskNameInput);
            break;
        case "todo":
            TaskList.toDoCommand(input, taskNameInput);
            break;
        case "deadline":
            TaskList.deadlineCommand(input, taskNameInput);
            break;
        case "event":
            TaskList.eventCommand(input, taskNameInput);
            break;
        case "delete":
            TaskList.deleteCommand(input, taskNameInput);
            break;
        case "find":
            TaskList.findCommand(input, taskNameInput);
            break;
        default:
            throw new DukeException(UNRECOGNISED_INPUT);

        }
    }

    /**
     * to check if the program end using the keyword "bye"
     * @param taskNameList list of task
     * @param filePath the path of the file
     * @return boolean values for checking the program
     */
    public static boolean checkInput(ArrayList<Task> taskNameList, String filePath) {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String taskName = userInput.nextLine();

            if (taskName.equalsIgnoreCase("bye")) {
                Storage.writeTaskToFile(filePath, taskNameList);
                return false;
            }
            try {
                inputValues(taskName, taskNameList);
            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }
    }
}
