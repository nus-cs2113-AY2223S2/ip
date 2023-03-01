package Duke;

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


    static boolean handleInput(String inputString) throws IOException, DukeException {
        String[] command = inputString.split(" ", 2);

        switch (command[0]) {
        case listCommand:
            Ui.doList(TaskList.taskList, TaskList.numberOfTasks);
            break;

        case findCommand:
            Ui.doFind(TaskList.taskList, command[1]);
            break;

        case byeCommand:
            Ui.doExit();
            Storage.saveTaskList(TaskList.taskList, TaskList.numberOfTasks);
            return true;

        case deleteCommand:
            TaskList.deleteTask(TaskList.taskList, Integer.parseInt(command[1]) - 1, TaskList.numberOfTasks);
            TaskList.numberOfTasks -= 1;
            break;

        //mark/unmark command
        case markCommand:
        case unmarkCommand:
            TaskList.doMarkOrUnmarked(TaskList.taskList, TaskList.numberOfTasks, command);
            break;

        //add task to list
        case todoCommand:
            TaskList.addTodo(TaskList.taskList, TaskList.numberOfTasks, command);
            TaskList.numberOfTasks += 1;
            break;

        case deadlineCommand:
            TaskList.addDeadline(TaskList.taskList, TaskList.numberOfTasks, command);
            TaskList.numberOfTasks += 1;
            break;

        case eventCommand:
            TaskList.addEvent(TaskList.taskList, TaskList.numberOfTasks, command);
            TaskList.numberOfTasks += 1;
            break;

        default:
            System.out.println("Unknown command issued");

        }
        return false;
    }
}
