package Duke;

import Duke.Tasks.Task;

import java.io.IOException;


public class Parser {

    private static final String listCommand = "list";
    private static final String findCommand = "find";
    private static final String byeCommand = "bye";
    private static final String deleteCommand = "delete";
    private static final String markCommand = "mark";
    private static final String unmarkCommand = "unmark";
    private static final String todoCommand = "todo";
    private static final String deadlineCommand = "deadline";
    private static final String eventCommand = "event";

    /***
     * Takes in user input and carries out respective method based on command type.
     * @return true if bye command is issued, else returns false
     */
    static boolean handleInput(String inputString) throws IOException, DukeException {
        String[] command = inputString.split(" ", 2);

        switch (command[0]) {

        case listCommand:
            TaskList.doList();
            break;

        case findCommand:
            Ui.doFind(TaskList.taskList, command[1]);
            break;

        case byeCommand:
            Ui.doExit();
            Storage.saveTaskList(TaskList.taskList, TaskList.numberOfTasks);
            return true;


        case "delete":
            TaskList.deleteTask(Integer.parseInt(command[1]) - 1);
            TaskList.numberOfTasks -= 1;
            break;

        //mark/unmark command

        case "mark":
        case "unmark":
            TaskList.doMarkOrUnmarked(command);
            break;

        case "todo":
            TaskList.addTodo(command);
            TaskList.numberOfTasks += 1;
            break;

        case "deadline":
            TaskList.addDeadline(command);
            TaskList.numberOfTasks += 1;
            break;

        case "event":
            TaskList.addEvent(command);
            TaskList.numberOfTasks += 1;
            break;

        /**
         * Prints unknown command issued when command not detected
         */
        default:
            System.out.println("Unknown command issued");

        }
        return false;
    }
}