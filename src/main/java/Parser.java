import java.util.ArrayList;

/**
 * Represents a checker that validates and categorise all user inputs
 */
public class Parser {
    /**
     * checks user input for valid commands.
     * If no valid command is found, user is attempting to input task and check for task type.
     *
     * @param input the user input.
     * @param listOfTasks the list of tasks that is currently in the ChatBot.
     */
    public static void checkInput(String input, ArrayList<Task> listOfTasks) {
        String[] inputs = input.split(" ");
        switch (inputs[0]) {
        case "bye":
            break;
        case "list":
            TaskList.listTasks(listOfTasks);
            break;
        case "mark":
            Task taskToBeMarked = listOfTasks.get(Integer.parseInt(inputs[1]) - 1);
            taskToBeMarked.setStatus(true);
            UI.printUpdateStatusMessage(taskToBeMarked.getStatus(), taskToBeMarked);
            break;
        case "unmark":
            Task taskToBeUnmarked = listOfTasks.get(Integer.parseInt(inputs[1]) - 1);
            taskToBeUnmarked.setStatus(false);
            UI.printUpdateStatusMessage(taskToBeUnmarked.getStatus(), taskToBeUnmarked);
            break;
        case "delete":
            int taskNumber = Integer.parseInt(inputs[1]) - 1;
            TaskList.deleteTask(taskNumber, listOfTasks);
            break;
        case "find":
            TaskList.findTask(input, listOfTasks);
            break;
        default:
            try {
                Parser.checkTaskType(input, listOfTasks);
                break;
            } catch (InvalidTaskTypeException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Checks for valid task type in user input.
     *
     * @param input the user input.
     * @param listOfTasks the list of tasks that is currently in the ChatBot.
     * @throws InvalidTaskTypeException if task is not todo, deadline or event.
     */
    public static void checkTaskType(String input, ArrayList<Task> listOfTasks) throws InvalidTaskTypeException {
        String taskType = input;
        int endTaskTypeIndex = input.indexOf(" ");
        if (endTaskTypeIndex > 0) {
            taskType = input.substring(0, endTaskTypeIndex);
        }
        switch (taskType) {
        case "todo":
            TaskList.addTodoToList(input, listOfTasks);
            break;
        case "deadline":
            TaskList.addDeadlineToList(input, listOfTasks);
            break;
        case "event":
            TaskList.addEventToList(input, listOfTasks);
            break;
        default:
            throw new InvalidTaskTypeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
