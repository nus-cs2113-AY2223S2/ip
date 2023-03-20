package duke;

import java.util.ArrayList;

/**
 * Deals with making sense of user command
 */
public class Parser {
    protected static int TODO_OFFSET = 5;
    protected static int DEADLINE_OFFSET = 9;
    protected static int BY_OFFSET = 4;
    protected static int EVENT_OFFSET = 6;
    protected static int FROM_OFFSET = 6;
    protected static int TO_OFFSET = 4;
    protected static int FIND_OFFSET = 5;

    /**
     * Handles command(action) input by the user
     * Returns updated tasks
     *
     * @param tasks all the tasks added
     * @param action the input line by the user
     * @param file the txt file storing the tasks
     * @return tasks
     * @throws DukeException when the command word is not recognised
     * @throws DukeException.TaskEmpty when the content of task to be added is empty
     */
    public static ArrayList<Task> handleAction(ArrayList<Task> tasks, String action, TaskData file
            ) throws DukeException, DukeException.TaskEmpty{
        if (action.equals("bye")) {
            Ui.exitProgram();
            Duke.shouldContinue = false;

        } else if (action.equals("list")) {
            Ui.listTasks(tasks, Duke.taskCount);

        } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
            throw new DukeException.TaskEmpty();

        } else if (action.startsWith("mark")){
            int dividerPos = action.indexOf(" ");
            int toBeMarked = Integer.parseInt(action.substring(dividerPos + 1)) - 1;
            tasks.get(toBeMarked).markAsDone();
            Ui.printMarked(tasks.get(toBeMarked), action.split(" ")[0]);
            file.updateFile(tasks, file);

        } else if (action.startsWith("unmark")) {
            int dividerPos = action.indexOf(" ");
            int toBeUnmarked = Integer.parseInt(action.substring(dividerPos + 1)) - 1;
            tasks.get(toBeUnmarked).markAsUndone();
            Ui.printMarked(tasks.get(toBeUnmarked), action.split(" ")[0]);
            file.updateFile(tasks, file);

        } else if (action.startsWith("delete")) {
            int dividerPos = action.indexOf(" ");
            int toBeDeleted = Integer.parseInt(action.substring(dividerPos + 1)) - 1;
            Task removedTask = tasks.get(toBeDeleted);
            tasks.remove(toBeDeleted);
            Duke.taskCount -= 1;
            Ui.printDeleted(removedTask);
            Ui.printNumTask(Duke.taskCount);
            file.updateFile(tasks, file);

        } else if (action.startsWith("todo")){
            Task tempTask = new Task(action.substring(TODO_OFFSET));
            tasks.add(tempTask);
            Ui.printAdded(tasks, Duke.taskCount);
            Duke.taskCount += 1;
            Ui.printNumTask(Duke.taskCount);
            tasks = file.addTaskToFile(tasks, tempTask, file);

        } else if (action.startsWith("deadline")) {
            int dividerPosition = action.indexOf("/by");
            Task tempTask = new Deadline(action.substring(DEADLINE_OFFSET,dividerPosition - 1),
                    action.substring(dividerPosition + BY_OFFSET));
            tasks.add(tempTask);
            Ui.printAdded(tasks, Duke.taskCount);
            Duke.taskCount += 1;
            Ui.printNumTask(Duke.taskCount);
            tasks = file.addTaskToFile(tasks, tempTask, file);

        } else if (action.startsWith("event")) {
            int dividerPosition1 = action.indexOf("/from");
            int dividerPosition2 = action.indexOf("/to");
            //extract the event details
            Task tempTask = new Event(action.substring(EVENT_OFFSET,dividerPosition1 - 1),
                    action.substring(dividerPosition1 + FROM_OFFSET, dividerPosition2 - 1),
                    action.substring(dividerPosition2 + TO_OFFSET));
            tasks.add(tempTask);
            Ui.printAdded(tasks, Duke.taskCount);
            Duke.taskCount += 1;
            Ui.printNumTask(Duke.taskCount);
            tasks = file.addTaskToFile(tasks, tempTask, file);

        } else if (action.startsWith("find")) {
            String taskToFind = action.substring(FIND_OFFSET);
            Ui.printFound(tasks, taskToFind);

        }  else {
            throw new DukeException();
        }
        return tasks;
    }
}
