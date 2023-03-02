package duke.parser;

import duke.DukeException;
import duke.tasks.TaskList;

/**
 * Class parses commands inputted by user and organizes them accordingly.
 */
public class Parser {

    private final String DEADLINE = "deadline";
    private final String TODO = "todo";
    private final String EVENT = "event";
    private final String BYE = "bye";
    private final String LIST = "list";
    private final String MARK = "mark";
    private final String UNMARK = "unmark";
    private final String DELETE = "delete";
    private boolean isUserDone;

    /**
     * Parses the user input and executes the methods associated
     * with the inputted task or command.
     *
     * @param input User input broken up into an array.
     * @param tasks List of all tasks.
     */
    public void parseInput(String[] input, TaskList tasks) {
        String taskType = input[0];
        try{
            // want to see all the tasks in a list
            if (taskType.equals(LIST)) {
                tasks.listTasks();
            // mark a task
            } else if (taskType.equals(MARK)) {
                int taskNumToMark = Integer.parseInt(input[1]);
                tasks.markTask(taskNumToMark);
            // unmark a task
            } else if (taskType.equals(UNMARK)) {
                int taskNumToUnmark = Integer.parseInt(input[1]);
                tasks.unmarkTask(taskNumToUnmark);
            // create a task
            } else if (taskType.equals(DEADLINE) || taskType.equals(TODO) || taskType.equals(EVENT)) {
                String taskDescription = String.join(" ", input).substring(taskType.length());
                if (taskType.equals(DEADLINE)) {
                    tasks.addDeadline(taskDescription, false);
                } else if (taskType.equals(TODO)) {
                    tasks.addTodo(taskDescription,false);
                } else {
                    tasks.addEvent(taskDescription, false);
                }
            // delete task
            } else if (taskType.equals(DELETE)) {
                int taskNumToDelete = Integer.parseInt(input[1]);
                tasks.deleteTask(taskNumToDelete);
            // exit
            } else if (taskType.equals(BYE)) {
                isUserDone = true;
            } else {
                throw new DukeException();
            }
        } catch (DukeException exception) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    /**
     * Returns whether user inputted "bye" to exit app.
     *
     * @return True if user inputted "bye" and false otherwise.
     */
    public boolean getIsUserDone() {
        return isUserDone;
    }

}
