import java.util.ArrayList;

/**
 * Represents a list of tasks that the user currently has inputted into the ChatBot
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<Task> getTaskList() {
        return listOfTasks;
    }

    /**
     * Lists all tasks that user has currently inputted in the current session.
     *
     * @param listOfTasks list of tasks in the current session.
     */
    public static void listTasks(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + "." + listOfTasks.get(i));
        }
    }

    /**
     * Adds Todo task type to current list of task.
     *
     * @param input user input.
     * @param listOfTasks list of tasks in the current session.
     */
    public static void addTodoToList(String input, ArrayList<Task> listOfTasks) {
        try {
            DukeExceptionTest.checkMissingTodoName(input);
        } catch (MissingTaskNameException e) {
            System.out.println(e);
            return;
        }
        int nameStartIndex = input.indexOf(" ");
        String name = input.substring(nameStartIndex + 1);
        Task todo = new Todo(name, false);
        listOfTasks.add(todo);
        UI.printAddTaskMessage(todo, listOfTasks);
    }

    /**
     * Adds Deadline task type to current list of task.
     *
     * @param input user input.
     * @param listOfTasks list of tasks in the current session.
     */
    public static void addDeadlineToList(String input, ArrayList<Task> listOfTasks) {
        int nameStartIndex = input.indexOf(" ");
        int nameEndIndex = input.indexOf("/by");
        String name = input.substring(nameStartIndex + 1, nameEndIndex -1);
        String dueDate = input.substring(nameEndIndex + 4);
        Task deadline = new Deadline(name, false, dueDate);
        listOfTasks.add(deadline);
        UI.printAddTaskMessage(deadline, listOfTasks);
    }

    /**
     * Adds Event task type to current list of task.
     *
     * @param input user input.
     * @param listOfTasks list of tasks in the current session.
     */
    public static void addEventToList(String input, ArrayList<Task> listOfTasks) {
        int nameStartIndex = input.indexOf(" ");
        int nameEndIndex = input.indexOf("/from");
        String name = input.substring(nameStartIndex + 1, nameEndIndex - 1);
        String remainingInfo = input.substring(nameEndIndex + 6);
        int toIndex = remainingInfo.indexOf("/to");
        String eventStart = remainingInfo.substring(0, toIndex - 1);
        String eventEnd = remainingInfo.substring(toIndex + 4);
        Task event = new Event(name, false, eventStart, eventEnd);
        listOfTasks.add(event);
        UI.printAddTaskMessage(event, listOfTasks);
    }

    /**
     * Deletes task in the current list of task.
     *
     * @param taskNumber the xth numbered task in the current session.
     * @param listOfTasks list of tasks in the current session.
     */
    public static void deleteTask(int taskNumber, ArrayList<Task> listOfTasks) {
        UI.printDeleteTaskMessage(taskNumber, listOfTasks);
        listOfTasks.remove(taskNumber);
    }

    /**
     * List all tasks in the current list of task that matches the inputted keyword.
     *
     * @param input user input.
     * @param listOfTasks list of tasks in the current session.
     */
    public static void findTask(String input, ArrayList<Task> listOfTasks) {
        String keyword = input.substring(input.indexOf(" ") + 1);
        for (int i = 0; i < listOfTasks.size(); i ++) {
            String taskName = listOfTasks.get(i).getName();
            if (taskName.contains(keyword)) {
                UI.printFindTaskMessage(i+1, listOfTasks.get(i));
            }
        }
    }
}
