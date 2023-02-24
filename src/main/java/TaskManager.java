import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import static java.util.stream.Collectors.toList;

public class TaskManager {
    static ArrayList<Task> taskManager = new ArrayList<>();

    public static void listTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". "
                    + taskList.get(i));
        }
    }

    public static void addToDo(String[] parsedCommand) {
        ToDo newToDo = new ToDo(parsedCommand[1]);
        taskManager.add(newToDo);
        System.out.println("Don't sleep on it.\n" + "added: " + newToDo);
    }

    public static void addDeadline(String[] parsedCommand) {
        String[] taskAndDeadline = parsedCommand[1].split("/", 2);
        Deadline newDeadline = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
        taskManager.add(newDeadline);
        System.out.println("Time is money, chop chop!\n" + "added: " + newDeadline);
    }

    public static void addEvent(String[] parsedCommand) {
        String[] taskAndDuration = parsedCommand[1].split("/");
        Event newEvent = new Event(taskAndDuration[0], taskAndDuration[1], taskAndDuration[2]);
        taskManager.add(newEvent);
        System.out.println("I'll see you there.\n" + "added: " + newEvent);
    }

    public static void markTask(String[] parsedCommand) {
        int whichTask = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        taskManager.get(whichTask).setIsDone(true);
        System.out.println("That was long due, well done.\n" + taskManager.get(whichTask));
    }

    public static void unmarkTask(String[] parsedCommand) {
        int whichTask = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        taskManager.get(whichTask).setIsDone(false);
        System.out.println("You've gotten lazy.\n" + taskManager.get(whichTask));
    }

    public static void deleteTask(String[] parsedCommand) {
        int taskNumber = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        Task taskToDelete = taskManager.get(taskNumber); // store in temp var to print later
        taskManager.remove(taskNumber);
        System.out.println("That's off the list: \n" + taskToDelete);
        System.out.println("You're left with " + taskManager.size() + " task(s).");
    }

    public static ArrayList<Task> findTask(String[] parseCommand) {
        return (ArrayList<Task>) taskManager.stream()
                .filter(task -> task.getDescription().contains(parseCommand[1]))
                .collect(toList());
    }
}
