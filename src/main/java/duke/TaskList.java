package duke;

import duke.instructions.Deadline;
import duke.instructions.Event;
import duke.instructions.Task;
import duke.instructions.Todo;

import java.util.ArrayList;

/*
    contains the methods for different instructions
 */

public class TaskList {

    public static final String LINE = "    ____________________________________________________________";
    public static final String EMPTY_DESCRIPTION = "     ☹ OOPS!!! The description cannot be empty.";


    /**
     * Print a list of tasks that have been entered
     * @param command user input that contains the user-specific keyword "list"
     */
    public static void listCommand(String command, ArrayList<Task> taskNameList) {
        Task task = new Task(command);
        System.out.println(LINE);
        System.out.print(task.guideline());
        for (int indexOfInstruction = 0; indexOfInstruction < taskNameList.size(); indexOfInstruction++) {
            System.out.print("     " + (indexOfInstruction + 1) + "."
                    + taskNameList.get(indexOfInstruction).getState().trim() + System.lineSeparator());
        }
        System.out.println(LINE);

    }

    /**
     * Print the task that have been marked
     * @param command user input that contains the user-specific keyword "mark"
     */

    public static void markCommand(String[] command, ArrayList<Task> taskNameList) {
        int indexTask = Integer.parseInt(command[1]);
        System.out.println(LINE);
        taskNameList.get(indexTask - 1).mark();
        System.out.print("     Nice! I've marked this task as done:"
                + System.lineSeparator()
                + taskNameList.get(indexTask - 1).getState());
        System.out.println(LINE);
    }

    /**
     * Print the task that have been unmarked
     * @param command user input that contains the user-specific keyword "unmark"
     */
    public static void unMarkCommand(String[] command , ArrayList<Task> taskNameList) {
        int indexOfTask = Integer.parseInt(command[1]);
        System.out.println(LINE);
        taskNameList.get(indexOfTask - 1).unMark();
        System.out.print("     OK, I've marked this task as not done yet:"
                + System.lineSeparator()
                + taskNameList.get(indexOfTask - 1).getState());
        System.out.println(LINE);

    }

    /**
     * Print the task that need to be done by user
     * @param command user input that contains the user-specific keyword "todo"
     */
    public static void toDoCommand(String command , ArrayList<Task> taskNameList) throws DukeException {
        Todo toDo = new Todo(command);
        toDo.setTaskType("T");
        Task task = new Task(command);
        String[] tokens = command.split("\\s+", 2);
        if (tokens.length < 2) {
            throw new DukeException(EMPTY_DESCRIPTION);
        }
        System.out.println(LINE);
        taskNameList.add(toDo);
        String outputForTodo = toDo.guideline()
                + toDo.getState()
                + task.numberOfTask(taskNameList.size());
        System.out.println(outputForTodo);
        System.out.println(LINE);

    }


    /**
     * Print the event that have been entered
     * @param command user input that contains the user-specific keyword "mark"
     */
    public static void eventCommand(String command , ArrayList<Task> taskNameList) {
        Event event = new Event(command);
        event.setTaskType("E");
        Task task = new Task(command);
        System.out.println(LINE);
        taskNameList.add(event);
        String outputForEvent = event.guideline()
                + event.getState()
                + task.numberOfTask(taskNameList.size());
        System.out.println(outputForEvent);
        System.out.println(LINE);

    }

    /**
     * Print the deadline that for the task they have entered
     * @param command user input that contains the user-specific keyword "deadline"
     */
    public static void deadlineCommand(String command , ArrayList<Task> taskNameList) {
        Deadline deadLine = new Deadline(command);
        deadLine.setTaskType("D");
        Task task = new Task(command);
        System.out.println(LINE);
        taskNameList.add(deadLine);
        String outputForDeadline = deadLine.guideline()
                + deadLine.getState()
                + task.numberOfTask(taskNameList.size());
        System.out.println(outputForDeadline);
        System.out.println(LINE);
    }

    /**
     * Print the task that have been deleted
     * @param command user input that contains the user-specific keyword "delete"
     */
    public static void deleteCommand(String command, ArrayList<Task> taskNameList){
        int indexTask = Integer.parseInt(command);
        Task task = new Task(command);

        System.out.println(LINE);
        System.out.print("     Noted. I've removed this task:"
                + System.lineSeparator()
                + taskNameList.get(indexTask - 1).getState());
        taskNameList.remove(indexTask - 1);
        System.out.println(task.numberOfTask(taskNameList.size()));
        System.out.println(LINE);
    }
    /**
     * Print the task that can be found in the tasks list
     * @param command user input that contains the user-specific keyword "find
     */
    public static void findCommand(String command,  ArrayList<Task> taskNameList){
        String keyword = command.substring(5);

        ArrayList<Task> matchedTask = new ArrayList<>();

        for (Task task : taskNameList) {
            if ((task.getState()).contains(keyword)) {
               matchedTask.add(task);
            }
        }
        int numberOfFoundTasks = matchedTask.size();
        int number0fTask = 1;
        System.out.println("     There are " + numberOfFoundTasks + " matching tasks in your list");
        for (Task foundTask : matchedTask) {
            if (foundTask != null) {
                System.out.print("     " +number0fTask + "." + foundTask.getState().trim() + System.lineSeparator());
            }
            number0fTask++;
        }
    }

}
