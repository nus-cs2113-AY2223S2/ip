package Duke;

import java.io.IOException;

public class Parser {
    static boolean handleInput(String inputString) throws IOException, DukeException {
        String[] command = inputString.split(" ", 2);

        switch (command[0]) {
        case "list":
            Ui.doList(TaskList.taskList, TaskList.numberOfTasks);
            break;

        case "bye":
            Ui.doExit();
            Storage.saveTaskList(TaskList.taskList, TaskList.numberOfTasks);
            return true;

        case "delete":
            TaskList.deleteTask(TaskList.taskList, Integer.parseInt(command[1]) - 1, TaskList.numberOfTasks);
            TaskList.numberOfTasks -= 1;
            break;

        //mark/unmark command
        case "mark":
        case "unmark":
            TaskList.doMarkOrUnmarked(TaskList.taskList, TaskList.numberOfTasks, command);
            break;

        //add task to list
        case "todo":
            TaskList.addTodo(TaskList.taskList, TaskList.numberOfTasks, command);
            TaskList.numberOfTasks += 1;
            break;

        case "deadline":
            TaskList.addDeadline(TaskList.taskList, TaskList.numberOfTasks, command);
            TaskList.numberOfTasks += 1;
            break;

        case "event":
            TaskList.addEvent(TaskList.taskList, TaskList.numberOfTasks, command);
            TaskList.numberOfTasks += 1;
            break;

        default:
            System.out.println("Unknown command issued");

        }
        return false;
    }
}