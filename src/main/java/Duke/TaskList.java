package Duke;

import Duke.Exceptions.DukeException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {
    static int numberOfTasks = 0;
    static ArrayList<Task> taskList = new ArrayList<>();

    public static void addTodo(ArrayList<Task> taskList, int numberOfTasks, String[] command) {
        taskList.add(new Todo(command[1]));
        taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    public static void addDeadline(ArrayList<Task> taskList, int numberOfTasks, String[] command) throws DukeException {
        if (!command[1].contains("/by")) {
            System.out.println("Error: Use /by");
            throw new DukeException();
        }
        String[] deadLineInputs = command[1].split("/by ", 2);
        taskList.add(new Deadline(deadLineInputs[0], deadLineInputs[1]));
        taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    public static void addEvent(ArrayList<Task> taskList, int numberOfTasks, String[] command) throws DukeException {
        if (!(command[1].contains("/from ") && command[1].contains("/to "))) {
            System.out.println("Error: Use /from and /to");
            throw new DukeException();
        }

        String[] eventInputs = command[1].split("/from|/to");
        taskList.add(new Event(eventInputs[0], eventInputs[1], eventInputs[2]));
        taskList.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    public static void doMarkOrUnmarked(ArrayList<Task> taskList, int numberOfTasks, String[] command) throws DukeException {
        int indexToChange = Integer.parseInt(command[1]) - 1;
        //Check for error
        if (indexToChange >= numberOfTasks || indexToChange < 0) {
            System.out.println("Index not found");
            throw new DukeException();
        }
        if (command[0].equals("mark")) {
            taskList.get(indexToChange).setDone();
            taskList.get(indexToChange).printMarkedTask();
        } else {
            taskList.get(indexToChange).setNotDone();
            taskList.get(indexToChange).printUnmarkedTask();
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, int indexToDelete, int numberOfTasks) {
        tasks.get(indexToDelete).printDeleteTask(numberOfTasks);
        tasks.remove(indexToDelete);
    }

    static void initializeTaskNumber(ArrayList<Task> taskList) {
        try {
            numberOfTasks += Storage.initializeTaskList(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found");
        }
    }
}
