package Duke;

import Duke.Tasks.Task;

import java.io.IOException;

public class Parser {
    /***
     * Takes in user input and carries out respective method based on command type.
     * @return true if bye command is issued, else returns false
     */
    static boolean handleInput(String inputString) throws IOException, DukeException {
        String[] command = inputString.split(" ", 2);

        switch (command[0]) {
        case "list":
            TaskList.doList();
            break;

        case "bye":
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