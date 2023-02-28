package commands;

import exceptions.InvalidTaskException;
import tasks.Deadline;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

/**
 * Read the existing txt file and save the data into the current operation
 * allow the user to perform further modifications/operations
 */
public class SaveToCurrentOperation {
    public static void save(String task, ArrayList<Task> list) {
        boolean isDone = task.substring(0, 1).equals("1");
        String userCommand = task.substring(2);
        Parser newCommand = new Parser();
        switch (newCommand.parseCommand(userCommand)) {
            case "todo":
                AddTodo.addTodoTask(list, userCommand);
                list.get(list.size() - 1).setIsDone(isDone);
                break;
            case "deadline":
                AddDeadline.addDeadlineTask(list, userCommand);
                list.get(list.size() - 1).setIsDone(isDone);
                break;
            case "event":
                AddEvent.addEventTask(list, userCommand);
                list.get(list.size() - 1).setIsDone(isDone);
                break;
            default:
                break;
        }
    }

}
