import java.util.ArrayList;

public class Parser {
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
