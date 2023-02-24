import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import static java.util.stream.Collectors.toList;

public class TaskManager {
    static ArrayList<Task> taskManager = new ArrayList<>();

    public static void listTasks(ArrayList<Task> taskList) {
        Ui.printTasksMessage();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". "
                    + taskList.get(i));
        }
    }

    public static void addToDo(String[] parsedCommand) {
        ToDo newToDo = new ToDo(parsedCommand[1]);
        taskManager.add(newToDo);
        Ui.printToDoMessage(newToDo);
    }

    public static void addDeadline(String[] parsedCommand) {
        String[] taskAndDeadline = parsedCommand[1].split("/", 2);
        Deadline newDeadline = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
        taskManager.add(newDeadline);
        Ui.printDeadlineMessage(newDeadline);
    }

    public static void addEvent(String[] parsedCommand) {
        String[] taskAndDuration = parsedCommand[1].split("/");
        Event newEvent = new Event(taskAndDuration[0], taskAndDuration[1], taskAndDuration[2]);
        taskManager.add(newEvent);
        Ui.printEventMessage(newEvent);
    }

    public static void markTask(String[] parsedCommand) {
        int taskNumber = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        taskManager.get(taskNumber).setIsDone(true);
        Ui.printMarkTaskMessage(taskNumber);
    }

    public static void unmarkTask(String[] parsedCommand) {
        int taskNumber = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        taskManager.get(taskNumber).setIsDone(false);
        Ui.printUnmarkTaskMessage(taskNumber);
    }

    public static void deleteTask(String[] parsedCommand) {
        int taskNumber = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        Task taskToDelete = taskManager.get(taskNumber); // store in temp var to print later
        taskManager.remove(taskNumber);
        Ui.printDeleteTaskMessage(taskToDelete);
    }

    public static ArrayList<Task> findTask(String[] parseCommand) {
        return (ArrayList<Task>) taskManager.stream()
                .filter(task -> task.getDescription().contains(parseCommand[1]))
                .collect(toList());
    }
}
