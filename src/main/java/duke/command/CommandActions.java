package duke.command;

import duke.output.Printer;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class CommandActions {
    public static void markTask(ArrayList<Task> tasks, String[] arrayOfInput) {
        tasks.get(Integer.parseInt(arrayOfInput[1]) - 1).markAsDone();
        Printer.markOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void unmarkTask(ArrayList<Task> tasks, String[] arrayOfInput) {
        tasks.get(Integer.parseInt(arrayOfInput[1]) - 1).markAsNotDone();
        Printer.markOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void addTodoTask(ArrayList<Task> tasks, String[] input) {
        tasks.add(Task.totalTasks, new Todo(input[1]));
    }

    public static void addDeadlineTask(ArrayList<Task> tasks, String input) {
        String[] commandInformation = input.split(" ", 2);
        String[] taskNameAndDate = commandInformation[1].split(" /by ", 2);
        tasks.add(Task.totalTasks, new Deadline(taskNameAndDate[0].trim(), taskNameAndDate[1].trim()));
    }

    public static void addEventTask(ArrayList<Task> tasks, String input) {
        String taskNameInformation = input.split(" ", 2)[1];
        String[] taskNameAndDate = taskNameInformation.split(" /from ", 2); // name fromTo
        String[] fromAndTo = taskNameAndDate[1].split(" /to ", 2); // from to
        tasks.add(Task.totalTasks, new Event(taskNameAndDate[0].trim(), fromAndTo[0].trim(), fromAndTo[1].trim()));
    }

    public static void deleteTask(ArrayList<Task> tasks, String[] arrayOfInput) {
        Printer.deleteTask(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
        tasks.remove(tasks.get(Integer.parseInt(arrayOfInput[1]) - 1));
        Task.totalTasks -= 1;
    }
}
